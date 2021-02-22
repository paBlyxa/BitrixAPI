package com.fpa.bitrixapi.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class BitrixOAuth2UserInfo implements OAuth2User, Serializable {

    private Map<String, Object> attributes;
    private final String token;
    private Collection<? extends  GrantedAuthority> authorities = new ArrayList<>();

    public BitrixOAuth2UserInfo(Map<String, Object> attributes, String token){
        this.attributes = attributes;
        this.token = token;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities){
        this.authorities = authorities;
    }


    @Override
    public String getName() {
        return (String) attributes.get("NAME");
    }

    public String getEmail() {
        return (String) attributes.get("EMAIL");
    }

    public Integer getId() {return Integer.parseInt((String) attributes.get("ID"));}

    public String getToken() {
        return token;
    }
}
