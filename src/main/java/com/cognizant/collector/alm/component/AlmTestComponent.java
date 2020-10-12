package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.test.ALMTestDetails;
import com.cognizant.collector.alm.beans.test.Test;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.TestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cognizant.collector.alm.constants.Constant.*;

@Component
@Slf4j
@AllArgsConstructor
public class AlmTestComponent {
    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    TestService testService;

    public void getTestDetails(String domainName, String projectName){
        Map<String, String> map = new HashMap<>();
        int resultPerPage = RESULTS_PER_PAGE;
        int startAt = PAGE_STARTS_AT;
        boolean isCompleted = false;
        Date lastUpdatedDate = testService.getLastUpdatedDate(domainName, projectName);
        String queryParamString = utilComponent.getLastUpdateQueryParamString(lastUpdatedDate);
        map.put(QUERY, queryParamString);
        map.put(PAGE_SIZE, String.valueOf(resultPerPage));
        do {
            map.put(START_INDEX, String.valueOf(startAt));
            log.info("start-index:{}, page-size:{}, QueryString:{}", startAt, resultPerPage, queryParamString);
            ALMTestDetails testDetails = getTestDetails(domainName, projectName, map);

            int totalResults = testDetails.getTotalResults();
            if (totalResults == 0) {
                isCompleted = true;
            } else {
                saveTestInDB(testDetails, domainName, projectName);
                startAt += resultPerPage;
                if (totalResults < startAt) {
                    isCompleted = true;
                }
                log.info("TestRuns count : {}", totalResults);
            }
        } while (!isCompleted);
    }

    private List<Test> saveTestInDB(ALMTestDetails almTestDetails, String domainName, String projectName) {
        List<Test> tests = almTestDetails.getTests();
        tests.forEach(test -> {
            test.setDomainName(domainName);
            test.setProjectName(projectName);
            Optional<Test> optional = testService.getByProjectNameAndTestId(projectName, test.getTestId());
            optional.ifPresent(run -> test.setId(run.getId()));
        });
        return testService.addAll(tests);
    }

    private ALMTestDetails getTestDetails(String domainName, String projectName, Map<String, String> map) {
        return client.getTests(authComponent.getCookies(), domainName, projectName, map);
    }
}
