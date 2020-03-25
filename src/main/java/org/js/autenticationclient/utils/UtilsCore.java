package org.js.autenticationclient.utils;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public interface UtilsCore<T extends HttpRequestBase> {

    public void setHeader(T httpRequest, Map<String,String> header);

    public void setEntiry(T httpRequest,String entity) throws UnsupportedEncodingException;

    public void setParam(T httpRequest , Map<String,String> parameters);

}
