package org.js.autenticationclient.registration.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.js.autenticationclient.auth.header.CommonHeader;
import org.js.autenticationclient.auth.impl.CommonAuthenticationImpl;
import org.js.autenticationclient.bean.Application;
import org.js.autenticationclient.bean.Authentication;
import org.js.autenticationclient.bean.CommonConfiguration;
import org.js.autenticationclient.bean.Response;
import org.js.autenticationclient.core.CommonAuthenticationCore;
import org.js.autenticationclient.core.impl.CommonAuthenticationCoreImpl;
import org.js.autenticationclient.registration.Registration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationImpl extends CommonHeader implements Registration {

    private final static Logger logger = Logger.getLogger(RegistrationImpl.class);


    public RegistrationImpl() {
        super();
    }

    @Override
    public Application registerYouApplication(Application application) throws JsonProcessingException {
        String entity = new Application.ApplicationBuilder(application).buildJson();
        Map<String,String> header = getCommonHeader();

        String url = CommonConfiguration.getProperties("app.base")
                + REGISTRATION;
        try {
                Response response = this.commonAuthenticationCore.post(url,header,entity,null) ;
                if(response.getHttpStatus() == 200) {
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(response.getResult(), Application.class);
                }
        } catch (IOException e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Authentication registerYouMember(Authentication authentication) throws JsonProcessingException {
        Map header = getCommonHeader();

        String url = CommonConfiguration.getProperties("app.base")
                + AUTHENTICATION
                + ACCOUNTREGISTER
                + CommonConfiguration.getProperties("app.id");

        String entity = new Authentication.AuthenticationBuilder(authentication.getUserName(),
                authentication.getPassward()).buildJson();
        try {
            Response response = this.commonAuthenticationCore.post(url,header,entity,null);
            if (response.getHttpStatus() == 200) {
                if(response.getResult()!=null && !response.getResult().isEmpty()) {
                    return  null;
                }
            }
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
            e.printStackTrace();
        }
        return null;
    }
}
