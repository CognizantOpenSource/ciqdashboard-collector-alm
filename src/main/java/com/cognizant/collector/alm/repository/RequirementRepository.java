
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

import com.cognizant.collector.alm.beans.requirement.Requirement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/*
 * RequirementRepository
 *
 * @author Cognizant
 */

public interface RequirementRepository extends MongoRepository<Requirement, String> {
    Optional<Requirement> findByProjectNameAndRequirementId(String projectName, String requirementId);
    long deleteByDomainNameAndProjectNameAndRequirementId(String domainName, String projectName, String requirementId);
    Optional<Requirement> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
