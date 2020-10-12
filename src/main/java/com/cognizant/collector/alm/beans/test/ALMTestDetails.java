package com.cognizant.collector.alm.beans.test;

import com.cognizant.collector.alm.util.TestDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = TestDetailsDeserializer.class)
public class ALMTestDetails {
    private List<Test> tests;
    private int totalResults;
}
