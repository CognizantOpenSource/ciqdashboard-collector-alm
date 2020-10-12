package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.requirement.ALMRequirementDetails;
import com.cognizant.collector.alm.beans.requirement.ALMRequirementTypeDetails;
import com.cognizant.collector.alm.beans.requirement.Requirement;
import com.cognizant.collector.alm.beans.requirement.RequirementType;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.RequirementService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

import static com.cognizant.collector.alm.constants.Constant.*;

@Component
@Slf4j
@AllArgsConstructor
public class AlmRequirementComponent {
    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    RequirementService requirementService;

    public void getRequirementDetails(String domainName, String projectName) {
        ALMRequirementTypeDetails requirementTypes = getRequirementTypes(domainName, projectName);
        Map<String, String> types = requirementTypes.getTypes().stream().collect(Collectors.toMap(RequirementType::getId, RequirementType::getName));
        authComponent.refreshALM();

        log.info("Getting Requirements: DomainName:{}, ProjectName:{}", domainName, projectName);
        getRequirements(domainName, projectName, types);

    }

    private void getRequirements(String domainName, String projectName, Map<String, String> types) {
        Map<String, String> map = new HashMap<>();
        int resultPerPage = RESULTS_PER_PAGE;
        int startAt = PAGE_STARTS_AT;
        boolean isCompleted = false;
        Date lastUpdatedDate = requirementService.getLastUpdatedDate(domainName, projectName);
        String queryParamString = utilComponent.getLastUpdateQueryParamString(lastUpdatedDate);
        map.put(QUERY, queryParamString);
        map.put(PAGE_SIZE, String.valueOf(resultPerPage));
        do {
            map.put(START_INDEX, String.valueOf(startAt));
            log.info("start-index:{}, page-size:{}, QueryString:{}", startAt, resultPerPage, queryParamString);
            ALMRequirementDetails requirementDetails = getRequirementDetails(domainName, projectName, map);

            int totalResults = requirementDetails.getTotalResults();
            if (totalResults == 0) {
                isCompleted = true;
            } else {
                saveRequirementsInDB(requirementDetails, domainName, projectName, types);
                startAt += resultPerPage;
                if (totalResults < startAt) {
                    isCompleted = true;
                }
                log.info("Requirements count : {}", totalResults);
            }
        } while (!isCompleted);
    }

    private List<Requirement> saveRequirementsInDB(ALMRequirementDetails requirementDetails,
                                                   final String domainName, final String projectName,
                                                   Map<String, String> types) {
        List<Requirement> requirements = requirementDetails.getRequirements();
        requirements.forEach(requirement -> {
            requirement.setDomainName(domainName);
            requirement.setProjectName(projectName);
            String type = types.get(requirement.getRequirementTypeId());
            Optional<Requirement> optional = requirementService.getByProjectNameAndRequirementId(projectName, requirement.getRequirementId());
            if (optional.isPresent()) requirement.setId(optional.get().getId());
            requirement.setRequirementTypeName(type);
        });
        return requirementService.addAll(requirements);
    }

    public ALMRequirementDetails getRequirementDetails(String domainName, String projectName, Map<String, String> requestHeaders) {
        return client.getRequirements(authComponent.getCookies(), domainName, projectName, requestHeaders);
    }

    public ALMRequirementTypeDetails getRequirementTypes(String domainName, String projectName) {
        return client.getRequirementTypes(authComponent.getCookies(), domainName, projectName);
    }
}
