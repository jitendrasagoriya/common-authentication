package org.js.autenticationclient.bean;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.Serializable;
import java.sql.Timestamp;


public class Authentication implements Serializable {


    private String userName;


    private String passward;


    private String userId;


    private String appId;


    private String token;


    private Integer expaireDay;


    private Timestamp creationDate;


    private Timestamp lastLogin;


    private Boolean isLogout;


    private Boolean isActive;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassward() {
        return passward;
    }

    public void setPassward(String passward) {
        this.passward = passward;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpaireDay() {
        return expaireDay;
    }

    public void setExpaireDay(Integer expaireDay) {
        this.expaireDay = expaireDay;
    }

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Boolean getIsLogout() {
        return isLogout;
    }

    public void setIsLogout(Boolean logout) {
        isLogout = logout;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean active) {
        isActive = active;
    }

    public Authentication() {
    }

    public Authentication(AuthenticationBuilder authenticationBuilder) {
        this.appId = authenticationBuilder.appId;
        this.creationDate = authenticationBuilder.creationDate;
        this.expaireDay = authenticationBuilder.expaireDay;
        this.isLogout = authenticationBuilder.isLogout;
        this.lastLogin = authenticationBuilder.lastLogin;
        this.passward = authenticationBuilder.passward;
        this.token = authenticationBuilder.token;
        this.userId = authenticationBuilder.userId;
        this.userName = authenticationBuilder.userName;
        this.isActive = authenticationBuilder.isActive;

    }

    public static class AuthenticationBuilder {

        private String userName;
        private String passward;
        private String userId;
        private String appId;
        private String token;
        private Integer expaireDay;
        private Timestamp creationDate;
        private Timestamp lastLogin;
        private Boolean isLogout;
        private Boolean isActive;



        public AuthenticationBuilder (String userName,String passward) {
            this.userName = userName;
            this.passward = passward;
        }

        public AuthenticationBuilder userName(String userName) {
            this.userName = userName;
            return this;
        }

        public AuthenticationBuilder passward(String passward) {
            this.passward = passward;
            return this;
        }

        public AuthenticationBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }

        public AuthenticationBuilder appId(String appId) {
            this.appId = appId;
            return this;
        }

        public AuthenticationBuilder token(String token) {
            this.token = token;
            return this;
        }

        public AuthenticationBuilder expireDay(Integer expireDay) {
            this.expaireDay = expireDay;
            return this;
        }

        public AuthenticationBuilder creationDate(Timestamp creationDate) {
            this.creationDate = creationDate;
            return this;
        }

        public AuthenticationBuilder lastLogin(Timestamp lastLogin) {
            this.lastLogin = lastLogin;
            return this;
        }

        public AuthenticationBuilder logout(Boolean logout) {
            this.isLogout = logout;
            return this;
        }

        public AuthenticationBuilder isActive(Boolean isActive) {
            this.isActive = isActive;
            return this;
        }

        //Return the finally consrcuted User object
        public Authentication build() {
            Authentication authentication =  new Authentication(this);
            validateUserObject(authentication);
            return authentication;
        }

        public String buildJson() throws JsonProcessingException {
            ObjectMapper objectMapper = new ObjectMapper();
            Authentication authentication =  new Authentication(this);
            return objectMapper.writeValueAsString(authentication);
        }

        private void validateUserObject(Authentication authentication) {
            //Do some basic validations to check
            //if user object does not break any assumption of system
        }
    }

}
