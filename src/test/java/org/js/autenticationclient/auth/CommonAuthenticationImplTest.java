package org.js.autenticationclient.auth;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.js.autenticationclient.auth.impl.CommonAuthenticationImpl;
import org.js.autenticationclient.bean.Authentication;
import org.js.autenticationclient.registration.Registration;
import org.js.autenticationclient.registration.impl.RegistrationImpl;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CommonAuthenticationImplTest {

    private CommonAuthenticationImpl subjectToTest = new CommonAuthenticationImpl();
    private Registration registration = new RegistrationImpl();


    public void testGetAccessToken() {
        try {
            Authentication authentication = registration.registerYouMember(new Authentication.
                    AuthenticationBuilder("D696596","J1tendr@").build());

            String accesToken =  subjectToTest.getToken("D696596","J1tendr@");
            authentication = subjectToTest.authenticate(accesToken);
            Boolean aBoolean = subjectToTest.deleteAccount(accesToken);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
