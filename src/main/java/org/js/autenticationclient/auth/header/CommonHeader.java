package org.js.autenticationclient.auth.header;

import org.apache.commons.collections.map.HashedMap;
import org.js.autenticationclient.bean.CommonConfiguration;
import org.js.autenticationclient.core.CommonAuthenticationCore;
import org.js.autenticationclient.core.impl.CommonAuthenticationCoreImpl;

import java.util.HashMap;
import java.util.Map;

public class CommonHeader {

    protected final String REGISTRATION = "api/application/";
    protected final String AUTHENTICATION = "api/authentication/";
    protected final String AUTHURL = "auth/";
    protected final String ACCOUNTREGISTER = "register/";

    protected CommonAuthenticationCore commonAuthenticationCore = null;

    public CommonHeader() {
        this.commonAuthenticationCore = new CommonAuthenticationCoreImpl();
    }

    public Map<String,String> getCommonHeader() {
        Map<String,String> header = new HashMap<>();
        header.put("X-AUTH-LOG-HEADER-APP-ACCESS", CommonConfiguration.getProperties("app.access"));
        header.put("content-type", "application/json");
        return header;
    }
}
