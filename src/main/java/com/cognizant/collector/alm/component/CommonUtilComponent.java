package com.cognizant.collector.alm.component;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.cognizant.collector.alm.constants.Constant.QUERY_LAST_MODIFIED_GTE;
import static com.cognizant.collector.alm.constants.Constant.QUERY_LAST_MODIFIED_LT;

@Component
@Slf4j
public class CommonUtilComponent {

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
