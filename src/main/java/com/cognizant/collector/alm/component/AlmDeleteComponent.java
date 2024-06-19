package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.audit.ALMDeleteDetails;
import com.cognizant.collector.alm.beans.defect.Defect;
import com.cognizant.collector.alm.beans.requirement.Requirement;
import com.cognizant.collector.alm.beans.test.*;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

import static com.cognizant.collector.alm.constants.Constant.PARENT_TYPES;

@Component
@Slf4j
@AllArgsConstructor
public class AlmDeleteComponent {

    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    TestService testService;
    RequirementService requirementService;
    DefectService defectService;
    TestRunService testRunService;

    public void removeDeletedDetails(String domainName, String projectName){

        PARENT_TYPES.forEach(parentType -> {

            ALMDeleteDetails deleteDetails = getDeleteComponents(domainName, projectName, "{parent-type["+parentType+"];parent-id[<>-1];action[='DELETE']}");

            if(parentType.equals("test")) {
                deleteDetails.getDeleteComponents().forEach(delete -> {

                    Optional<Test> test = testService.getByProjectNameAndTestId(projectName, delete.getParentId());

                    if(test.isPresent()){
                        testService.deleteByDomainNameAndProjectNameAndTestId(domainName, projectName, delete.getParentId());
                    }
                });
            }
            else if(parentType.equals("requirement")) {
                deleteDetails.getDeleteComponents().forEach(delete -> {

                    Optional<Requirement> requirement = requirementService.getByProjectNameAndRequirementId(projectName, delete.getParentId());

                    if(requirement.isPresent()){
                        requirementService.deleteByDomainNameAndProjectNameAndRequirementId(domainName, projectName, delete.getParentId());
                    }
                });
            }
            else if(parentType.equals("defect")) {
                deleteDetails.getDeleteComponents().forEach(delete -> {

                    Optional<Defect> defect = defectService.getByProjectNameAndDefectId(projectName, delete.getParentId());

                    if(defect.isPresent()){
                        defectService.deleteByDomainNameAndProjectNameAndDefectId(domainName, projectName, delete.getParentId());
                    }
                });
            }
            else if(parentType.equals("run")) {
                deleteDetails.getDeleteComponents().forEach(delete -> {

                    Optional<TestRun> testRun = testRunService.getByProjectNameAndTestRunId(projectName, delete.getParentId());

                    if(testRun.isPresent()){
                        testRunService.deleteByDomainNameAndProjectNameAndTestRunId(domainName, projectName, delete.getParentId());
                    }
                });
            }
        });
    }

    private ALMDeleteDetails getDeleteComponents(String domainName, String projectName, String query) {
        return client.getDeleteComponents(authComponent.getCookies(), domainName, projectName, query);
    }

}
