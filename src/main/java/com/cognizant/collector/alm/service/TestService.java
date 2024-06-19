
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

package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.test.Test;
import com.cognizant.collector.alm.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
 * TestService
 *
 * @author Cognizant
 */

@Service
@Slf4j
public class TestService {
    @Autowired
    TestRepository repository;

    public List<Test> getAll(){
        return repository.findAll();
    }

    public Optional<Test> getByProjectNameAndTestId(String projectName, String testId){
        return repository.findByProjectNameAndTestId(projectName, testId);
    }

    public long deleteByDomainNameAndProjectNameAndTestId(String domainName, String projectName, String testId) {
        return repository.deleteByDomainNameAndProjectNameAndTestId(domainName, projectName, testId);
    }

    public Test add(Test test){
        return repository.save(test);
    }

    public List<Test> addAll(List<Test> tests){
        return repository.saveAll(tests);
    }

    public Date getLastUpdatedDate(String domainName, String projectName){
        Optional<Test> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
