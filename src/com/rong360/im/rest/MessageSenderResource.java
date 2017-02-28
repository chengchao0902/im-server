package com.rong360.im.rest;

import com.rong360.im.request.GroupInfo;
import com.rong360.im.service.remote.HGroupService;
import net.openmob.mobileimsdk.server.ServerLauncher;
import org.json.JSONObject;
import org.restlet.data.Form;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Post;
import org.restlet.resource.ServerResource;

/**
 * Created by chengchao on 2017/2/28.
 */
public class MessageSenderResource extends ServerResource {

    private ServerLauncher launcher;
    private HGroupService groupService = new HGroupService();

    public MessageSenderResource(ServerLauncher launcher) {
        this.launcher = launcher;
    }

    @Post
    public Representation action() {
        Form form = getRequest().getResourceRef().getQueryAsForm();
        String msgType = form.getFirstValue("type");
        int fromUid = Integer.parseInt(form.getFirstValue("from_uid"));
        String msg = form.getFirstValue("msg");
        JSONObject result = new JSONObject();
        try {
            if ("1" == msgType) {
                int toUid = Integer.parseInt(form.getFirstValue("to_uid"));
                sendSingleMessage(fromUid, toUid, msg);
            } else {
                int toGid = Integer.parseInt(form.getFirstValue("gid"));
                sendGroupMessage(fromUid, toGid, msg);
            }
            result.put("error", 0);
            result.put("msg", "success");
            result.put("data", (String) null);
        } catch (Exception e) {
            result.put("error", -1);
            result.put("msg", "error");
            result.put("data", e.getMessage());
        }

        return new JsonRepresentation(result);
    }

    public void sendGroupMessage(int fromUid, int groupId, String message) throws Exception {
        GroupInfo groupInfo = groupService.getGroupInfo(groupId);
        for (int toUid : groupInfo.getUids()) {
            launcher.sendData(fromUid, toUid, message, true, groupId);
        }

    }

    public void sendSingleMessage(int fromUid, int toUid, String message) throws Exception {
        launcher.sendData(fromUid, toUid, message, true, 0);
    }


}
