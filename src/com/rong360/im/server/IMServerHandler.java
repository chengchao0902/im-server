package com.rong360.im.server;

import com.rong360.im.request.UserInfo;
import com.rong360.im.service.remote.HUserService;
import com.rong360.im.service.remote.HttpService;
import net.openmob.mobileimsdk.server.ServerCoreHandler;
import net.openmob.mobileimsdk.server.protocol.c.PLoginInfo;

/**
 * Created by chengchao on 2017/2/25.
 */
public class IMServerHandler extends ServerCoreHandler {

    private HttpService<UserInfo, Integer> userService = new HUserService();

    @Override
    protected int getNextUserId(PLoginInfo loginInfo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(loginInfo.getLoginName());
        userInfo.setPassword(loginInfo.getLoginPsw());
        //TODO get deviceinfo and deviceid from loginInfo.getExtra()
        return userService.send(userInfo);
    }
}
