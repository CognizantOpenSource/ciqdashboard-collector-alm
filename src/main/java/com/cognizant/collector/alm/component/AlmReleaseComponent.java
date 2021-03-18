
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

import com.cognizant.collector.alm.beans.release.ALMReleaseDetails;
import com.cognizant.collector.alm.beans.release.Release;
import com.cognizant.collector.alm.client.AlmClient;
import com.cognizant.collector.alm.service.ReleaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;

import static com.cognizant.collector.alm.constants.Constant.*;

/*
 * AlmReleaseComponent
 *
 * @author Cognizant
 */

@Component
@Slf4j
@AllArgsConstructor
public class AlmReleaseComponent {
    AlmClient client;
    AlmAuthComponent authComponent;
    CommonUtilComponent utilComponent;
    ReleaseService releaseService;

    public void getReleaseDetails(String domainName, String projectName) {
        Map<String, String> map = new HashMap<>();
        int resultPerPage = RESULTS_PER_PAGE;
        int startAt = PAGE_STARTS_AT;
        boolean isCompleted = false;
        Date lastUpdatedDate = releaseService.getLastUpdatedDate(domainName, projectName);
        String queryParamString = utilComponent.getLastUpdateQueryParamString(lastUpdatedDate);
        map.put(QUERY, queryParamString);
        map.put(PAGE_SIZE, String.valueOf(resultPerPage));
        do {
            map.put(START_INDEX, String.valueOf(startAt));
            log.info("start-index:{}, page-size:{}, QueryString:{}", startAt, resultPerPage, queryParamString);
            ALMReleaseDetails releaseDetails = getReleaseDetails(domainName, projectName, map);

            int totalResults = releaseDetails.getTotalResults();
            if (totalResults == 0) {
                isCompleted = true;
            } else {
                saveInDB(releaseDetails, domainName, projectName);
                startAt += resultPerPage;
                if (totalResults < startAt) {
                    isCompleted = true;
                }
                log.info("Requirements count : {}", totalResults);
            }
        } while (!isCompleted);

    }

    private ALMReleaseDetails getReleaseDetails(String domainName, String projectName, Map<String, String> map) {
        return client.getReleases(authComponent.getCookies(), domainName, projectName, map);
    }

    public List<Release> saveInDB(ALMReleaseDetails releaseDetails, String domainName, String projectName) {
        List<Release> releases = releaseDetails.getReleases();
        if (CollectionUtils.isEmpty(releases)) return new ArrayList<>();

        log.info("DomainName: {}, ProjectName: {}, ReleasesCount: {}", domainName, projectName, releases.size());
        releases.forEach(release -> {
            release.setDomainName(domainName);
            release.setProjectName(projectName);
            Optional<Release> optional = releaseService.getByProjectNameAndReleaseId(projectName, release.getReleaseId());
            if (optional.isPresent()) release.setId(optional.get().getId());
            releaseService.add(release);
        });
        return releases;
    }
}
