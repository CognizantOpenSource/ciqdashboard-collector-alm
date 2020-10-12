package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.requirement.ALMRequirementTypeDetails;
import com.cognizant.collector.alm.beans.requirement.RequirementType;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RequirementTypeDetailsDeserializer extends JsonDeserializer<ALMRequirementTypeDetails> {
    @Override
    public ALMRequirementTypeDetails deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        ALMRequirementTypeDetails typeDetails = new ALMRequirementTypeDetails();
        List<RequirementType> types = new ArrayList<>();
        ArrayNode typesNode = (ArrayNode) jsonNode.get("types");
        typesNode.forEach(typeNode -> {
            RequirementType type = new RequirementType();
            type.setId(typeNode.get("id").asText());
            type.setName(typeNode.get("name").asText());
            type.setHasDirectCoverage(typeNode.get("has-direct-coverage").asText());
            type.setRiskAnalysisType(typeNode.get("risk-analysis-type").asText());
            type.setDefaultChildTypeId(typeNode.get("default-child-type-id").asText());
            type.setIsDocumentRoot(typeNode.get("is-document-root").asText());
            types.add(type);
        });

        typeDetails.setTypes(types);
        return typeDetails;
    }
}
