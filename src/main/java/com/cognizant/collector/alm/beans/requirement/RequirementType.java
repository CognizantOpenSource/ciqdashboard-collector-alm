package com.cognizant.collector.alm.beans.requirement;

import lombok.Data;

@Data
public class RequirementType {
    private String name;
    private String id;
    private String hasDirectCoverage;
    private String riskAnalysisType;
    private String defaultChildTypeId;
    private String isDocumentRoot;

}
