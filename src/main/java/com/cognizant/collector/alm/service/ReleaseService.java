package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.defect.Defect;
import com.cognizant.collector.alm.beans.release.Release;
import com.cognizant.collector.alm.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ReleaseService {
    @Autowired
    ReleaseRepository repository;

    public List<Release> getAll(){
        return repository.findAll();
    }

    public Optional<Release> getByProjectNameAndReleaseId(String projectName, String releaseId){
        return repository.findByProjectNameAndReleaseId(projectName, releaseId);
    }

    public Release add(Release release){
        return repository.save(release);
    }

    public List<Release> addAll(List<Release> releases){
        return repository.saveAll(releases);
    }

    public Date getLastUpdatedDate(String domainName, String projectName){
        Optional<Defect> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
