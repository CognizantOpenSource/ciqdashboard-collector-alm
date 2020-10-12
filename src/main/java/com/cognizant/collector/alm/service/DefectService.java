package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.defect.Defect;
import com.cognizant.collector.alm.repository.DefectRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DefectService {
    @Autowired
    DefectRepository repository;

    public List<Defect> getAll(){
        return repository.findAll();
    }

    public Optional<Defect> getByProjectNameAndDefectId(String projectName, String defectId){
        return repository.findByProjectNameAndDefectId(projectName, defectId);
    }

    public Defect add(Defect defect){
        return repository.save(defect);
    }

    public List<Defect> addAll(List<Defect> defects){
        return repository.saveAll(defects);
    }

    public Date getLastUpdatedDate(String domainName, String projectName){
        Optional<Defect> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
