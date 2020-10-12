package com.cognizant.collector.alm.beans.requirement;

import com.cognizant.collector.alm.util.RequirementTypeDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = RequirementTypeDetailsDeserializer.class)
public class ALMRequirementTypeDetails {
    private List<RequirementType> types;
}
