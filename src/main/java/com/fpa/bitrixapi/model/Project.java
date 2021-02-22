package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Project extends BitrixEntity {

    private String description;

    public String getDescription() {
        return description;
    }

    @JsonSetter("DESCRIPTION")
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
