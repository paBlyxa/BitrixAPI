package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.*;

import java.util.Arrays;

@JsonTypeName(value = "task")
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.WRAPPER_OBJECT)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Task {

    private Integer id;
    private String title;
    private Integer responsible;
    private String description;
    private Integer group;
    private Integer[] files;
    private Integer[] accomplices;
    private Status status;


    public Integer getId() {
        return id;
    }

    @JsonSetter("ID")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonGetter("TITLE")
    public String getTitle() {
        return title;
    }

    @JsonSetter("TITLE")
    public void setTitle(String title) {
        this.title = title;
    }

    @JsonGetter("RESPONSIBLE_ID")
    public Integer getResponsible() {
        return responsible;
    }

    @JsonSetter("RESPONSIBLEID")
    public void setResponsible(Integer responsible) {
        this.responsible = responsible;
    }

    @JsonGetter("DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @JsonSetter("DESCRIPTION")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonGetter("GROUP_ID")
    public Integer getGroup() {
        return group;
    }
    @JsonSetter("GROUP_ID")
    public void setGroup(Integer group) {
        this.group = group;
    }

    @JsonGetter("ACCOMPLICES")
    public Integer[] getAccomplices() {
        return accomplices;
    }

    @JsonSetter("ACCOMPLICES")
    public void setAccomplices(Integer[] accomplices) {
        this.accomplices = accomplices;
    }

    @JsonGetter("UF_TASK_WEBDAV_FILES")
    public String[] getFiles() {
        if (files == null){
            return null;
        }
        String[] strFiles = new String[files.length];
        for (int i = 0; i < files.length; i++){
            strFiles[i] = "n" + files[i];
        }
        return strFiles;
    }

    @JsonSetter("UF_TASK_WEBDAV_FILES")
    public void setFiles(Integer[] files) {
        this.files = files;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @JsonSetter("STATUS")
    public void setStatus(String status) {
        int statusId = Integer.parseInt(status);
        for (Status s : Status.values()){
            if (s.id == statusId){
                this.status = s;
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", responsible=" + responsible +
                ", description='" + description + '\'' +
                ", group=" + group +
                ", files=" + Arrays.toString(files) +
                ", accomplices=" + Arrays.toString(accomplices) +
                ", status=" + status +
                '}';
    }

    public enum Status {
        WAIT(2),
        EXECUTING(3),
        WAITCTRL(4),
        GONE(5),
        DEFERRED(6);

        Status(int id){
            this.id = id;
        }

        private int id;
    }
}
