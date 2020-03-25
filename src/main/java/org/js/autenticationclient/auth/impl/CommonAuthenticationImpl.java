package org.js.autenticationclient.auth.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.js.autenticationclient.auth.CommonAuthentication;
import org.js.autenticationclient.auth.header.CommonHeader;
import org.js.autenticationclient.bean.CommonConfiguration;
import org.js.autenticationclient.bean.Response;
import org.js.autenticationclient.core.CommonAuthenticationCore;
import org.js.autenticationclient.core.impl.CommonAuthenticationCoreImpl;
import org.js.autenticationclient.bean.Authentication;
import org.js.autenticationclient.dto.LoginDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CommonAuthenticationImpl extends CommonHeader implements CommonAuthentication {

    private final static Logger logger = Logger.getLogger(CommonAuthenticationImpl.class);

    public CommonAuthenticationImpl() {
        super();
    }

    @Override
    public String getToken(String userName, String password) throws JsonProcessingException {
        Map<String,String> header = new HashMap<>();
        header.put("X-AUTH-LOG-HEADER-APP-ACCESS",CommonConfiguration.getProperties("app.access"));
        //SET CONTENT TYPE
        header.put("content-type", "application/json");

        String url = CommonConfiguration.getProperties("app.base")
                + AUTHENTICATION
                + "token/"
                + CommonConfiguration.getProperties("app.id");
        LoginDto dto = new LoginDto();
        dto.setUserName(userName);//"USER12"
        dto.setPassword(password);//"myPassword123"
        String entity = dto.buildJson();

        try {
            return this.commonAuthenticationCore.post(url,header,entity,null).getResult();
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Authentication authenticate(String accessToken) {
        Map<String,String> header = new HashMap<>();
        header.put("X-AUTH-LOG-HEADER-APP-ACCESS",CommonConfiguration.getProperties("app.access"));
        header.put("X-AUTH-LOG-HEADER-ACCESS",accessToken);

        header.put("content-type", "application/json");

        String url = CommonConfiguration.getProperties("app.base")
                + AUTHENTICATION
                + AUTHURL;

        try {
            Response response = this.commonAuthenticationCore.post(url,header,null,null);

            if (response.getHttpStatus() == 200) {
                ObjectMapper objectMapper = new ObjectMapper();
                if(response.getResult()!=null && !response.getResult().isEmpty()) {
                    return objectMapper.readValue(response.getResult(), Authentication.class);
                }
            }

        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Boolean deleteAccount(String accessToken) {
        Map<String,String> header = getCommonHeader();
        header.put("X-AUTH-LOG-HEADER-ACCESS",accessToken);

        String url = CommonConfiguration.getProperties("app.base")
                + AUTHENTICATION;
        try {
            Response response = this.commonAuthenticationCore.delete(url,header,null);
            if (response.getHttpStatus() == 200) {
                if(response.getResult()!=null && !response.getResult().isEmpty()) {
                    return  response.getResult()=="TRUE"?Boolean.TRUE:Boolean.FALSE;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return Boolean.FALSE;
    }

}
