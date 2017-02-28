package com.rong360.im.service.remote;

import com.rong360.im.Config;
import com.rong360.im.common.Utils;
import com.rong360.im.request.GroupInfo;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HGroupService extends HttpService {

    private static final String CREATE = Config.getString("http.group.create");
    private static final String JOIN = Config.getString("http.group.join");
    private static final String LEAVE = Config.getString("http.group.leave");
    private static final String GET = Config.getString("http.group.get");

    public int createGroup(String name, List<Integer> uids) {
        JSONObject response = post(CREATE, buildMapParams("name", name, "uids", Utils.joinList(uids, null)));
        int error = response.getInt("error");
        if (error != 0) {
            return -1;
        }
        return Integer.parseInt(((Map) response.get("data")).get("gid").toString());
    }

    public int joinGroup(int uid, int gid) {
        JSONObject response = post(JOIN, buildMapParams("uid", String.valueOf(uid), "gid", String.valueOf(gid)));
        int error = response.getInt("error");
        if (error != 0) {
            return -1;
        }
        return Integer.parseInt(((Map) response.get("data")).get("gid").toString());
    }

    public boolean leaveGroup(int uid, int gid) {
        JSONObject response = post(LEAVE, buildMapParams("uid", String.valueOf(uid), "gid", String.valueOf(gid)));
        int error = response.getInt("error");
        return 0 == error;
    }

    public GroupInfo getGroupInfo(int gid) {
        JSONObject response = post(GET, buildMapParams("gid", String.valueOf(gid)));
        int error = response.getInt("error");
        if (error != 0) {
            return null;
        }
        JSONObject data = response.getJSONObject("data");
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(data.getInt("gid"));
        groupInfo.setName(data.getString("name"));
        JSONArray uids = data.getJSONArray("uids");
        List<Integer> uidList = new ArrayList<>();
        for (int i = 0; i < uids.length(); i++) {
            uidList.add(uids.getInt(i));
        }
        groupInfo.setUids(uidList);
        return groupInfo;
    }


}
