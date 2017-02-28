package com.rong360.im.rest;

import com.rong360.im.request.GroupInfo;
import com.rong360.im.request.Message;
import com.rong360.im.service.remote.HGroupService;
import com.rong360.im.service.remote.HMessageService;
import net.openmob.mobileimsdk.server.ServerLauncher;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ResourceException;
import org.restlet.resource.ServerResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengchao on 2017/2/28.
 */
public class MessageSenderResource extends ServerResource {
    private static final Logger logger = LoggerFactory.getLogger(MessageSenderResource.class);

    //调用前需要先给launcher赋值
    public static ServerLauncher launcher = null;

    private HGroupService groupService = new HGroupService();
    private HMessageService messageService = new HMessageService();

    public MessageSenderResource() {
        if (launcher == null) {
            throw new RuntimeException("请先设置 launcher，MessageSenderResource.launcher = ServerLauncher;");
        }
    }

    @Override
    protected Representation post(Representation entity) throws ResourceException {
        Form form = new Form(entity);
        String msgType = form.getFirstValue("type");
        int fromUid = Integer.parseInt(form.getFirstValue("from_uid"));
        String msg = form.getFirstValue("msg");
        JSONObject result = new JSONObject();
        try {
            if ("1".equals(msgType)) {
                int toUid = Integer.parseInt(form.getFirstValue("to_uid"));
                sendSingleMessage(fromUid, toUid, msg);
            } else {
                int toGid = Integer.parseInt(form.getFirstValue("gid"));
                sendGroupMessage(fromUid, toGid, msg);
            }
            result.put("error", 0);
            result.put("msg", "success");
            result.put("data", "");
        } catch (Exception e) {
            logger.error("[REST]发送消息异常:", e);
            result.put("error", -1);
            result.put("msg", "error");
            result.put("data", e.getMessage());
        }

        return new JsonRepresentation(result);
    }

    public void sendGroupMessage(int fromUid, int groupId, String message) throws Exception {
        GroupInfo groupInfo = groupService.getGroupInfo(groupId);
        List<Integer> sendFailed = new ArrayList<>();
        for (int toUid : groupInfo.getUids()) {
            if (toUid == fromUid) {
                continue;
            }
            boolean sendOk = launcher.sendData(fromUid, toUid, message, true, groupId);
            if (!sendOk) {
                sendFailed.add(toUid);
            }
        }

        for (int toUid : sendFailed) {
            saveOffline(fromUid, toUid, groupId, message);
        }


    }

    protected void saveOffline(int fromUid, int toUid, int groupId, String message) {
        Message msg = new Message();
        msg.setMessage(message);
        msg.setFromUid(fromUid);
        msg.setGroupId(groupId);
        msg.addToUid(toUid);
        boolean saveOk = messageService.saveOfflineMsg(msg);
        if (!saveOk) {
            logger.error("[REST] 离线消息存储失败，from_uid:" + fromUid + ", to_uid:" + toUid + ", group_id:" + groupId + ", message:" + message);
        } else {
            logger.info("[REST] 离线消息存储成功，from_uid:" + fromUid + ", to_uid:" + toUid + ", group_id:" + groupId);
        }
    }

    public void sendSingleMessage(int fromUid, int toUid, String message) throws Exception {
        boolean sendOk = launcher.sendData(fromUid, toUid, message, true, 0);
        if (!sendOk) {
            saveOffline(fromUid, toUid, 0, message);
        }
    }


}
