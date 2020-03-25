package org.js.autenticationclient.utils;

import org.apache.http.client.methods.HttpGet;

import static org.assertj.core.api.Assertions.assertThat;

import org.js.autenticationclient.utils.impl.GetRequestBuilder;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class UtilsGetRequestTest {

    private GetRequestBuilder subjectToTest = new GetRequestBuilder();

    HttpGet httpGet = new HttpGet("http://localhost:8080");
    @Test
    public void testSetParameter() {
        Map<String,String> param = new HashMap<String, String>();
        param.put("username","D579446");
        param.put("password","123456");
        subjectToTest.setParam(httpGet,param);
        assertThat(httpGet.getURI().toString())
                .isEqualTo("http://localhost:8080?password=123456&username=D579446");
    }

    @Test
    public void testSetHeader() {
        Map<String,String> param = new HashMap<String, String>();
        param.put("username","D579446");
        param.put("password","123456");
        subjectToTest.setHeader(httpGet,param);

        assertThat(httpGet.getAllHeaders().length)
                .isEqualTo(2);
        assertThat(httpGet.getHeaders("username")[0].getName())
                .isEqualTo("username");
        assertThat(httpGet.getHeaders("username")[0].getValue())
                .isEqualTo("D579446");
    }
}
