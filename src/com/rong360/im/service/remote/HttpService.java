package com.rong360.im.service.remote;

import com.rong360.im.request.HttpRequest;

/**
 * Created by chengchao on 2017/2/25.
 */
public abstract class HttpService<T extends HttpRequest> {

    public T get(String params) {
        return null;
    }

    public boolean send(T data) {
        return true;
    }

    protected abstract T parseToObject(String request);
}
