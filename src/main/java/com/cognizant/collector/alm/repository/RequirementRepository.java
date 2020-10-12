package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.requirement.Requirement;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RequirementRepository extends MongoRepository<Requirement, String> {
    Optional<Requirement> findByProjectNameAndRequirementId(String projectName, String requirementId);

    Optional<Requirement> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
