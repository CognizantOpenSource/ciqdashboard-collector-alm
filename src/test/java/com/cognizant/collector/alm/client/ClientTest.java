package com.cognizant.collector.alm.client;

import com.cognizant.collector.alm.beans.cycle.ALMCycleDetails;
import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.beans.defect.ALMDefectDetails;
import com.cognizant.collector.alm.beans.domain.ALMDomainDetails;
import com.cognizant.collector.alm.beans.project.ALMProjectDetails;
import com.cognizant.collector.alm.beans.release.ALMReleaseDetails;
import com.cognizant.collector.alm.beans.requirement.ALMRequirementDetails;
import com.cognizant.collector.alm.beans.requirement.ALMRequirementTypeDetails;
import com.cognizant.collector.alm.beans.test.ALMTestDetails;
import com.cognizant.collector.alm.beans.test.ALMTestRunDetails;
import com.cognizant.collector.alm.component.AlmAuthComponent;
import com.cognizant.collector.alm.component.AlmComponent;
import com.cognizant.collector.alm.repository.CycleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Optional;

@SpringBootTest
class ClientTest {

    @Autowired
    AlmComponent component;
    @Autowired
    AlmAuthComponent authComponent;
    @Autowired
    AlmClient almClient;
    @Autowired
    CycleRepository cycleRepository;

    @org.junit.jupiter.api.Test
    void getTests() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD";
        String projectName = "ciqDashboard_final";
        ALMTestDetails tests = almClient.getTests(authComponent.getCookies(), domainName, projectName, new HashMap<>());
        Assertions.assertNotNull(tests);
    }

    @org.junit.jupiter.api.Test
    void getTestRuns() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD";
        String projectName = "ciqDashboard_final";
        ALMTestRunDetails runs = almClient.getRuns(authComponent.getCookies(), domainName, projectName, new HashMap<>());
        Assertions.assertNotNull(runs);
    }

    @org.junit.jupiter.api.Test
    void getDefects() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD";
        String projectName = "ciqDashboard_final";
        ALMDefectDetails defects = almClient.getDefects(authComponent.getCookies(), domainName, projectName, new HashMap<>());
        Assertions.assertNotNull(defects);
    }

    @Test
    void getCycleDetails() {
        Optional<Cycle> optional = cycleRepository.findByProjectNameAndCycleId("DEFAULT", "1001");
        Assertions.assertNotNull(optional);
    }

    @Test
    void getCycles() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD";
        String projectName = "ciqDashboard_final";
        ALMCycleDetails requirements = almClient.getCycles(authComponent.getCookies(), domainName, projectName, new HashMap<>());
        Assertions.assertNotNull(requirements);
    }

    @org.junit.jupiter.api.Test
    void getRequirements() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD_DUMMY";
        String projectName = "dummy_proj_1";
        ALMRequirementDetails requirements = almClient.getRequirements(authComponent.getCookies(), domainName, projectName, new HashMap<>());
        Assertions.assertNotNull(requirements);
    }

    @org.junit.jupiter.api.Test
    void getRequirementType() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD_DUMMY";
        String projectName = "dummy_proj_3";
        ALMRequirementTypeDetails typeDetails = almClient.getRequirementTypes(authComponent.getCookies(), domainName, projectName);
        Assertions.assertNotNull(typeDetails);
    }

    @org.junit.jupiter.api.Test
    void getReleases() {
        authComponent.loginALM();
        String domainName = "CIQDASHBOARD_DUMMY";
        String projectName = "dummy_proj_3";
        ALMReleaseDetails releaseDetails = almClient.getReleases(authComponent.getCookies(), domainName, projectName, new HashMap<>());
        Assertions.assertNotNull(releaseDetails);
    }

    @org.junit.jupiter.api.Test
    void getDomains() {
        authComponent.loginALM();
        ALMDomainDetails domainDetails = almClient.getDomains(authComponent.getCookies());
        domainDetails.getDomains().forEach(domain -> {
            ALMProjectDetails projects = almClient.getProjects(authComponent.getCookies(), domain.getName());
            System.out.println(projects);
            authComponent.refreshALM();
        });
        Assertions.assertNotNull(almClient);
    }

    @org.junit.jupiter.api.Test
    void clientTest() {
        component.updateAlmDetails();
        Assertions.assertNotNull(component);
    }
}
