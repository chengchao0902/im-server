
package net.openmob.mobileimsdk.server.protocal;

import com.google.gson.Gson;

import java.util.UUID;

public class Protocal {
    private int type = 0;
    private String dataContent = null;
    private int from = -1;
    private int to = -1;
    private int gid = -1;
    private String fp = null;
    private boolean QoS = false;
    private transient int retryCount = 0;

    public Protocal(int type, String dataContent, int from, int to) {
        this(type, dataContent, from, to, false, null);
    }

    public Protocal(int type, String dataContent, int from, int to, boolean QoS, String fingerPrint) {
        this.type = type;
        this.dataContent = dataContent;
        this.from = from;
        this.to = to;
        this.QoS = QoS;

        // 只有在需要QoS支持时才生成指纹，否则浪费数据传输流量
        // 目前一个包的指纹只在对象建立时创建哦
        if ((QoS) && (fingerPrint == null))
            this.fp = genFingerPrint();
        else
            this.fp = fingerPrint;
    }

    public int getGid() {
        return gid;
    }

    public void setGid(int gid) {
        this.gid = gid;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDataContent() {
        return this.dataContent;
    }

    public void setDataContent(String dataContent) {
        this.dataContent = dataContent;
    }

    public int getFrom() {
        return this.from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return this.to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public String getFp() {
        return this.fp;
    }

    public int getRetryCount() {
        return this.retryCount;
    }

    public void increaseRetryCount() {
        this.retryCount += 1;
    }

    public boolean isQoS() {
        return this.QoS;
    }

    public boolean isGroup() {
        return gid != -1;
    }

    public String toGsonString() {
        return new Gson().toJson(this);
    }

    public byte[] toBytes() {
        return CharsetHelper.getBytes(toGsonString());
    }

    public Object clone() {
        // 克隆一个Protocal对象（该对象已重置retryCount数值为0）
        Protocal cloneP = new Protocal(getType(),
                getDataContent(), getFrom(), getTo(), isQoS(), getFp());
        cloneP.setGid(getGid());
        return cloneP;
    }

    public static String genFingerPrint() {
        return UUID.randomUUID().toString();
    }
}