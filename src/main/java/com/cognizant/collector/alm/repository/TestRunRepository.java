package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.beans.test.TestRun;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TestRunRepository extends MongoRepository<TestRun, String> {
    Optional<TestRun> findByProjectNameAndTestRunId(String projectName, String testRunId);

    Optional<Cycle> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
