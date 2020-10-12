package com.cognizant.collector.alm.service;

import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.cognizant.collector.alm.repository.CycleRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CycleService {
    @Autowired
    CycleRepository repository;

    public List<Cycle> getAll() {
        return repository.findAll();
    }

    public Optional<Cycle> getByProjectNameAndCycleId(String projectName, String cycleId) {
        return repository.findByProjectNameAndCycleId(projectName, cycleId);
    }

    public Cycle add(Cycle cycle) {
        return repository.save(cycle);
    }

    public List<Cycle> addAll(List<Cycle> cycles) {
        return repository.saveAll(cycles);
    }

    public Date getLastUpdatedDate(String domainName, String projectName) {
        Optional<Cycle> optional = repository.findFirstByDomainNameAndProjectNameOrderByLastModifiedDesc(domainName, projectName);
        return optional.isPresent() ? optional.get().getLastModified() : null;
    }
}
