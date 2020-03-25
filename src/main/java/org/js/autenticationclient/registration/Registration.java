package org.js.autenticationclient.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.js.autenticationclient.bean.Application;
import org.js.autenticationclient.bean.Authentication;

public interface Registration {

    public Application registerYouApplication(Application application) throws JsonProcessingException;

    public Authentication registerYouMember(Authentication authentication) throws JsonProcessingException;
}
