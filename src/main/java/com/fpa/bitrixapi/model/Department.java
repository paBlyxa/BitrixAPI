package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Department extends BitrixEntity{

    private Integer parent;
    private Integer head;

    public Integer getParent() {
        return parent;
    }

    @JsonSetter("PARENT")
    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public Integer getHead() {
        return head;
    }

    @JsonSetter("UF_HEAD")
    public void setHead(Integer head) {
        this.head = head;
    }

    @Override
    public String toString() {
        return "BitrixDepartment{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                ", head=" + head +
                '}';
    }
}
