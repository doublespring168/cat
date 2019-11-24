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
package com.dianping.cat.configuration;

import com.doublespring.common.U;
import com.doublespring.log.LogUtil;

public enum ProblemLongType {

	LONG_CACHE("long-cache", 25) {
		@Override
		protected boolean checkLongType(String type) {
			Boolean result = type.startsWith("Squirrel.") || type.startsWith("Cellar.") || type.startsWith("Cache.");
			LogUtil.info("long-cache 类型检测返参", U.format("result", result));
			return result;
		}
	},

	LONG_CALL("long-call", 100) {
		@Override
		protected boolean checkLongType(String type) {
			Boolean result = "PigeonCall".equals(type) || "OctoCall".equals(type) || "Call".equals(type);
			LogUtil.info("long-call 类型检测返参", U.format("result", result));
			return result;
		}
	},

	LONG_SERVICE("long-service", 100) {
		@Override
		protected boolean checkLongType(String type) {
			Boolean result = "PigeonService".equals(type) || "OctoService".equals(type) || "Service".equals(type);
			LogUtil.info("long-service 类型检测返参", U.format("result", result));
			return result;

		}
	},

	LONG_SQL("long-sql", 100) {
		@Override
		protected boolean checkLongType(String type) {
			Boolean result = "SQL".equals(type);
			LogUtil.info("long-sql 类型检测返参", U.format("result", result));
			return result;
		}
	},

	LONG_URL("long-url", 1000) {
		@Override
		protected boolean checkLongType(String type) {
			Boolean result = "URL".equals(type);
			LogUtil.info("long-url 类型检测返参", U.format("result", result));
			return result;
		}
	},

	LONG_MQ("long-mq", 100) {
		@Override
		protected boolean checkLongType(String type) {
			Boolean result = "MtmqRecvMessage".equals(type) || "MafkaRecvMessage".equals(type);
			LogUtil.info("long-mq 类型检测返参", U.format("result", result));
			return result;
		}
	};

	private String m_name;

	private int m_threshold;

	ProblemLongType(String name, int threshold) {
		m_name = name;
		m_threshold = threshold;
	}

	public static ProblemLongType findByName(String name) {
		for (ProblemLongType longType : values()) {
			if (longType.getName().equals(name)) {
				return longType;
			}
		}

		throw new RuntimeException("Error long type " + name);
	}

	public String getName() {
		return m_name;
	}

	public static ProblemLongType findByMessageType(String type) {
		for (ProblemLongType longType : values()) {
			if (longType.checkLongType(type)) {
				return longType;
			}
		}

		return null;
	}

	protected abstract boolean checkLongType(String type);

	public int getThreshold() {
		return m_threshold;
	}

}
