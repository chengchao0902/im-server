package com.rong360.im.server;

import com.rong360.im.request.UserInfo;
import com.rong360.im.service.MessageService;
import com.rong360.im.service.UserService;
import net.openmob.mobileimsdk.server.event.ServerEventListener;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chengchao on 2017/2/25.
 */
public class ServerEventListenerImpl implements ServerEventListener {
    private static Logger logger = LoggerFactory.getLogger(ServerEventListenerImpl.class);
    private UserService userService = new UserService();
    private MessageService messageService = new MessageService();

    @Override
    public int onVerifyUserCallBack(String loginName, String password, String extra) {
        logger.info("[IM][Login]验证用户 " + loginName + "登录开始！");
        UserInfo userInfo = userService.login(loginName, password);
        return userInfo == null ? -1 : 0;
    }

    @Override
    public void onUserLoginAction_CallBack(int userId, String loginName, IoSession session) {
        logger.info("[IM][Login]用户 {} 登录成功！", loginName);
        userService.saveSession(userId, String.valueOf(session.getId()));
    }

    @Override
    public void onUserLogoutAction_CallBack(int userId, Object paramObject) {
        logger.info("[IM][Logout]用户 {} 退出登录！");
        userService.logout(userId);
    }

    @Override
    public boolean onTransBuffer_CallBack(int paramInt1, int paramInt2, String paramString1, String paramString2) {
        System.out.println("==========================onTransBuffer_CallBack");
        return true;
    }

    @Override
    public void onTransBuffer_C2C_CallBack(int paramInt1, int paramInt2, String paramString) {
        System.out.println("=========================onTransBuffer_C2C_CallBack");

    }

    @Override
    public boolean onTransBuffer_C2C_RealTimeSendFaild_CallBack(int toUserId, int fromUserId, String message, String fp) {
        return messageService.saveOfflineMessage(fromUserId, toUserId, message);
    }
}
