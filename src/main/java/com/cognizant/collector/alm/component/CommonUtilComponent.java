
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

    static String almCycleCollectionName;
    static String almDefectCollectionName;
    static String almReleaseCollectionName;
    static String almRequirementCollectionName;
    static String almTestCollectionName;
    static String almRunCollectionName;

    public static String getAlmCycleCollectionName() {
        return almCycleCollectionName;
    }

    @Value("${spring.data.mongodb.collection.cycle}")
    public void setAlmCycleCollectionName(String almCycleCollectionName) {
        CommonUtilComponent.almCycleCollectionName = SOURCE+almCycleCollectionName;
    }

    public static String getAlmDefectCollectionName() {
        return almDefectCollectionName;
    }

    @Value("${spring.data.mongodb.collection.defects}")
    public void setAlmDefectCollectionName(String almDefectCollectionName) {
        CommonUtilComponent.almDefectCollectionName = SOURCE+almDefectCollectionName;
    }

    public static String getAlmReleaseCollectionName() {
        return almReleaseCollectionName;
    }

    @Value("${spring.data.mongodb.collection.release}")
    public void setAlmReleaseCollectionName(String almReleaseCollectionName) {
        CommonUtilComponent.almReleaseCollectionName = SOURCE+almReleaseCollectionName;
    }

    public static String getAlmRequirementCollectionName() {
        return almRequirementCollectionName;
    }

    @Value("${spring.data.mongodb.collection.requirements}")
    public void setAlmRequirementCollectionName(String almRequirementCollectionName) {
        CommonUtilComponent.almRequirementCollectionName = SOURCE+almRequirementCollectionName;
    }

    public static String getAlmTestCollectionName() {
        return almTestCollectionName;
    }

    @Value("${spring.data.mongodb.collection.test}")
    public void setAlmTestCollectionName(String almTestCollectionName) {
        CommonUtilComponent.almTestCollectionName = SOURCE+almTestCollectionName;
    }

    public static String getAlmRunCollectionName() {
        return almRunCollectionName;
    }

    @Value("${spring.data.mongodb.collection.runs}")
    public void setAlmRunCollectionName(String almRunCollectionName) {
        CommonUtilComponent.almRunCollectionName = SOURCE + almRunCollectionName;
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
