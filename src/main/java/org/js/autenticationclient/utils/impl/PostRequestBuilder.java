package org.js.autenticationclient.utils.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;

import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;

public class PostRequestBuilder extends UtilsCoreImpl<HttpPost> {

    final static Logger logger = Logger.getLogger(PostRequestBuilder.class);

    public void setEntiry(HttpPost httpRequest, String entity)   {
        if(StringUtils.isNoneEmpty( entity)) {
            try {
                httpRequest.setEntity(new StringEntity(entity));
            } catch (UnsupportedEncodingException e) {
                logger.error("Given Json is not in correct format",e);
                e.printStackTrace();
            }
        }
    }
}
