package org.js.autenticationclient.core;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.entity.StringEntity;
import org.apache.http.localserver.LocalTestServer;
import org.apache.http.protocol.HttpContext;
import org.apache.http.protocol.HttpRequestHandler;

import static org.assertj.core.api.Assertions.assertThat;

import org.js.autenticationclient.core.impl.CommonAuthenticationCoreImpl;
import org.js.autenticationclient.bean.Response;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AuthenticationCommonCoreTest {

    private LocalTestServer localTestServer = new LocalTestServer(null,null);

    private CommonAuthenticationCore authenticationCommonCore = new CommonAuthenticationCoreImpl();

    private HttpRequestHandler myHttpRequestHandler = new HttpRequestHandler() {
        public void handle(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext)
                throws HttpException, IOException {
            httpResponse.setEntity(new StringEntity("[{\"id\":\"MYID123\",\"appName\":\"DOCTORAPP1\",\"description\":null,\"access\":\"GHGI5KFJGFKD5\",\"onBoardTime\":\"2020-03-21T16:14:35.525+0000\",\"salt\":\"DJFDJFD\",\"active\":false}]"));
        }
    };

    @Before
    public void setUp() throws Exception {
        localTestServer.start();
        localTestServer.register("/api/application/", myHttpRequestHandler);

    }

    @After
    public void tearDown() throws Exception {
        localTestServer.stop();
    }

    @Test
    public void testGetStatus501() {
        String serverUrl = "http:/" + localTestServer.getServiceAddress();
        Response result =  authenticationCommonCore.get(serverUrl +"/foo/bar");
        assertThat(result.getHttpStatus())
                .isEqualTo(501);
         assertThat(result).isNotNull();
         assertThat(result.getResult())
                .isEmpty();
    }

    @Test
    public void testGet() {
        String serverUrl = "http:/" + localTestServer.getServiceAddress();
        Response result =  authenticationCommonCore.get(serverUrl +"/api/application/");
        assertThat(result.getHttpStatus())
                .isEqualTo(200);
        assertThat(result).isNotNull();
        assertThat(result.getResult())
                .isNotNull() ;
    }

    @Test
    public void testPost() throws IOException {
        String serverUrl = "http:/" + localTestServer.getServiceAddress();
        Response result =  authenticationCommonCore.post(serverUrl +"/api/application/");
        assertThat(result.getHttpStatus())
                .isEqualTo(200);
        assertThat(result).isNotNull();
        assertThat(result.getResult())
                .isNotNull() ;
    }
}
