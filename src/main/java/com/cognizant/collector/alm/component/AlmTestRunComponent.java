package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.beans.test.ALMTestRunDetails;
import com.cognizant.collector.alm.beans.test.TestRun;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.CycleService;
import com.cognizant.collector.alm.service.TestRunService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.*;

import static com.cognizant.collector.alm.constants.Constant.*;

@Component
@Slf4j
@AllArgsConstructor
public class AlmTestRunComponent {
    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    TestRunService testRunService;
    CycleService cycleService;

    public void getTestRunDetails(String domainName, String projectName){
        Map<String, String> map = new HashMap<>();
        int resultPerPage = RESULTS_PER_PAGE;
        int startAt = PAGE_STARTS_AT;
        boolean isCompleted = false;
        Date lastUpdatedDate = testRunService.getLastUpdatedDate(domainName, projectName);
        String queryParamString = utilComponent.getLastUpdateQueryParamString(lastUpdatedDate);
        map.put(QUERY, queryParamString);
        map.put(PAGE_SIZE, String.valueOf(resultPerPage));
        do {
            map.put(START_INDEX, String.valueOf(startAt));
            log.info("start-index:{}, page-size:{}, QueryString:{}", startAt, resultPerPage, queryParamString);
            ALMTestRunDetails almTestRunDetails = getTestRunDetails(domainName, projectName, map);

            int totalResults = almTestRunDetails.getTotalResults();
            if (totalResults == 0) {
                isCompleted = true;
            } else {
                saveTestRunInDB(almTestRunDetails, domainName, projectName);
                startAt += resultPerPage;
                if (totalResults < startAt) {
                    isCompleted = true;
                }
                log.info("TestRuns count : {}", totalResults);
            }
        } while (!isCompleted);
    }

    private List<TestRun> saveTestRunInDB(ALMTestRunDetails almTestRunDetails, String domainName, String projectName) {
        List<TestRun> testRuns = almTestRunDetails.getTestRuns();
        testRuns.forEach(testRun -> {
            testRun.setDomainName(domainName);
            testRun.setProjectName(projectName);
            if (StringUtils.hasText(testRun.getAssignedCycleId())) {
                Optional<Cycle> optionalCycle = cycleService.getByProjectNameAndCycleId(projectName, testRun.getAssignedCycleId());
                optionalCycle.ifPresent(cycle -> testRun.setReleaseId(cycle.getReleaseId()));
            }
            Optional<TestRun> optional = testRunService.getByProjectNameAndTestRunId(projectName, testRun.getTestRunId());
            optional.ifPresent(run -> testRun.setId(run.getId()));
        });
        return testRunService.addAll(testRuns);
    }

    private ALMTestRunDetails getTestRunDetails(String domainName, String projectName, Map<String, String> map) {
        return client.getRuns(authComponent.getCookies(), domainName, projectName, map);
    }
}
