package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.test.Test;
import com.cognizant.collector.alm.repository.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestService {
    @Autowired
    TestRepository repository;

    public List<Test> getAll(){
        return repository.findAll();
    }

    public Optional<Test> getByProjectNameAndTestId(String projectName, String testId){
        return repository.findByProjectNameAndTestId(projectName, testId);
    }

    public Test add(Test test){
        return repository.save(test);
    }

    public List<Test> addAll(List<Test> tests){
        return repository.saveAll(tests);
    }

    public Date getLastUpdatedDate(String domainName, String projectName){
        Optional<Test> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
