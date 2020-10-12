package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.requirement.Requirement;
import com.cognizant.collector.alm.repository.RequirementRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RequirementService {
    @Autowired
    RequirementRepository repository;

    public List<Requirement> getAll(){
        return repository.findAll();
    }

    public Optional<Requirement> getByProjectNameAndRequirementId(String projectName, String requirementId){
        return repository.findByProjectNameAndRequirementId(projectName, requirementId);
    }

    public Requirement add(Requirement requirement){
        return repository.save(requirement);
    }

    public List<Requirement> addAll(List<Requirement> requirements){
        return repository.saveAll(requirements);
    }

    public Date getLastUpdatedDate(String domainName, String projectName){
        Optional<Requirement> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
