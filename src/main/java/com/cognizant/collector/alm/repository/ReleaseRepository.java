package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.defect.Defect;
import com.cognizant.collector.alm.beans.release.Release;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ReleaseRepository extends MongoRepository<Release, String> {
    Optional<Release> findByProjectNameAndReleaseId(String projectName, String releaseId);

    Optional<Defect> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
