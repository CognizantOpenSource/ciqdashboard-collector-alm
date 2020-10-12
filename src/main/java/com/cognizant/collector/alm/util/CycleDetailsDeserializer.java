package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.cycle.ALMCycleDetails;
import com.cognizant.collector.alm.beans.cycle.Cycle;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CycleDetailsDeserializer extends JsonDeserializer<ALMCycleDetails> {
    @Override
    public ALMCycleDetails deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        ALMCycleDetails almCycleDetails = new ALMCycleDetails();
        List<Cycle> cycles = new ArrayList<>();

        ArrayNode entities = (ArrayNode) jsonNode.get("entities");
        int totalResults = jsonNode.get("TotalResults").asInt();
        if (entities != null && entities.size() > 0) {
            entities.forEach(entityNode -> {
                ArrayNode fields = (ArrayNode) entityNode.get("Fields");
                if (fields != null && fields.size() > 0) {
                    Cycle cycle = getCycle(fields);
                    cycles.add(cycle);
                }
            });
        }

        almCycleDetails.setCycles(cycles);
        almCycleDetails.setTotalResults(totalResults);
        return almCycleDetails;
    }

    private Cycle getCycle(ArrayNode fields){
        Cycle cycle = new Cycle();
        fields.forEach(fieldNode -> {
            String name = fieldNode.get("Name").asText();
            ArrayNode values = (ArrayNode) fieldNode.get("values");
            if (values != null && values.size() > 0) {
                JsonNode valueNode = values.get(0);
                if (valueNode.has("value")) {
                    String value = valueNode.get("value").asText();
                    switch (name) {
                        case "id":
                            cycle.setCycleId(value);
                            break;
                        case "name":
                            cycle.setCycleName(value);
                            break;
                        case "description":
                            cycle.setDescription(value);
                            break;
                        case "req-count":
                            cycle.setReqCount(Integer.parseInt(value));
                            break;
                        case "ver-stamp":
                            cycle.setVerStamp(value);
                            break;
                        case "cf-count":
                            cycle.setCfCount(Integer.parseInt(value));
                            break;
                        case "has-attachments":
                            cycle.setHasAttachments(value);
                            break;
                        case "parent-id":
                            cycle.setReleaseId(value);
                            break;
                        case "start-date":
                            cycle.setStartDate(Util.getDateFromString(value));
                            break;
                        case "end-date":
                            cycle.setEndDate(Util.getDateFromString(value));
                            break;
                        case "last-modified":
                            cycle.setLastModified(Util.getDateFromString(value));
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        return cycle;
    }
}
