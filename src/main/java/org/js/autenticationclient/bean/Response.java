package org.js.autenticationclient.bean;


public class Response {

    private int httpStatus;

    private String result;

    public Response() {
    }

    public Response(int httpStatus, String result) {
        this.httpStatus = httpStatus;
        this.result = result;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "{" +
                "httpStatus:" + httpStatus +
                ", result:'" + result + '\'' +
                '}';
    }
}
