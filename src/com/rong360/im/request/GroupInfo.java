package com.rong360.im.request;

import com.rong360.im.common.Utils;

import java.util.*;


/**
 * Created by chengchao on 2017/2/25.
 */
public class GroupInfo implements HttpRequest {
    private int id;
    private int groupId;
    private String name;
    private List<Integer> uids;
    private Date createTime;

    public GroupInfo() {
        uids = new ArrayList<>();
        createTime = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public Map<String, String> toRequestParam() {
        Map<String, String> jsonMap = new HashMap<>();
        jsonMap.put("id", String.valueOf(getId()));
        jsonMap.put("name", getName());
        jsonMap.put("gid", String.valueOf(getGroupId()));
        jsonMap.put("uids", Utils.joinList(getUids(), null));
        jsonMap.put("create_time", Utils.dateToString(getCreateTime()));
        return jsonMap;
    }

    public static void main(String[] args) {
        System.out.println(new GroupInfo().toRequestParam());
    }
}
