package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface CycleRepository extends MongoRepository<Cycle, String> {
    Optional<Cycle> findByProjectNameAndCycleId(String projectName, String cycleId);

    Optional<Cycle> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
