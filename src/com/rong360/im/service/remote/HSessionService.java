package com.rong360.im.service.remote;

import com.rong360.im.request.LoginSession;

/**
 * Created by chengchao on 2017/2/26.
 */
public class HSessionService extends HttpService<LoginSession, Integer> {
    public HSessionService() {
        super("", "");
    }

    @Override
    protected Integer isSendSuccess(String response) {
        return null;
    }

    @Override
    protected LoginSession parseToObject(String response) {
        return null;
    }
}
