package com.cognizant.collector.alm.util;

import com.cognizant.collector.alm.beans.domain.ALMDomainDetails;
import com.cognizant.collector.alm.beans.domain.Domain;
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

public class DomainDetailsDeserializer extends JsonDeserializer<ALMDomainDetails> {
    @Override
    public ALMDomainDetails deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode jsonNode = jsonParser.getCodec().readTree(jsonParser);

        ALMDomainDetails details = new ALMDomainDetails();
        List<Domain> domains = new ArrayList<>();
        JsonNode domainNodes = jsonNode.get("Domains");
        if (domainNodes.has("Domain")) {
            JsonNode domain = domainNodes.get("Domain");
            if (domain instanceof ArrayNode)
                domains = (List<Domain>) JsonUtil.getJsonClassList(domainNodes, "Domain", Domain.class);
            else
                domains = Arrays.asList((Domain) JsonUtil.getJsonClassObject(domainNodes, "Domain", Domain.class));
        }
        details.setDomains(domains);

        return details;
    }
}
