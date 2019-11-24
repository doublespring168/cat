/*
 * Copyright (c) 2011-2018, Meituan Dianping. All Rights Reserved.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dianping.cat.analyzer;

import com.dianping.cat.Cat;
import com.dianping.cat.CatConstants;
import com.dianping.cat.configuration.ClientConfigManager;
import com.dianping.cat.configuration.ProblemLongType;
import com.dianping.cat.message.Transaction;
import com.dianping.cat.message.spi.MessageTree;
import com.doublespring.common.U;
import com.doublespring.log.LogUtil;

import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TransactionAggregator {

	private static TransactionAggregator s_instance = new TransactionAggregator();

	private volatile ConcurrentHashMap<String, ConcurrentHashMap<String, TransactionData>> m_transactions = new ConcurrentHashMap<String, ConcurrentHashMap<String, TransactionData>>();

	public static TransactionAggregator getInstance() {
		return s_instance;
	}

	public void logTransaction(Transaction t) {
		LogUtil.info("记录 Transaction 类消息");
		TransactionData transactionData = makeSureTransactionExist(t.getType(), t.getName());
		transactionData.add(t);
		LogUtil.info("记录 transactionData 返参", U.format("transactionData", U.toString(transactionData)));
	}

	private TransactionData makeSureTransactionExist(String type, String name) {

		LogUtil.info("检测 TransactionData 是否存在", U.format("type", type, "name", name));

		ConcurrentHashMap<String, TransactionData> item = m_transactions.get(type);

		if (null == item) {

			LogUtil.info("ConcurrentHashMap<String, TransactionData> 不存在 ,即将新建", U.format("type", type, "name", name));
			item = new ConcurrentHashMap<String, TransactionData>();

			ConcurrentHashMap<String, TransactionData> oldValue = m_transactions.putIfAbsent(type, item);

			if (oldValue != null) {
				item = oldValue;
			}
		}

		TransactionData data = item.get(name);

		if (null == data) {

			LogUtil.info("TransactionData 不存在 ,即将新建", U.format("type", type, "name", name));

			data = createTransactionData(type, name);

			TransactionData oldValue = item.putIfAbsent(name, data);

			if (oldValue == null) {
				return data;
			} else {
				return oldValue;
			}
		}

		return data;
	}

	private TransactionData createTransactionData(String type, String name) {
		TransactionData transactionData = new TransactionData(type, name);
		LogUtil.info("创建 TransactionData", U.format("transactionData", U.toString(transactionData), "type", type, "name", name));
		return transactionData;
	}

	public void sendTransactionData() {

		LogUtil.info("发送 TransactionData 数据");
		ConcurrentHashMap<String, ConcurrentHashMap<String, TransactionData>> transactions = getAndResetTransactions();
		boolean hasData = false;

		for (Map<String, TransactionData> entry : transactions.values()) {
			for (TransactionData data : entry.values()) {
				if (data.getCount().get() > 0) {
					hasData = true;
					break;
				}
			}
		}

		if (hasData) {

			Transaction transaction = Cat.newTransaction(CatConstants.CAT_SYSTEM, this.getClass().getSimpleName());
			LogUtil.info("实例化 Transaction", U.format("transaction", U.toString(transaction)));
			MessageTree messageTree = Cat.getManager().getThreadLocalMessageTree();
			LogUtil.info("实例化 MessageTree", U.format("messageTree", U.toString(messageTree)));


			messageTree.setDomain(getDomain(messageTree));
			messageTree.setDiscardPrivate(false);

			for (Map<String, TransactionData> entry : transactions.values()) {
				for (TransactionData data : entry.values()) {
					if (data.getCount().get() > 0) {

						Transaction tmp = Cat.newTransaction(data.getType(), data.getName());

						LogUtil.info("实例化 Transaction", U.format("Transaction", tmp));

						StringBuilder sb = new StringBuilder(32);

						sb.append(CatConstants.BATCH_FLAG).append(data.getCount().get()).append(CatConstants.SPLIT);
						sb.append(data.getFail().get()).append(CatConstants.SPLIT);
						sb.append(data.getSum().get()).append(CatConstants.SPLIT);
						sb.append(data.getDurationString()).append(CatConstants.SPLIT).append(data.getLongDurationString());

						tmp.addData(sb.toString());
						tmp.setSuccessStatus();
						tmp.complete();

						LogUtil.info("TODO 即将发送 Transaction 类消息", U.format("Transaction", tmp));

					}
				}
			}

			transaction.setSuccessStatus();
			transaction.complete();

			LogUtil.info("TODO 即将发送 Transaction 类消息", U.format("Transaction", transaction));

		}
	}

	public ConcurrentHashMap<String, ConcurrentHashMap<String, TransactionData>> getAndResetTransactions() {

		LogUtil.info("即将 clone TransactionData 缓存数据");

		ConcurrentHashMap<String, ConcurrentHashMap<String, TransactionData>> cloned = m_transactions;

		m_transactions = new ConcurrentHashMap<String, ConcurrentHashMap<String, TransactionData>>();

		for (Entry<String, ConcurrentHashMap<String, TransactionData>> entry : cloned.entrySet()) {
			String type = entry.getKey();
			m_transactions.putIfAbsent(type, new ConcurrentHashMap<String, TransactionData>());
		}

		LogUtil.info("clone TransactionData 缓存数据返参", U.format("TransactionDatas", U.toString(cloned)));


		return cloned;
	}

	public String getDomain(MessageTree tree) {
		String domain = Cat.getManager().getDomain();
		LogUtil.info("获取 domain 返参", U.format("domain", domain));
		return domain;
	}

	private int checkAndGetLongThreshold(String type, int duration) {
		ClientConfigManager config = Cat.getManager().getConfigManager();
		ProblemLongType longType = ProblemLongType.findByMessageType(type);

		int result = -1;

		if (longType != null) {
			switch (longType) {
				case LONG_CACHE:
					result = config.getLongThresholdByDuration(ProblemLongType.LONG_CACHE.getName(), duration);
					break;
				case LONG_CALL:
					result = config.getLongThresholdByDuration(ProblemLongType.LONG_CALL.getName(), duration);
					break;
				case LONG_SERVICE:
					result = config.getLongThresholdByDuration(ProblemLongType.LONG_SERVICE.getName(), duration);
					break;
				case LONG_SQL:
					result = config.getLongThresholdByDuration(ProblemLongType.LONG_SQL.getName(), duration);
					break;
				case LONG_URL:
					result = config.getLongThresholdByDuration(ProblemLongType.LONG_URL.getName(), duration);
					break;
				case LONG_MQ:
					result = config.getLongThresholdByDuration(ProblemLongType.LONG_MQ.getName(), duration);
					break;
			}
		}

		return result;
	}

	public class TransactionData {

		private String m_type;

		private String m_name;

		private AtomicInteger m_count = new AtomicInteger();

		private AtomicInteger m_fail = new AtomicInteger();

		private AtomicLong m_sum = new AtomicLong();

		private ConcurrentHashMap<Integer, AtomicInteger> m_durations = new ConcurrentHashMap<Integer, AtomicInteger>();

		private ConcurrentHashMap<Integer, AtomicInteger> m_longDurations = new ConcurrentHashMap<Integer, AtomicInteger>();

		public TransactionData(String type, String name) {
			LogUtil.info("实例化 TransactionData", U.format("type", type, "name", name));
			m_type = type;
			m_name = name;
		}

		public TransactionData add(int count, int error, long sum) {

			LogUtil.info("更新 TransactionData 消息", U.format("count", count, "error", error, "sum", sum));

			m_count.addAndGet(count);
			m_sum.addAndGet(sum);
			m_fail.addAndGet(error);

			if (count == 1) {

				LogUtil.info("即将初始化 m_durations");
				int duration = DurationComputer.computeDuration((int) sum);
				AtomicInteger durationCount = m_durations.get(duration);

				if (durationCount == null) {
					LogUtil.info("durationCount 不存在 ,即将初始化");
					m_durations.put(duration, new AtomicInteger(1));
				} else {
					int i = durationCount.incrementAndGet();
					LogUtil.info("durationCount 已存在,即将更新", U.format("durationCount", i));
				}
			}

			return this;
		}

		public TransactionData add(Transaction transaction) {

			LogUtil.info("更新 TransactionData", U.format("Transaction", U.toString(transaction)));

			m_count.incrementAndGet();
			m_sum.getAndAdd(transaction.getDurationInMillis());

			LogUtil.info("更新 TransactionData 统计数据", U.format("m_count", m_count, "m_sum", m_sum, "m_fail", m_fail.get()));

			if (!transaction.isSuccess()) {
				int failNum = m_fail.incrementAndGet();
				LogUtil.info("更新 TransactionData 统计数据", U.format("m_fail", failNum));
			}

			int duration = DurationComputer.computeDuration((int) transaction.getDurationInMillis());
			AtomicInteger count = m_durations.get(duration);

			if (count == null) {

				LogUtil.info("m_durations 不存在,即将初始化", U.format("duration", duration));
				count = new AtomicInteger(0);

				AtomicInteger oldCount = m_durations.putIfAbsent(duration, count);

				if (oldCount != null) {
					count = oldCount;
				}
			}

			int countNum = count.incrementAndGet();

			LogUtil.info("duration count数量为", U.format("count", countNum));

			String type = transaction.getType();
			int longDuration = checkAndGetLongThreshold(type, duration);

			LogUtil.info("加载 longDuration", U.format("type", type, "duration", duration, "longDuration", longDuration));

			if (longDuration > 0) {

				LogUtil.info("longDuration 不存在,即将初始化");
				AtomicInteger longCount = m_longDurations.get(longDuration);

				if (longCount == null) {

					LogUtil.info("longCount 不存在,即将初始化");

					longCount = new AtomicInteger(0);

					AtomicInteger oldLongCount = m_longDurations.putIfAbsent(longDuration, longCount);

					if (oldLongCount != null) {
						longCount = oldLongCount;
					}
				}

				int longCountNum = longCount.incrementAndGet();
				LogUtil.info("longCount 更新后值为", U.format("longCount", longCountNum, "type", type, "duration", duration, "longDuration", longDuration));

			}
			return this;
		}

		public AtomicInteger getCount() {
			return m_count;
		}

		public Map<Integer, AtomicInteger> getDurations() {
			return m_durations;
		}

		public String getDurationString() {
			StringBuilder sb = new StringBuilder();
			boolean first = true;

			for (Entry<Integer, AtomicInteger> entry : m_durations.entrySet()) {
				Integer key = entry.getKey();
				AtomicInteger value = entry.getValue();

				if (first) {
					sb.append(key).append(',').append(value);
					first = false;
				} else {
					sb.append('|').append(key).append(',').append(value);
				}
			}
			String result = sb.toString();
			LogUtil.info("getDurationString 返参", U.format("m_durations", result));
			return result;
		}

		public Map<Integer, AtomicInteger> getLongDurations() {
			return m_longDurations;
		}

		public String getLongDurationString() {
			StringBuilder sb = new StringBuilder();
			boolean first = true;

			for (Entry<Integer, AtomicInteger> entry : m_longDurations.entrySet()) {
				Integer key = entry.getKey();
				AtomicInteger value = entry.getValue();

				if (first) {
					sb.append(key).append(',').append(value);
					first = false;
				} else {
					sb.append('|').append(key).append(',').append(value);
				}
			}

			String result = sb.toString();
			LogUtil.info("getLongDurationString 返参", U.format("m_longDurations", result));
			return result;
		}

		public AtomicInteger getFail() {
			return m_fail;
		}

		public String getName() {
			return m_name;
		}

		public AtomicLong getSum() {
			return m_sum;
		}

		public String getType() {
			return m_type;
		}
	}

}
