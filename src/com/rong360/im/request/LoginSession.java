package com.rong360.im.request;

import com.rong360.im.common.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/26.
 */
public class LoginSession implements HttpRequest {
    private int id;
    private String sessionId;
    private int userId;
    private byte stat;
    private Date createTime;

    public LoginSession() {
        createTime = new Date();
        stat = 1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public byte getStat() {
        return stat;
    }

    public void setStat(byte stat) {
        this.stat = stat;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Map<String, String> toRequestParam() {
        Map<String, String> map = new HashMap<>();
        map.put("id", String.valueOf(getId()));
        map.put("sid", getSessionId());
        map.put("uid", String.valueOf(getUserId()));
        map.put("stat", String.valueOf(getStat()));
        map.put("create_time", Utils.dateToString(getCreateTime()));
        return map;
    }

}
