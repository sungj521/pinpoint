/*
 * Copyright 2017 NAVER Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.navercorp.pinpoint.web.service.stat;

import com.navercorp.pinpoint.web.dao.ApplicationTransactionDao;
import com.navercorp.pinpoint.web.util.TimeWindow;
import com.navercorp.pinpoint.web.vo.stat.AggreJoinTransactionBo;
import com.navercorp.pinpoint.web.vo.stat.chart.StatChart;
import com.navercorp.pinpoint.web.vo.stat.chart.application.ApplicationTransactionChart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author minwoo.jung
 */
@Service
public class ApplicationTransactionService implements ApplicationStatChartService {

    private final ApplicationTransactionDao applicationTransactionDao;

    public ApplicationTransactionService(ApplicationTransactionDao applicationTransactionDao) {
        this.applicationTransactionDao = Objects.requireNonNull(applicationTransactionDao, "applicationTransactionDao");
    }

    @Override
    public StatChart selectApplicationChart(String applicationId, TimeWindow timeWindow) {
        Objects.requireNonNull(applicationId, "applicationId");
        Objects.requireNonNull(timeWindow, "timeWindow");

        List<AggreJoinTransactionBo> aggreJoinTransactionBoList = this.applicationTransactionDao.getApplicationStatList(applicationId, timeWindow);
        return new ApplicationTransactionChart(timeWindow, aggreJoinTransactionBoList);
    }
}
