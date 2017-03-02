
package net.openmob.mobileimsdk.server.protocal.c;

public class PLoginInfo {
    private int uid = -1;
    private String loginName = null;
    private String phone = null;
    private String deviceId = null;
    private String deviceInfo = null;
    private String loginPsw = null;
    private String extra = null;

    public PLoginInfo(String loginName, String loginPsw) {
        this(loginName, loginPsw, null);
    }

    public PLoginInfo(String loginName, String loginPsw, String extra) {
        this.loginName = loginName;
        this.loginPsw = loginPsw;
        this.extra = extra;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getLoginName() {
        return this.loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getLoginPsw() {
        return this.loginPsw;
    }

    public void setLoginPsw(String loginPsw) {
        this.loginPsw = loginPsw;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
}