package com.rong360.im.server;

import com.rong360.im.request.GroupInfo;
import com.rong360.im.request.UserInfo;
import com.rong360.im.service.remote.HGroupService;
import com.rong360.im.service.remote.HUserService;
import net.openmob.mobileimsdk.server.ServerCoreHandler;
import net.openmob.mobileimsdk.server.protocol.Protocol;
import net.openmob.mobileimsdk.server.protocol.c.PLoginInfo;
import org.apache.mina.core.session.IoSession;

import java.util.List;

/**
 * Created by chengchao on 2017/2/25.
 */
public class IMServerHandler extends ServerCoreHandler {

    private HUserService userService = new HUserService();
    private HGroupService groupService = new HGroupService();

    @Override
    protected int getNextUserId(PLoginInfo loginInfo) {
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername(loginInfo.getLoginName());
        userInfo.setPassword(loginInfo.getLoginPsw());
        //TODO get deviceinfo and deviceid from loginInfo.getExtra()
        return 0;
    }

    @Override
    public void sendGroupMessage(IoSession session, Protocol pFromClient, String remoteAddress) throws Exception {
        super.sendGroupMessage(session, pFromClient, remoteAddress);
        int gid = pFromClient.getGid();
        GroupInfo groupInfo = groupService.getGroupInfo(gid);
        List<Integer> uids = groupInfo.getUids();
        for (int uid : uids) {
            //todo 改为Threadpool 实现
            Protocol groupProtocol = (Protocol) pFromClient.clone();
            processReceiveData(session, groupProtocol, remoteAddress);
        }
    }
}
