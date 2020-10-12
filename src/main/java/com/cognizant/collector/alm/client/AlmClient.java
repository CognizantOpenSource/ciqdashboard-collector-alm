package com.cognizant.collector.alm.client;

import com.cognizant.collector.alm.beans.cycle.ALMCycleDetails;
import com.cognizant.collector.alm.beans.defect.ALMDefectDetails;
import com.cognizant.collector.alm.beans.domain.ALMDomainDetails;
import com.cognizant.collector.alm.beans.project.ALMProjectDetails;
import com.cognizant.collector.alm.beans.release.ALMReleaseDetails;
import com.cognizant.collector.alm.beans.requirement.ALMRequirementDetails;
import com.cognizant.collector.alm.beans.requirement.ALMRequirementTypeDetails;
import com.cognizant.collector.alm.beans.test.ALMTestDetails;
import com.cognizant.collector.alm.beans.test.ALMTestRunDetails;
import feign.Response;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface AlmClient {

    /*SignIn and SignOut*/
    @GetMapping("/api/authentication/sign-in")
    Response signIn();

    @GetMapping("/api/authentication/sign_out")
    Response signOut(@RequestHeader("Cookie") String strCookie);

    /*Domains*/
    @GetMapping(value = "/rest/domains", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMDomainDetails getDomains(@RequestHeader("Cookie") String strCookie);

    /*Projects*/
    @GetMapping(value = "/rest/domains/{domainName}/projects", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMProjectDetails getProjects(@RequestHeader("Cookie") String strCookie, @PathVariable("domainName") String domainName);

    /*Releases*/
    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/releases", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMReleaseDetails getReleases(@RequestHeader("Cookie") String strCookie,
                                  @PathVariable("domainName") String domainName,
                                  @PathVariable("projectName") String projectName,
                                  @RequestParam Map<String, String> requestParams);

    /*Requirements*/
    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/customization/entities/requirement/types",
            produces = MediaType.APPLICATION_JSON_VALUE)
    ALMRequirementTypeDetails getRequirementTypes(@RequestHeader("Cookie") String strCookie,
                                                  @PathVariable("domainName") String domainName,
                                                  @PathVariable("projectName") String projectName);

    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/requirements", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMRequirementDetails getRequirements(@RequestHeader("Cookie") String strCookie, @PathVariable("domainName") String domainName,
                                          @PathVariable("projectName") String projectName, @RequestParam Map<String, String> requestParams);

    /*Cycles*/
    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/release-cycles?query={releaseId}", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMCycleDetails getCycles(@RequestHeader("Cookie") String strCookie,
                              @PathVariable("domainName") String domainName,
                              @PathVariable("projectName") String projectName,
                              @RequestParam Map<String, String> requestParams);

    /*Defects*/
    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/defects", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMDefectDetails getDefects(@RequestHeader("Cookie") String strCookie,
                                @PathVariable("domainName") String domainName,
                                @PathVariable("projectName") String projectName,
                                @RequestParam Map<String, String> requestParams);

    /*TestRuns*/
    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/runs", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMTestRunDetails getRuns(@RequestHeader("Cookie") String strCookie,
                              @PathVariable("domainName") String domainName,
                              @PathVariable("projectName") String projectName,
                              @RequestParam Map<String, String> requestParams);

    /*Tests*/
    @GetMapping(value = "/rest/domains/{domainName}/projects/{projectName}/tests", produces = MediaType.APPLICATION_JSON_VALUE)
    ALMTestDetails getTests(@RequestHeader("Cookie") String strCookie,
                            @PathVariable("domainName") String domainName,
                            @PathVariable("projectName") String projectName,
                            @RequestParam Map<String, String> requestParams);


}
