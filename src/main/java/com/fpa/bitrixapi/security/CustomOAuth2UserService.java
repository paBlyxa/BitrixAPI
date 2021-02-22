package com.fpa.bitrixapi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Map;

public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final static Logger logger = LoggerFactory.getLogger(CustomOAuth2UserService.class);

    @Override
    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
        logger.debug("Ger user: {}", oAuth2User.getAttributes().toString());

        try {
            return processOAuth2User(oAuth2UserRequest, oAuth2User);
        } catch (Exception e){
            throw  new InternalAuthenticationServiceException(e.getMessage(), e.getCause());
        }
    }

    private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
        String token = oAuth2UserRequest.getAccessToken().getTokenValue();

        BitrixOAuth2UserInfo userInfo = new BitrixOAuth2UserInfo((Map<String, Object>) oAuth2User.getAttributes().get("result"), token);

        return userInfo;
    }
}
