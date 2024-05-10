
/*
 *    © [2021] Cognizant. All rights reserved.
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http:www.apache.orglicensesLICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.cognizant.collector.alm.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.cognizant.collector.alm.constants.Constant.*;

/*
 * CommonUtilComponent
 *
 * @author Cognizant
 */

@Component
@Slf4j
public class CommonUtilComponent {

    static String almCollectionName;

    public static String getAlmCollectionName() {
        return almCollectionName;
    }

    @Value("${spring.data.mongodb.collection.assets}")
    public void setAlmCollectionName(String almCollectionName) {
        CommonUtilComponent.almCollectionName = SOURCE+almCollectionName;
    }

    public String parseDateToString(Date date) {
        if (null == date) return "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getDefault());
        return sdf.format(date);
    }

    private List<String> getLastUpdatedQueryParam(Date lastUpdatedDate) {
        List<String> queryParams = new ArrayList<>();
        if (!StringUtils.isEmpty(lastUpdatedDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(lastUpdatedDate);
            calendar.add(Calendar.MINUTE, 1);
            String updatedDateString = parseDateToString(calendar.getTime());
            queryParams.add(String.format(QUERY_LAST_MODIFIED_GTE, updatedDateString));
        }

        Calendar now = Calendar.getInstance();
        now.add(Calendar.MINUTE, -1);
        String nowDateSting = parseDateToString(now.getTime());
        queryParams.add(String.format(QUERY_LAST_MODIFIED_LT, nowDateSting));
        return queryParams;
    }

    public String getLastUpdateQueryParamString(Date lastUpdatedDate) {
        List<String> queryParams = new ArrayList<>();
        queryParams.addAll(getLastUpdatedQueryParam(lastUpdatedDate));
        String queryString = String.format("{%s}", queryParams.stream().collect(Collectors.joining(";")));
        log.info("TestRun Query string : " + queryString);
        return queryString;
    }
}
