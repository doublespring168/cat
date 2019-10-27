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
package com.dianping.cat.report.view;

import com.dianping.cat.report.ReportPage;
import com.dianping.cat.system.SystemPage;
import org.unidal.web.mvc.Page;

public class NavigationBar {
	public Page[] getSystemPages() {
		return new Page[] {

								SystemPage.CONFIG,

								SystemPage.LOGIN

		};
	}

	public Page[] getVisiblePages() {
		return new Page[] {

								ReportPage.BROWSER,

								ReportPage.APP,

								ReportPage.TRANSACTION,

								ReportPage.EVENT,

								ReportPage.PROBLEM,

								ReportPage.HEARTBEAT,

								ReportPage.CROSS,

								ReportPage.CACHE,

								ReportPage.DEPENDENCY,

								ReportPage.STATE,

								ReportPage.LOGVIEW

		};
	}
}
