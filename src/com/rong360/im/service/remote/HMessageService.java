package com.rong360.im.service.remote;

import com.rong360.im.Config;
import com.rong360.im.common.Utils;
import com.rong360.im.request.Message;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HMessageService extends HttpService {

    private static final String SAVE_OFFLINE_MSG = Config.getString("http.message.save");
    private static final String GET_OFFLINE_MSG = Config.getString("http.message.get");

    public boolean saveOfflineMsg(Message message) {
        JSONObject response = post(SAVE_OFFLINE_MSG, message.toRequestParam());
        int error = response.getInt("error");
        return 0 == error;
    }

    public List<Message> getOfflineMsg(int userId) {
        JSONObject response = post(GET_OFFLINE_MSG, buildMapParams("uid", String.valueOf(userId)));
        int error = response.getInt("error");
        if (0 != error) {
            return null;
        }
        JSONArray list = response.getJSONObject("data").getJSONArray("list");
        List<Message> messages = new ArrayList<>();
        for (int i = 0; i < list.length(); i++) {
            JSONObject item = list.getJSONObject(i);
            Message message = new Message();
            message.setId(item.getInt("id"));
            message.addToUid(item.getInt("uid"));
            message.setFromUid(item.getInt("from_uid"));
            message.setGroupId(item.getInt("gid"));
            message.setMessage(item.getString("msg"));
            message.setCreateTime(Utils.stringToDate(item.getString("create_time")));
            messages.add(message);
        }
        return messages;
    }

}
