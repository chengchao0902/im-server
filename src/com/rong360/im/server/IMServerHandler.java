package com.rong360.im.server;

import com.rong360.im.request.GroupInfo;
import com.rong360.im.request.Message;
import com.rong360.im.service.remote.HGroupService;
import com.rong360.im.service.remote.HMessageService;
import net.openmob.mobileimsdk.server.ServerCoreHandler;
import net.openmob.mobileimsdk.server.protocal.Protocal;
import net.openmob.mobileimsdk.server.protocal.c.PLoginInfo;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * Created by chengchao on 2017/2/25.
 */
public class IMServerHandler extends ServerCoreHandler {
    private static Logger logger = LoggerFactory.getLogger(IMServerHandler.class);

    private HGroupService groupService = new HGroupService();
    private HMessageService messageService = new HMessageService();

    private ThreadPoolExecutor executor;
    private BlockingQueue<Runnable> queue;

    {
        queue = new ArrayBlockingQueue<>(1000);
        executor = new ThreadPoolExecutor(50, 100, 1, TimeUnit.MINUTES, queue);
    }


    @Override
    protected int getNextUserId(PLoginInfo loginInfo) {
        return loginInfo.getUid();
    }

    @Override
    public void sendGroupMessage(IoSession session, final Protocal pFromClient, String remoteAddress) throws Exception {
        super.sendGroupMessage(session, pFromClient, remoteAddress);
        final int gid = pFromClient.getGid();
        GroupInfo groupInfo = groupService.getGroupInfo(gid);
        List<Integer> uids = groupInfo.getUids();
        for (final int uid : uids) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    boolean sendOk;
                    try {
                        sendOk = IMServerLauncher.sendData(pFromClient.getFrom(), uid, pFromClient.getDataContent(), true, gid);
                    } catch (Exception e) {
                        logger.error("[IM][Group]群消息发送异常，from：" + pFromClient.getFrom() + ",to:" + uid + ",gid:" + gid, e);
                        sendOk = false;
                    }
                    if (!sendOk) {
                        Message message = new Message();
                        message.addToUid(uid);
                        message.setFromUid(pFromClient.getFrom());
                        message.setGroupId(gid);
                        message.setMessage(pFromClient.getDataContent());
                        boolean saveOk = messageService.saveOfflineMsg(message);
                        if (!saveOk) {
                            logger.error("[IM][Group]群消息发送失败，并保存消息系统失败，from：" + pFromClient.getFrom() + ",to:" + uid + ",gid:" + gid);
                        }
                    }
                }
            });

        }
        logger.info("[IM][Group] 群消息发送队列大小：" + queue.size() + ", 用户数量：" + uids.size());

    }
}
