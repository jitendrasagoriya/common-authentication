package org.js.autenticationclient.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.js.autenticationclient.bean.Authentication;

public interface CommonAuthentication  {

    public String getToken(String userName,String password) throws JsonProcessingException;

    public Authentication authenticate(String accessToken);

    public Boolean deleteAccount(String accessToken);

}
