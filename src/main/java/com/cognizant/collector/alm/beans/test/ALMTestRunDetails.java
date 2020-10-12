package com.cognizant.collector.alm.beans.test;

import com.cognizant.collector.alm.util.TestRunDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = TestRunDetailsDeserializer.class)
public class ALMTestRunDetails {
    private List<TestRun> testRuns;
    private int totalResults;
}
