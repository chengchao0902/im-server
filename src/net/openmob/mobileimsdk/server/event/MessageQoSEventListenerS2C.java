
package net.openmob.mobileimsdk.server.event;

import java.util.ArrayList;

import net.openmob.mobileimsdk.server.protocol.Protocol;

public abstract interface MessageQoSEventListenerS2C {
    public abstract void messagesLost(ArrayList<Protocol> paramArrayList);

    public abstract void messagesBeReceived(String paramString);
}