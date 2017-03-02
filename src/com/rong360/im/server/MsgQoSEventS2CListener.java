package com.rong360.im.server;

import net.openmob.mobileimsdk.server.event.MessageQoSEventListenerS2C;
import net.openmob.mobileimsdk.server.protocal.Protocal;

import java.util.ArrayList;

/**
 * Created by chengchao on 2017/2/25.
 */
public class MsgQoSEventS2CListener implements MessageQoSEventListenerS2C {
    @Override
    public void messagesLost(ArrayList<Protocal> paramArrayList) {
        //// TODO: 2017/3/2  保存消息到数据库中

    }

    @Override
    public void messagesBeReceived(String paramString) {
        //// TODO: 2017/3/2  从库中删除消息
    }
}
