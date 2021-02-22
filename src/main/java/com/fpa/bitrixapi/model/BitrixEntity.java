package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public abstract class BitrixEntity {

    protected Integer id;
    protected String name;

    @JsonGetter("ID")
    public Integer getId() {
        return id;
    }

    @JsonSetter("ID")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonGetter("NAME")
    public String getName() {
        return name;
    }

    @JsonSetter("NAME")
    public void setName(String name) {
        this.name = name;
    }
}
