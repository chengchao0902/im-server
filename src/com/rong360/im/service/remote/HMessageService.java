package com.rong360.im.service.remote;

import com.rong360.im.request.Message;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HMessageService extends HttpService<Message> {
    @Override
    protected Message parseToObject(String request) {
        return null;
    }
}
