package com.cognizant.collector.alm.beans.audit;

import com.cognizant.collector.alm.util.DeleteDetailsDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import java.util.List;

@Data
@JsonDeserialize(using = DeleteDetailsDeserializer.class)
public class ALMDeleteDetails {

    private List<Delete> deleteComponents ;
    private int totalResults;

}
