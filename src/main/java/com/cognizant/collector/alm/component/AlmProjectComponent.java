
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

import com.cognizant.collector.alm.beans.domain.Domain;
import com.cognizant.collector.alm.beans.project.Project;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/*
 * AlmProjectComponent
 *
 * @author Cognizant
 */

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
