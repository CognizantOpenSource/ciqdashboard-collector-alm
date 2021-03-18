
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

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.repository.CycleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/*
 * CycleService
 *
 * @author Cognizant
 */

@Service
@Slf4j
public class CycleService {
    @Autowired
    CycleRepository repository;

    public List<Cycle> getAll() {
        return repository.findAll();
    }

    public Optional<Cycle> getByProjectNameAndCycleId(String projectName, String cycleId) {
        return repository.findByProjectNameAndCycleId(projectName, cycleId);
    }

    public Cycle add(Cycle cycle) {
        return repository.save(cycle);
    }

    public List<Cycle> addAll(List<Cycle> cycles) {
        return repository.saveAll(cycles);
    }

    public Date getLastUpdatedDate(String domainName, String projectName) {
        Optional<Cycle> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
