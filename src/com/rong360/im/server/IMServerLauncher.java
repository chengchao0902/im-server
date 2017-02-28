package com.rong360.im.server;

import net.openmob.mobileimsdk.server.ServerCoreHandler;
import net.openmob.mobileimsdk.server.ServerLauncher;
import net.openmob.mobileimsdk.server.qos.QoS4SendDaemonS2C;

import java.io.IOException;

/**
 * Created by chengchao on 2017/2/25.
 */
public class IMServerLauncher extends ServerLauncher {


    public IMServerLauncher(int port, boolean isDebug, SenseMode mode) throws IOException {
        super();
        QoS4SendDaemonS2C.DEBUG = isDebug;
        IMServerLauncher.PORT = port;
        IMServerLauncher.setSenseMode(mode);
    }

    @Override
    protected ServerCoreHandler initServerCoreHandler() {
        return new IMServerHandler();
    }

    @Override
    protected void initListeners() {
        this.setServerEventListener(new ServerEventListenerImpl(this));
        this.setServerMessageQoSEventListener(new MsgQoSEventS2CListener());
    }

}
