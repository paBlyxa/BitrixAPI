package com.fpa.bitrixapi.model;

import com.fasterxml.jackson.annotation.JsonSetter;

public class UserGroup {
    private Integer userId;
    private String role;

    public Integer getUserId() {
        return userId;
    }
    @JsonSetter("USER_ID")
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getRole() {
        return role;
    }
    @JsonSetter("ROLE")
    public void setRole(String role) {
        this.role = role;
    }

    public boolean isModerator(){
        return SONET_ROLES.isModerator(role);
    }
}
