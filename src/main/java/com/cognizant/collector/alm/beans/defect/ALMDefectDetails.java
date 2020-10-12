package com.cognizant.collector.alm.beans.defect;

import com.cognizant.collector.alm.util.DefectDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = DefectDetailsDeserializer.class)
public class ALMDefectDetails {
    private List<Defect> defects;
    private int totalResults;
}
