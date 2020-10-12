package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.release.ALMReleaseDetails;
import com.cognizant.collector.alm.beans.release.Release;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReleaseDetailsDeserializer extends JsonDeserializer<ALMReleaseDetails> {
    @Override
    public ALMReleaseDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        ALMReleaseDetails releaseDetails = new ALMReleaseDetails();
        List<Release> releases = new ArrayList<>();

        ArrayNode entities = (ArrayNode) jsonNode.get("entities");
        int totalResults = jsonNode.get("TotalResults").asInt();
        if (entities != null && entities.size() > 0) {
            entities.forEach(entityNode -> {
                ArrayNode fields = (ArrayNode) entityNode.get("Fields");
                if (fields != null && fields.size() > 0) {
                    Release release = getRelease(fields);
                    releases.add(release);
                }
            });
        }

        releaseDetails.setReleases(releases);
        releaseDetails.setTotalResults(totalResults);
        return releaseDetails;
    }

    private Release getRelease(ArrayNode fields){
        Release release = new Release();
        fields.forEach(fieldNode -> {
            String name = fieldNode.get("Name").asText();
            ArrayNode values = (ArrayNode) fieldNode.get("values");
            if (values != null && values.size() > 0) {
                JsonNode valueNode = values.get(0);
                if (valueNode.has("value")) {
                    String value = valueNode.get("value").asText();
                    switch (name) {
                        case "id":
                            release.setReleaseId(value);
                            break;
                        case "name":
                            release.setReleaseName(value);
                            break;
                        case "description":
                            release.setDescription(value);
                            break;
                        case "parent-id":
                            release.setParentId(value);
                            break;
                        case "start-date":
                            release.setStartDate(Util.getDateFromString(value));
                            break;
                        case "end-date":
                            release.setEndDate(Util.getDateFromString(value));
                            break;
                        case "last-modified":
                            release.setLastModified(Util.getDateFromString(value));
                            break;
                        case "req-count":
                            release.setReqCount(Integer.parseInt(value));
                            break;
                        case "ver-stamp":
                            release.setVerStamp(value);
                            break;
                        case "scope-items-count":
                            release.setScopeItemsCount(Integer.parseInt(value));
                            break;
                        case "milestones-count":
                            release.setMilestonesCount(Integer.parseInt(value));
                            break;
                        case "has-attachments":
                        default:
                            break;
                    }
                }
            }
        });
        return release;
    }

}
