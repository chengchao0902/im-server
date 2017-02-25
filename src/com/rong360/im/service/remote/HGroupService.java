package com.rong360.im.service.remote;

import com.rong360.im.request.GroupInfo;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HGroupService extends HttpService<GroupInfo> {
    @Override
    protected GroupInfo parseToObject(String request) {
        return null;
    }
}
