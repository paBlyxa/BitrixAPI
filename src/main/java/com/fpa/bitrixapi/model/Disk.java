package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class Disk extends BitrixEntity {

    private String code;
    private String moduleId;
    private String entityType;
    private String entityId;
    private Integer rootId;

    public String getCode() {
        return code;
    }

    @JsonSetter("CODE")
    public void setCode(String code) {
        this.code = code;
    }

    public String getModuleId() {
        return moduleId;
    }

    @JsonSetter("MODULE_ID")
    public void setModuleId(String moduleId) {
        this.moduleId = moduleId;
    }

    public String getEntityType() {
        return entityType;
    }

    @JsonSetter("ENTITY_TYPE")
    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public String getEntityId() {
        return entityId;
    }

    @JsonSetter("ENTITY_ID")
    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }

    public Integer getRootId() {
        return rootId;
    }

    @JsonSetter("ROOT_OBJECT_ID")
    public void setRootId(Integer rootId) {
        this.rootId = rootId;
    }

    public boolean isGroupDisk(){
        return "group".equals(entityType);
    }

    @Override
    public String toString() {
        return "Disk{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", moduleId='" + moduleId + '\'' +
                ", entityType='" + entityType + '\'' +
                ", entityId=" + entityId +
                ", rootId=" + rootId +
                '}';
    }
}
