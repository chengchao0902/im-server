package com.rong360.im.request;

import com.rong360.im.common.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class Message implements HttpRequest {
    private int id;
    private int fromUid;
    private int toUid;
    private int groupId;
    private String message;
    private Date createTime;

    public Message() {
        createTime = new Date();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFromUid() {
        return fromUid;
    }

    public void setFromUid(int fromUid) {
        this.fromUid = fromUid;
    }

    public int getToUid() {
        return toUid;
    }

    public void setToUid(int toUid) {
        this.toUid = toUid;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        jsonMap.put("from_uid", String.valueOf(getFromUid()));
        jsonMap.put("to_uid", String.valueOf(getToUid()));
        jsonMap.put("gid", String.valueOf(getGroupId()));
        jsonMap.put("msg", getMessage());
        jsonMap.put("create_time", Utils.dateToString(getCreateTime()));
        return jsonMap;
    }
}
