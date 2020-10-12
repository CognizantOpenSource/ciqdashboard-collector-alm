package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.defect.ALMDefectDetails;
import com.cognizant.collector.alm.beans.defect.Defect;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.DefectService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;

import static com.cognizant.collector.alm.constants.Constant.*;

@Component
@Slf4j
@AllArgsConstructor
public class AlmDefectComponent {
    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    DefectService defectService;

    public void getDefectDetails(String domainName, String projectName) {
        authComponent.refreshALM();
        log.info("Getting Defects : DomainName:{}, ProjectName:{}", domainName, projectName);
        getDefectsDetails(domainName, projectName);
    }

    private void getDefectsDetails(String domainName, String projectName) {
        Map<String, String> map = new HashMap<>();
        int resultPerPage = RESULTS_PER_PAGE;
        int startAt = PAGE_STARTS_AT;
        boolean isCompleted = false;
        Date lastUpdatedDate = defectService.getLastUpdatedDate(domainName, projectName);
        String queryParamString = utilComponent.getLastUpdateQueryParamString(lastUpdatedDate);
        map.put(QUERY, queryParamString);
        map.put(PAGE_SIZE, String.valueOf(resultPerPage));
        do {
            map.put(START_INDEX, String.valueOf(startAt));
            log.info("start-index:{}, page-size:{}, QueryString:{}", startAt, resultPerPage, queryParamString);
            ALMDefectDetails defectDetails = getDefects(domainName, projectName, map);

            int totalResults = defectDetails.getTotalResults();
            if (totalResults == 0) {
                isCompleted = true;
            } else {
                saveDefectsInDB(defectDetails, domainName, projectName);
                startAt += resultPerPage;
                if (totalResults < startAt) {
                    isCompleted = true;
                }
                log.info("Cycles count : {}", totalResults);
            }
        } while (!isCompleted);
    }

    private List<Defect> saveDefectsInDB(ALMDefectDetails defectDetails, final String domainName, final String projectName) {
        List<Defect> defects = defectDetails.getDefects();
        defects.forEach(defect -> {
            defect.setDomainName(domainName);
            defect.setProjectName(projectName);
            Optional<Defect> optional = defectService.getByProjectNameAndDefectId(projectName, defect.getDefectId());
            if (optional.isPresent()) defect.setId(optional.get().getId());
        });
        return defectService.addAll(defects);
    }

    private ALMDefectDetails getDefects(String domainName, String projectName, Map<String, String> map) {
        return client.getDefects(authComponent.getCookies(), domainName, projectName, map);
    }
}
