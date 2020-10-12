package com.cognizant.collector.alm.component;

import com.cognizant.collector.alm.beans.domain.Domain;
import com.cognizant.collector.alm.beans.project.Project;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class AlmProjectComponent {
    AlmReleaseComponent releaseComponent;
    AlmRequirementComponent requirementComponent;
    AlmCycleComponent cycleComponent;
    AlmDefectComponent defectComponent;
    AlmTestRunComponent testRunComponent;
    AlmTestComponent testComponent;


    public void getALMDetailsByProject(Domain domain, Project project){
        releaseComponent.getReleaseDetails(domain.getName(), project.getName());
        requirementComponent.getRequirementDetails(domain.getName(), project.getName());
        cycleComponent.getCycles(domain.getName(), project.getName());
        defectComponent.getDefectDetails(domain.getName(), project.getName());
        testRunComponent.getTestRunDetails(domain.getName(), project.getName());
        testComponent.getTestDetails(domain.getName(), project.getName());
    }
}
