
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

import com.cognizant.collector.alm.beans.domain.ALMDomainDetails;
import com.cognizant.collector.alm.beans.domain.Domain;
import com.cognizant.collector.alm.beans.project.ALMProjectDetails;
import com.cognizant.collector.alm.client.AlmClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * AlmComponent
 *
 * @author Cognizant
 */

@Component
@Slf4j
public class AlmComponent {

    @Autowired
    private AlmClient client;
    @Autowired
    AlmAuthComponent authComponent;
    @Autowired
    AlmProjectComponent projectComponent;


    public void updateAlmDetails() {
        authComponent.loginALM();
        log.info("Data Retrieval Starts !");
        getALMDetails();
        authComponent.logoutALM();
        log.info("Signed Out");
    }

    private void getALMDetails() {
        ALMDomainDetails domainDetails = getDomains();
        domainDetails.getDomains().forEach(domain -> {
            ALMProjectDetails details = getProjects(domain.getName());
            getALMDetailsByProject(domain, details);
        });

    }

    private void getALMDetailsByProject(Domain domain, ALMProjectDetails projectDetails) {
        projectDetails.getProjects().forEach(project -> projectComponent.getALMDetailsByProject(domain, project));
    }

    private ALMDomainDetails getDomains() {
        return client.getDomains(authComponent.getCookies());
    }

    private ALMProjectDetails getProjects(String domainName){
        return client.getProjects(authComponent.getCookies(), domainName);
    }


}
