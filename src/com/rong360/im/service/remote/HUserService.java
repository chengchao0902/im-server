package com.rong360.im.service.remote;

import com.rong360.im.Config;
import com.rong360.im.common.TypeUtils;
import com.rong360.im.common.Utils;
import com.rong360.im.request.UserInfo;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HUserService extends HttpService {

    private static final String LOGIN = Config.getString("http.user.login");
    private static final String LOGOUT = Config.getString("http.user.logout");
    private static final String USERINFO = Config.getString("http.user.info");
    private static final String SAVE_FRIEND = Config.getString("http.user.save_friend");
    private static final String FRIEND_LIST = Config.getString("http.user.friend_list");

    public int login(String deviceId, String phone, String deviceInfo, String sessionId) {
        Map<String, String> request = new HashMap<>();
        request.put("device_id", deviceId);
        request.put("phone", phone);
        request.put("device_info", deviceInfo);
        request.put("sid", sessionId);
        JSONObject response = post(LOGIN, request);
        int error = response.getInt("error");
        if (0 != error) {
            return -1;
        }
        return response.getJSONObject("data").getInt("uid");
    }

    public UserInfo getUserInfo(int userId) {
        JSONObject response = post(USERINFO, buildMapParams("uid", String.valueOf(userId)));
        int error = response.getInt("error");
        if (0 != error) {
            return null;
        }
        JSONObject dataMap = response.getJSONObject("data");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(dataMap.getInt("uid"));
        userInfo.setPhone(dataMap.getString("phone"));
        userInfo.setSessionId(dataMap.getString("sid"));
        userInfo.setDeviceId(dataMap.getString("device_id"));
        userInfo.setDeviceInfo(dataMap.getString("device_info"));
        userInfo.setCreateTime(Utils.stringToDate(dataMap.getString("create_time")));
        return userInfo;
    }

    public boolean saveFriend(int userId, int friendId) {
        JSONObject response = post(SAVE_FRIEND, buildMapParams("uid", String.valueOf(userId), "friend_id", String.valueOf(friendId)));
        int error = response.getInt("error");
        return error == 0;
    }

    public boolean logout(int userId) {
        JSONObject response = post(LOGOUT, buildMapParams("uid", String.valueOf(userId)));
        int error = response.getInt("error");
        return error == 0;
    }

    public List<UserInfo> getFrindes(int userId) {
        JSONObject response = post(FRIEND_LIST, buildMapParams("uid", String.valueOf(userId)));
        int error = response.getInt("error");
        if (0 != error) {
            return null;
        }
        JSONObject list = response.getJSONObject("data").getJSONObject("list");
        List<UserInfo> userInfoList = new ArrayList<>();

        for (Object key : list.keySet()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(TypeUtils.toInt(key, -1));
            JSONObject userInfoMap = list.getJSONObject(key.toString());
            userInfo.setUsername(userInfoMap.getString("name"));
            userInfo.setPhone(userInfoMap.getString("phone"));
            userInfo.setSessionId(userInfoMap.getString("sid"));
            userInfo.setDeviceId(userInfoMap.getString("device_id"));
            userInfo.setDeviceInfo(userInfoMap.getString("device_info"));
            userInfo.setCreateTime(Utils.stringToDate(userInfoMap.getString("create_time")));
            userInfo.setUpdateTime(Utils.stringToDate(userInfoMap.getString("update_time")));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }

}
