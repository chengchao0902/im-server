package com.rong360.im.service.remote;

import com.rong360.im.common.SimpleHttpClient;
import com.rong360.im.request.HttpRequest;

import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public abstract class HttpService<T extends HttpRequest, Response> {

    private String getUrl;

    private String sendUrl;

    public HttpService(String getUrl, String sendUrl) {
        this.getUrl = getUrl;
        this.sendUrl = sendUrl;
    }

    private SimpleHttpClient httpClient() {
        return SimpleHttpClient.getDefaultHttpClient();
    }

    public T get(Map<String, String> params) {
        String body = httpClient().post(this.getUrl, params);
        return parseToObject(body);
    }

    public Response send(T data) {
        if (data == null) {
            return null;
        }
        String body = httpClient().post(this.sendUrl, data.toRequestParam());
        return isSendSuccess(body);
    }

    protected abstract Response isSendSuccess(String response);

    protected abstract T parseToObject(String response);
}
