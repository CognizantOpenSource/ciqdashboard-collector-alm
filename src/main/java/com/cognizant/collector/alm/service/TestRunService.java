package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.beans.test.TestRun;
import com.cognizant.collector.alm.repository.TestRunRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TestRunService {
    @Autowired
    TestRunRepository repository;

    public List<TestRun> getAll(){
        return repository.findAll();
    }

    public Optional<TestRun> getByProjectNameAndTestRunId(String projectName, String testRunId){
        return repository.findByProjectNameAndTestRunId(projectName, testRunId);
    }

    public TestRun add(TestRun testRun){
        return repository.save(testRun);
    }

    public List<TestRun> addAll(List<TestRun> runs){
        return repository.saveAll(runs);
    }

    public Date getLastUpdatedDate(String domainName, String projectName){
        Optional<Cycle> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }

}
