package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.cycle.ALMCycleDetails;
import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.CycleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cognizant.collector.alm.constants.Constant.*;

@Component
@Slf4j
@AllArgsConstructor
public class AlmCycleComponent {
    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    CycleService cycleService;

    public void getCycles(String domainName, String projectName) {
        authComponent.refreshALM();
        log.info("Getting Cycles : DomainName:{}, ProjectName:{}", domainName, projectName);
        getCyclesDetails(domainName, projectName);
    }

    private void getCyclesDetails(String domainName, String projectName) {
        Map<String, String> map = new HashMap<>();
        int resultPerPage = RESULTS_PER_PAGE;
        int startAt = PAGE_STARTS_AT;
        boolean isCompleted = false;
        Date lastUpdatedDate = cycleService.getLastUpdatedDate(domainName, projectName);
        String queryParamString = utilComponent.getLastUpdateQueryParamString(lastUpdatedDate);
        map.put(QUERY, queryParamString);
        map.put(PAGE_SIZE, String.valueOf(resultPerPage));
        do {
            map.put(START_INDEX, String.valueOf(startAt));
            log.info("start-index:{}, page-size:{}, QueryString:{}", startAt, resultPerPage, queryParamString);
            ALMCycleDetails almCycleDetails = getCycleDetails(domainName, projectName, map);

            int totalResults = almCycleDetails.getTotalResults();
            if (totalResults == 0) {
                isCompleted = true;
            } else {
                saveCyclesInDB(almCycleDetails, domainName, projectName);
                startAt += resultPerPage;
                if (totalResults < startAt) {
                    isCompleted = true;
                }
                log.info("Cycles count : {}", totalResults);
            }
        } while (!isCompleted);
    }

    private List<Cycle> saveCyclesInDB(ALMCycleDetails almCycleDetails, final String domainName, final String projectName) {
        List<Cycle> cycles = almCycleDetails.getCycles();
        cycles.forEach(cycle -> {
            cycle.setDomainName(domainName);
            cycle.setProjectName(projectName);
            Optional<Cycle> optional = cycleService.getByProjectNameAndCycleId(projectName, cycle.getCycleId());
            if (optional.isPresent()) cycle.setId(optional.get().getId());
        });
        return cycleService.addAll(cycles);
    }

    private ALMCycleDetails getCycleDetails(String domainName, String projectName, Map<String, String> map) {
        return client.getCycles(authComponent.getCookies(), domainName, projectName, map);
    }
}
