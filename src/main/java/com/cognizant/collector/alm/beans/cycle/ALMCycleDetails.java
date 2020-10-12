package com.cognizant.collector.alm.beans.cycle;

import com.cognizant.collector.alm.util.CycleDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = CycleDetailsDeserializer.class)
public class ALMCycleDetails {
    private List<Cycle> cycles;
    private int totalResults;
}
