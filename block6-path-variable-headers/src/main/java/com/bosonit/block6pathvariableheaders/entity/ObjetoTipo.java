package com.bosonit.block6pathvariableheaders.entity;

import java.util.List;

public class ObjetoTipo {
    private String body;
    private List<String> headers;
    private List<String> requestParams;


    public ObjetoTipo(List<String> headers, List<String> requestParams) {
        this(null, headers, requestParams);
    }

    public ObjetoTipo(String body, List<String> headers, List<String> requestParams) {
        this.body = body;
        this.headers = headers;
        this.requestParams = requestParams;
    }


    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public void setHeaders(List<String> headers) {
        this.headers = headers;
    }

    public List<String> getRequestParams() {
        return requestParams;
    }

    public void setRequestParams(List<String> requestParams) {
        this.requestParams = requestParams;
    }
}
