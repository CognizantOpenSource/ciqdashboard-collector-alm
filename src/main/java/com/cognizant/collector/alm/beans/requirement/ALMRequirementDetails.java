package com.cognizant.collector.alm.beans.requirement;

import com.cognizant.collector.alm.util.RequirementDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = RequirementDetailsDeserializer.class)
public class ALMRequirementDetails {
    private List<Requirement> requirements ;
    private int totalResults;
}
