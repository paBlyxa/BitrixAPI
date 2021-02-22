package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;

import java.util.List;

public class User extends BitrixEntity{

    private String email;
    private String lastName;
    private String secondName;
    private boolean active;
    private List<Integer> ufDepartments;

    public String getEmail() {
        return email;
    }

    @JsonSetter("EMAIL")
    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("LAST_NAME")
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSecondName() {
        return secondName;
    }

    @JsonSetter("SECOND_NAME")
    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public boolean isActive() {
        return active;
    }

    @JsonSetter("ACTIVE")
    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Integer> getUfDepartments() {
        return ufDepartments;
    }

    @JsonSetter("UF_DEPARTMENT")
    public void setUfDepartments(List<Integer> ufDepartments) {
        this.ufDepartments = ufDepartments;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", active=" + active +
                ", departments=" + ufDepartments +
                '}';
    }
}
