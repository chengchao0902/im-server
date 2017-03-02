package com.rong360.im.server;

import com.rong360.im.request.Message;
import com.rong360.im.service.remote.HMessageService;
import com.rong360.im.service.remote.HUserService;
import net.openmob.mobileimsdk.server.event.ServerEventListener;
import net.openmob.mobileimsdk.server.protocal.Protocal;
import net.openmob.mobileimsdk.server.protocal.c.PLoginInfo;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by chengchao on 2017/2/25.
 */
public class ServerEventListenerImpl implements ServerEventListener {
    private static Logger logger = LoggerFactory.getLogger(ServerEventListenerImpl.class);
    private HUserService userService = new HUserService();
    private HMessageService messageService = new HMessageService();
    private ThreadPoolExecutor executor;
    private BlockingQueue<Runnable> queue;

    {
        queue = new ArrayBlockingQueue<>(1000);
        executor = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES, queue);
    }


    @Override
    public int onVerifyUserCallBack(PLoginInfo loginInfo, IoSession session) {
        logger.info("[IM][Login]验证用户 " + loginInfo.getPhone() + "登录开始！");
//        int uid = userService.login(loginInfo.getDeviceId(), loginInfo.getPhone(), loginInfo.getDeviceInfo(), String.valueOf(session.getId()));

        int uid = userService.login(loginInfo.getLoginPsw(), loginInfo.getLoginName(), loginInfo.getLoginName(), String.valueOf(session.getId()));
        logger.info("=============uid:" + uid);
        loginInfo.setUid(uid);
        return uid == -1 ? -1 : 0;
    }

    @Override
    public void onUserLoginAction_CallBack(final int userId, String loginName, IoSession session) {
        //在这获取离线消息，并发送出去
        logger.info("[IM][Login]用户 {} 登录成功！", loginName);
        executor.execute(new Runnable() {
            //// TODO: 2017/3/2 改为任务的形式，Timer实现
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    logger.warn("[IM][Offline] 发送离线消息sleep 0.5s 异常, 忽略异常");
                }
                List<Message> messages = messageService.getOfflineMsg(userId);
                for (Message message : messages) {
                    boolean sendOk;
                    try {
                        logger.debug("[IM][Offline]发送离线消息为：" + message.toRequestParam());
                        sendOk = IMServerLauncher.sendData(message.getFromUid(), userId, message.getMessage(), true, message.getGroupId());
                    } catch (Exception e) {
                        logger.error("[IM][Offline]发送离线消息出错 message_id: {}", message.getId());
                        sendOk = false;
                    }
                    if (!sendOk) {
                        logger.warn("[IM][Offline]发送离线消息失败 message_id: {}", message.getId());
                        messageService.saveOfflineMsg(message);
                    }
                }
            }
        });

    }

    @Override
    public void onUserLogoutAction_CallBack(int userId, Object paramObject) {
        logger.info("[IM][Logout]用户 " + userId + " 退出登录！");
        userService.logout(userId);
    }

    @Override
    public boolean onTransBuffer_CallBack(int paramInt1, int paramInt2, String paramString1, String paramString2) {

        return true;
    }

    @Override
    public void onTransBuffer_C2C_CallBack(int paramInt1, int paramInt2, String paramString) {

    }

    @Override
    public boolean onTransBuffer_C2C_RealTimeSendFaild_CallBack(Protocal pFromClient) {
        Message message = new Message();
        message.setFromUid(pFromClient.getFrom());
        message.addToUid(pFromClient.getTo());
        message.setGroupId(pFromClient.getGid());
        message.setMessage(pFromClient.getDataContent());
        boolean result = messageService.saveOfflineMsg(message);
        logger.info("[IM][Offline]保存离线消息，from：{}, to:{}, result:" + result, pFromClient.getFrom(), pFromClient.getTo());
        return result;
    }
}
