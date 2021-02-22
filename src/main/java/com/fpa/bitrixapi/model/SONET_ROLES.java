package com.fpa.bitrixapi.model;

public enum SONET_ROLES {
    OWNER("A"), MODERATOR("E"), USER("K");

    private SONET_ROLES(String role){
        this.role = role;
    }

    private final String role;

    public static boolean isModerator(String role) {
        return MODERATOR.role.equals(role);
    }
}
