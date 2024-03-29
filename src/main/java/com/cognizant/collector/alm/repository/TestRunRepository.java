
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

package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.beans.test.TestRun;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/*
 * TestRunRepository
 *
 * @author Cognizant
 */

public interface TestRunRepository extends MongoRepository<TestRun, String> {
    Optional<TestRun> findByProjectNameAndTestRunId(String projectName, String testRunId);

    Optional<Cycle> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
