package com.rong360.im.service.remote;

import com.rong360.im.common.Utils;
import com.rong360.im.request.Message;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HMessageService extends HttpService {

    private static final String SAVE_OFFLINE_MSG = "http://im.rong360.com/msg/saveOfflineMsg";
    private static final String GET_OFFLINE_MSG = "http://im.rong360.com/msg/getofflinemsg";

    public boolean saveOfflineMsg(Message message) {
        Map<String, Object> response = post(SAVE_OFFLINE_MSG, message.toRequestParam());
        int error = (int) response.get("error_code");
        return 0 == error;
    }

    public List<Message> getOfflineMsg(int userId) {
        Map<String, Object> response = post(GET_OFFLINE_MSG, buildMapParams("uid", String.valueOf(userId)));
        int error = (int) response.get("error_code");
        if (0 != error) {
            return null;
        }
        Map<String, Object>[] list = (Map<String, Object>[]) ((Map) response.get("data")).get("list");
        List<Message> messages = new ArrayList<>();
        for (Map<String, Object> item : list) {
            Message message = new Message();
            message.setId(Integer.parseInt(item.get("id").toString()));
            message.addToUid(Integer.parseInt(item.get("uid").toString()));
            message.setFromUid(Integer.parseInt(item.get("from_uid").toString()));
            message.setGroupId(Integer.parseInt(item.get("gid").toString()));
            message.setMessage(item.get("msg").toString());
            message.setCreateTime(Utils.stringToDate(item.get("create_time").toString()));
            messages.add(message);
        }
        return messages;
    }

}
