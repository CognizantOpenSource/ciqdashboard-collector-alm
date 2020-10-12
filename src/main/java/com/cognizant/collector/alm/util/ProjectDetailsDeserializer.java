package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.project.ALMProjectDetails;
import com.cognizant.collector.alm.beans.project.Project;
import com.cognizant.collector.alm.config.JsonUtil;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProjectDetailsDeserializer extends JsonDeserializer<ALMProjectDetails> {
    private static final String PROJECT = "Project";
    private static final String PROJECTS = "Projects";

    @Override
    public ALMProjectDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        ALMProjectDetails details = new ALMProjectDetails();
        List<Project> projects = new ArrayList<>();
        JsonNode projectsNode = jsonNode.get(PROJECTS);
        if (projectsNode.has(PROJECT)) {
            JsonNode project = projectsNode.get(PROJECT);
            if (project instanceof ArrayNode)
                projects = (List<Project>) JsonUtil.getJsonClassList(projectsNode, PROJECT, Project.class);
            else
                projects = Arrays.asList((Project) JsonUtil.getJsonClassObject(projectsNode, PROJECT, Project.class));
        }
        details.setProjects(projects);

        return details;
    }
}
