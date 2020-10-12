package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.test.Test;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TestRepository extends MongoRepository<Test, String> {
    Optional<Test> findByProjectNameAndTestId(String projectName, String testId);

    Optional<Test> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
