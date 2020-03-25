package org.js.autenticationclient.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.js.autenticationclient.bean.Application;
import org.js.autenticationclient.registration.impl.RegistrationImpl;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class RegistrationImplTest {

    private RegistrationImpl subjectToTest = new RegistrationImpl();

    @Test
    public void testRegisterYouApplication() throws JsonProcessingException {
        Application application = new Application.ApplicationBuilder()
                .withAppName("FIRSTDOCTORAPP")
                .withDescription("THIS IS MY FIRST FIRSTDOCTORAPP TESTING WITH JAR")
                .build();

       Application response =  subjectToTest.registerYouApplication(application);
       System.out.println(response);
    }
}
