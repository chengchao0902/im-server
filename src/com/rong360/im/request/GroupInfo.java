package com.rong360.im.request;

import com.google.gson.Gson;
import com.rong360.im.utils.Utils;

import java.util.*;


/**
 * Created by chengchao on 2017/2/25.
 */
public class GroupInfo implements HttpRequest {
    private int id;
    private int groupId;
    private List<Integer> uids;
    private Date createTime;

    public GroupInfo() {
        uids = new ArrayList<>();
        createTime = new Date();
    }

    public void addUser(int uid) {
        uids.add(uid);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public List<Integer> getUids() {
        return uids;
    }

    public void setUids(List<Integer> uids) {
        this.uids = uids;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toJson() {
        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("id", getId());
        jsonMap.put("gid", getGroupId());
        jsonMap.put("uids", getUids());
        jsonMap.put("create_time", Utils.dateToString(getCreateTime()));
        return new Gson().toJson(jsonMap);
    }
}
