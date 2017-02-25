package com.rong360.im.service.remote;

import com.rong360.im.request.UserInfo;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HUserService extends HttpService<UserInfo> {

    @Override
    protected UserInfo parseToObject(String request) {
        return null;
    }
}
