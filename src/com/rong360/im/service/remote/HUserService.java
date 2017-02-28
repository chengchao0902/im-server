package com.rong360.im.service.remote;

import com.google.gson.Gson;
import com.rong360.im.common.Utils;
import com.rong360.im.request.UserInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HUserService extends HttpService {

    private static final String LOGIN = "http://im.rong360.com/user/login";
    private static final String LOGOUT = "http://im.rong360.com/user/unlogin";
    private static final String USERINFO = "http://im.rong360.com/user/userinfo";
    private static final String SAVE_FRIEND = "http://im.rong360.com/user/savefriend";
    private static final String FRIEND_LIST = "http://im.rong360.com/user/getfriends";

    public int login(String deviceId, String phone, String deviceInfo, String sessionId) {
        Map<String, String> request = new HashMap<>();
        request.put("device_id", deviceId);
        request.put("phone", phone);
        request.put("device_info", deviceInfo);
        request.put("sid", sessionId);
        Map<String, Object> response = post(LOGIN, request);
        int error = (int) response.get("error");
        if (0 != error) {
            return -1;
        }
        return Integer.valueOf(((Map) (response.get("data"))).get("uid").toString());
    }

    public UserInfo getUserInfo(int userId) {
        Map<String, Object> response = post(USERINFO, buildMapParams("uid", String.valueOf(userId)));
        int error = (int) response.get("error");
        if (0 != error) {
            return null;
        }
        Map dataMap = (Map) response.get("data");
        UserInfo userInfo = new UserInfo();
        userInfo.setId(Integer.valueOf(dataMap.get("uid").toString()));
        userInfo.setPhone(dataMap.get("phone").toString());
        userInfo.setSessionId(dataMap.get("sid").toString());
        userInfo.setDeviceId(dataMap.get("device_id").toString());
        userInfo.setDeviceInfo(dataMap.get("device_info").toString());
        userInfo.setCreateTime(Utils.stringToDate(dataMap.get("create_time").toString()));
        return userInfo;
    }

    public boolean saveFriend(int userId, int friendId) {
        Map<String, Object> response = post(SAVE_FRIEND, buildMapParams("uid", String.valueOf(userId), "friend_id", String.valueOf(friendId)));
        int error = (int) response.get("error");
        return error == 0;
    }

    public boolean logout(int userId) {
        Map<String, Object> response = post(LOGOUT, buildMapParams("uid", String.valueOf(userId)));
        int error = (int) response.get("error_code");
        return error == 0;
    }

    public List<UserInfo> getFrindes(int userId) {
        Map<String, Object> response = post(FRIEND_LIST, buildMapParams("uid", String.valueOf(userId)));
        int error = (int) response.get("error_code");
        if (0 != error) {
            return null;
        }
        Map<String, Object> list = (Map<String, Object>) ((Map) response.get("data")).get("list");
        List<UserInfo> userInfoList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : list.entrySet()) {
            UserInfo userInfo = new UserInfo();
            userInfo.setId(Integer.valueOf(entry.getKey()));
            Map userInfoMap = (Map) entry.getValue();
            userInfo.setUsername(userInfoMap.get("name").toString());
            userInfo.setPhone(userInfoMap.get("phone").toString());
            userInfo.setSessionId(userInfoMap.get("sid").toString());
            userInfo.setDeviceId(userInfoMap.get("device_id").toString());
            userInfo.setDeviceInfo(userInfoMap.get("device_info").toString());
            userInfo.setCreateTime(Utils.stringToDate(userInfoMap.get("create_time").toString()));
            userInfo.setUpdateTime(Utils.stringToDate(userInfoMap.get("update_time").toString()));
            userInfoList.add(userInfo);
        }
        return userInfoList;
    }


    public static void main(String[] args) {
        String str = "{\n" +
                "         \"error\": 0,\n" +
                "         \"msg\" : \"success\",\n" +
                "         \"data\": {\n" +
                "         \"uid\": \"1000001\"," +
                "\"uids\": [123,333,22222]" +
                "         }\n" +
                "         }";
        Map<String, Object> response = new Gson().fromJson(str, HashMap.class);
        System.out.println(((Map) (response.get("data"))).get("uids").getClass());
    }

}
