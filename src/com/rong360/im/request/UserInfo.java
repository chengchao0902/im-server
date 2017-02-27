package com.rong360.im.request;

import com.rong360.im.common.Utils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public class UserInfo implements HttpRequest {
    private int id;
    private String username;
    private String phone;
    private String sessionId;
    private String password;
    private String deviceId;
    private String deviceInfo;
    private Date createTime;
    private Date updateTime;

    public UserInfo() {
        createTime = new Date();
        updateTime = new Date();
    }

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
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
        jsonMap.put("username", getUsername());
        jsonMap.put("phone", getPhone());
        jsonMap.put("sid", getSessionId());
        jsonMap.put("password", getPassword());
        jsonMap.put("device_id", getDeviceId());
        jsonMap.put("device_info", getDeviceInfo());
        jsonMap.put("create_time", Utils.dateToString(getCreateTime()));
        jsonMap.put("update_time", Utils.dateToString(getUpdateTime()));
        return jsonMap;
    }
}
