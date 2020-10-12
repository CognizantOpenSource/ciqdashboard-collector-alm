package com.cognizant.collector.alm.repository;

import com.cognizant.collector.alm.beans.defect.Defect;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DefectRepository extends MongoRepository<Defect, String> {
    Optional<Defect> findByProjectNameAndDefectId(String projectName, String defectId);

    Optional<Defect> findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(String domainName, String projectName);
}
