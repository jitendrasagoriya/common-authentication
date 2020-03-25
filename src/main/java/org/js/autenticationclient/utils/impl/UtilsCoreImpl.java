package org.js.autenticationclient.utils.impl;

import org.apache.commons.httpclient.params.DefaultHttpParams;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpParams;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpRequestBase;
import org.js.autenticationclient.utils.UtilsCore;

import java.net.URI;
import java.util.Map;

public abstract class UtilsCoreImpl<T extends HttpRequestBase> implements UtilsCore<T> {

    public void setHeader(T httpRequest, Map<String, String> header) {
        if(header!=null) {
            for (Map.Entry entry : header.entrySet()) {
                httpRequest.setHeader(entry.getKey().toString(),
                        entry.getValue().toString());
            }
        }
    }

    public void setParam(T httpRequest, Map<String, String> parameters) {
        StringBuilder url = new StringBuilder("?");
        if(parameters!=null) {
            int paramCount = parameters.size();
            int paramCounter = 1;
            for (Map.Entry entry : parameters.entrySet()) {
               url.append(entry.getKey().toString()+"="+entry.getValue().toString());

               if(paramCounter != paramCount) {
                   url.append("&");
               }
                paramCounter++;
            }
        }
        URI uri = httpRequest.getURI();
        URI newUri1 = URI.create(uri.toString() + url);
        httpRequest.setURI(newUri1);
    }
}
