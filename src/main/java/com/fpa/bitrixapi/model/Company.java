package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.node.ArrayNode;

import java.io.IOException;

public class Company {

    private Integer companyId;
    private String title;
    private String email;

    public Integer getCompanyId() {
        return companyId;
    }

    @JsonSetter("ID")
    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    @JsonSetter("TITLE")
    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    @JsonSetter("EMAIL")
    @JsonDeserialize(using = CustomCompanyMailDeserializer.class)
    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", title='" + title + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public static class CustomCompanyMailDeserializer extends StdDeserializer<String> {

        public CustomCompanyMailDeserializer(){
            this(null);
        }

        public CustomCompanyMailDeserializer(Class<?> vc){
            super(vc);
        }

        @Override
        public String deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
            JsonNode root = jsonParser.readValueAsTree();
            ArrayNode data = (ArrayNode) root;
            JsonNode node = data.get(0);
            return node.get("VALUE").textValue();
        }
    }
}
