package com.rong360.im.service.remote;

import com.rong360.im.common.Utils;
import com.rong360.im.request.GroupInfo;

import java.util.List;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class HGroupService extends HttpService {

    private static final String CREATE = "";
    private static final String JOIN = "";
    private static final String LEAVE = "";
    private static final String GET = "";

    public int createGroup(String name, List<Integer> uids) {
        Map<String,Object> response = post(CREATE, buildMapParams("name", name, "uids", Utils.joinList(uids, null)));
        int error = (int) response.get("error");
        if (error != 0) {
            return -1;
        }
        return Integer.parseInt(((Map)response.get("data")).get("gid").toString());
    }

    public int joinGroup(int uid, int gid) {
        Map<String,Object> response = post(JOIN, buildMapParams("uid", String.valueOf(uid), "gid",String.valueOf(gid)));
        int error = (int) response.get("error");
        if (error != 0) {
            return -1;
        }
        return Integer.parseInt(((Map)response.get("data")).get("gid").toString());
    }

    public boolean leaveGroup(int uid, int gid) {
        Map<String,Object> response = post(LEAVE, buildMapParams("uid", String.valueOf(uid), "gid",String.valueOf(gid)));
        int error = (int) response.get("error");
        return 0 == error;
    }

    public GroupInfo getGroupInfo(int gid) {
        Map<String, Object> response = post(GET, buildMapParams("gid", String.valueOf(gid)));
        int error = (int) response.get("error");
        if (error != 0) {
            return null;
        }
        Map data = (Map) response.get("data");
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(Integer.parseInt(data.get("gid").toString()));
        groupInfo.setName(data.get("name").toString());
        groupInfo.setUids((List<Integer>) data.get("uids"));
        return groupInfo;
    }



}
