
package net.openmob.mobileimsdk.server.event;

import java.util.ArrayList;

import net.openmob.mobileimsdk.server.protocal.Protocal;

public abstract interface MessageQoSEventListenerS2C {
    public abstract void messagesLost(ArrayList<Protocal> paramArrayList);

    public abstract void messagesBeReceived(String paramString);
}