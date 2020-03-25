package org.js.autenticationclient.core.impl;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;
import org.js.autenticationclient.core.CommonAuthenticationCore;
import org.js.autenticationclient.bean.Response;
import org.js.autenticationclient.utils.impl.DeleteRequestBuilder;
import org.js.autenticationclient.utils.impl.GetRequestBuilder;
import org.js.autenticationclient.utils.impl.PostRequestBuilder;

import java.io.IOException;
import java.util.Map;

public class CommonAuthenticationCoreImpl implements CommonAuthenticationCore {

    private final static Logger logger = Logger.getLogger(CommonAuthenticationCoreImpl.class);

    public CommonAuthenticationCoreImpl() {
    }

    public Response post(String url, Map<String, String> header, String entity, Map<String, String> param) throws IOException {
        String result = "";
        Response localResponse = new Response();
        PostRequestBuilder utilsPostRequest = new PostRequestBuilder();
        HttpPost post = new HttpPost(url);


        //SET HEADER
        utilsPostRequest.setHeader(post,header);

        //SET ENTITY
        utilsPostRequest.setEntiry(post,entity);

        //SET PARAMS
        utilsPostRequest.setParam(post,param);



        if (logger.isDebugEnabled())
            logger.debug("POST REQUEST :" + post);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(post)) {
            // Get HttpResponse Status
            if(logger.isDebugEnabled()) {
                logger.debug("ProtocolVersion : "+response.getProtocolVersion());
                logger.debug("StatusCode : " + response.getStatusLine().getStatusCode());
                logger.debug("ReasonPhrase : "+response.getStatusLine().getReasonPhrase());
            }

            localResponse.setHttpStatus(response.getStatusLine().getStatusCode());
            localResponse.setResult( EntityUtils.toString(response.getEntity()) );
        }
        return localResponse;
    }

    public Response post(String url, Map<String, String> header, String entity) throws IOException {
        return post(url,header,entity,null);
    }

    public Response post(String url, String entity, Map<String, String> request) throws IOException {
        return post(url,null,entity,request);
    }

    public Response post(String url, String entity) throws IOException {
        return post(url,null,entity,null);
    }

    public Response post(String url, Map<String, String> header, Map<String, String> request) throws IOException {
        return post(url,header,null,request);
    }

    public Response post(String url) throws IOException {
        return post(url,null,null,null);
    }

    public Response get(String url, Map<String, String> header, Map<String, String> request) {
        Response localResponse = new Response();
        HttpGet httpGet = new HttpGet(url);
        GetRequestBuilder getRequestBuilder = new GetRequestBuilder();

        //SET HEADER
        getRequestBuilder.setHeader(httpGet,header);

        //SET PARAMS
        getRequestBuilder.setParam(httpGet,request);


        if (logger.isDebugEnabled())
            logger.debug("GET REQUEST :" + httpGet);

        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse response = httpClient.execute(httpGet)) {

            // Get HttpResponse Status
            if(logger.isDebugEnabled()) {
                logger.debug("ProtocolVersion : "+response.getProtocolVersion());
                logger.debug("StatusCode : " + response.getStatusLine().getStatusCode());
                logger.debug("ReasonPhrase : "+response.getStatusLine().getReasonPhrase());
                logger.debug("StatusLine : "+response.getStatusLine().toString());
            }

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                // return it as a String
                String result = EntityUtils.toString(entity);
                localResponse.setHttpStatus(response.getStatusLine().getStatusCode());
                if(logger.isDebugEnabled()) {
                    logger.debug("Response : "+result);
                    localResponse.setResult(result);
                }
                return  localResponse;
            }

        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return localResponse;
    }

    public Response get(String url, Map<String, String> header, boolean isRequest) {
        if(isRequest)
            return get(url,null,header);
        else
            return get(url,header,null);
    }

    public Response get(String url) {
        return get(url,null,null);
    }

    @Override
    public Response delete(String url, Map<String, String> header, Map<String, String> request) {
            Response localResponse = new Response();
            HttpDelete httpDelete = new HttpDelete(url);
            DeleteRequestBuilder deleteRequestBuilder = new DeleteRequestBuilder();

            //SET HEADER
             deleteRequestBuilder.setHeader(httpDelete,header);

            //SET PARAMS
             deleteRequestBuilder.setParam(httpDelete,request);

        if (logger.isDebugEnabled())
            logger.debug("DELETE REQUEST :" + httpDelete);

            try (CloseableHttpClient httpClient = HttpClients.createDefault();
                 CloseableHttpResponse response = httpClient.execute(httpDelete)) {

                // Get HttpResponse Status
                if(logger.isDebugEnabled()) {
                    logger.debug("ProtocolVersion : "+response.getProtocolVersion());
                    logger.debug("StatusCode : " + response.getStatusLine().getStatusCode());
                    logger.debug("ReasonPhrase : "+response.getStatusLine().getReasonPhrase());
                    logger.debug("StatusLine : "+response.getStatusLine().toString());
                }

                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    // return it as a String
                    String result = EntityUtils.toString(entity);
                    localResponse.setHttpStatus(response.getStatusLine().getStatusCode());
                    if(logger.isDebugEnabled()) {
                        logger.debug("Response : "+result);
                        localResponse.setResult(result);
                    }
                    return  localResponse;
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return localResponse;
    }

    @Override
    public Response delete(String url, Map<String, String> header, boolean isRequest) {
        if(isRequest)
            return delete(url,null,header);
        else
            return delete(url,header,null);
    }

    @Override
    public Response delete(String url) {
        return delete(url,null,null);
    }


}
