package com.rong360.im;

import net.openmob.mobileimsdk.server.event.MessageQoSEventListenerS2C;
import net.openmob.mobileimsdk.server.protocol.Protocol;

import java.util.ArrayList;

/**
 * Created by chengchao on 2017/2/25.
 */
public class MsgQoSEventS2CListener implements MessageQoSEventListenerS2C {
    @Override
    public void messagesLost(ArrayList<Protocol> paramArrayList) {

    }

    @Override
    public void messagesBeReceived(String paramString) {

    }
}
