package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.test.ALMTestRunDetails;
import com.cognizant.collector.alm.beans.test.TestRun;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TestRunDetailsDeserializer extends JsonDeserializer<ALMTestRunDetails> {
    @Override
    public ALMTestRunDetails deserialize(JsonParser jsonParser, DeserializationContext context) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);
        ALMTestRunDetails testRunDetails = new ALMTestRunDetails();
        List<TestRun> testRuns = new ArrayList<>();

        ArrayNode entities = (ArrayNode) jsonNode.get("entities");
        int totalResults = jsonNode.get("TotalResults").asInt();
        if (entities != null && entities.size() > 0) {
            entities.forEach(entityNode -> {
                ArrayNode fields = (ArrayNode) entityNode.get("Fields");
                if (fields != null && fields.size() > 0) {
                    TestRun defect = getTestRun(fields);
                    testRuns.add(defect);
                }
            });
        }

        testRunDetails.setTestRuns(testRuns);
        testRunDetails.setTotalResults(totalResults);
        return testRunDetails;
    }

    private TestRun getTestRun(ArrayNode fields) {
        TestRun testRun = new TestRun();
        fields.forEach(fieldNode -> {
            String name = fieldNode.get("Name").asText();
            ArrayNode values = (ArrayNode) fieldNode.get("values");
            if (values != null && values.size() > 0) {
                JsonNode valueNode = values.get(0);
                if (valueNode.has("value")) {
                    String value = valueNode.get("value").asText();
                    String referenceValue = "";
                    if (valueNode.has("ReferenceValue")) referenceValue = valueNode.get("ReferenceValue").asText();
                    switch (name) {
                        case "id":
                            testRun.setTestRunId(value);
                            break;
                        case "name":
                            testRun.setTestRunName(value);
                            break;
                        case "duration":
                            testRun.setDuration(Long.parseLong(value));
                            break;
                        case "cycle":
                            testRun.setCycle(value);
                            break;
                        case "path":
                            testRun.setPath(value);
                            break;
                        case "attachment":
                            testRun.setAttachment(value);
                            break;
                        case "host":
                            testRun.setHost(value);
                            break;
                        case "state":
                            testRun.setState(value);
                            break;
                        case "test-description":
                            testRun.setTestDescription(value);
                            break;
                        case "owner":
                            testRun.setTester(value);
                            break;
                        case "comments":
                            testRun.setComments(value);
                            break;
                        case "status":
                            testRun.setStatus(value);
                            break;
                        case "assign-rcyc":
                            testRun.setAssignedCycleId(value);
                            testRun.setAssignedCycleName(referenceValue);
                            break;
                        case "cycle-name":
                            testRun.setTestSetName(value);
                            break;
                        case "os-config":
                            testRun.setOsConfig(value);
                            break;
                        case "test-id":
                            testRun.setTestId(value);
                            break;
                        case "test-name":
                            testRun.setTestName(value);
                            break;
                        case "has-linkage":
                            testRun.setHasLinkage(value);
                            break;
                        case "subtype-id":
                            testRun.setSubtypeId(value);
                            break;
                        case "cycle-id":
                            testRun.setCycleId(value);
                            break;
                        case "test-config-id":
                            testRun.setTestConfigId(value);
                            break;
                        case "test-instance":
                            testRun.setTestInstance(value);
                            break;
                        case "os-name":
                            testRun.setOsName(value);
                            break;
                        case "os-build":
                            testRun.setOsBuild(value);
                            break;
                        case "testcycl-name":
                            testRun.setTestCycleName(value);
                            break;
                        case "testcycl-id":
                            testRun.setTestCycleId(value);
                            break;
                        case "execution-date":
                            testRun.setExecutionDate(Util.getDateFromString(value));
                            break;
                        case "execution-time":
                            testRun.setExecutionTime(Util.getTimeFromString(value));
                            break;
                        case "last-modified":
                            testRun.setLastModified(Util.getDateFromString(value));
                            break;
                        default:
                            break;
                    }
                }
            }
        });
        return testRun;
    }
}
