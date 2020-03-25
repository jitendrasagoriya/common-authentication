package org.js.autenticationclient.core;

import org.js.autenticationclient.bean.Response;

import java.io.IOException;
import java.util.Map;

public interface CommonAuthenticationCore {

        public Response post(String url, Map<String,String> header, String entity, Map<String,String> request) throws IOException;
        public Response post(String url, Map<String,String> header,String entity ) throws IOException;
        public Response post(String url,  String entity, Map<String,String> request) throws IOException;
        public Response post(String url, String entity ) throws IOException;
        public Response post(String url, Map<String,String> header, Map<String,String> request) throws IOException;
        public Response post(String url ) throws IOException;

        public Response get(String url, Map<String,String> header, Map<String,String> request);
        public Response get(String url, Map<String,String> header, boolean isRequest );
        public Response get(String url );

        public Response delete(String url, Map<String,String> header, Map<String,String> request);
        public Response delete(String url, Map<String,String> header, boolean isRequest );
        public Response delete(String url );
}
