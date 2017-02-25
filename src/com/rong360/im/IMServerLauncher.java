package com.rong360.im;

import net.openmob.mobileimsdk.server.ServerLauncher;
import net.openmob.mobileimsdk.server.qos.QoS4SendDaemonS2C;

import java.io.IOException;

/**
 * Created by chengchao on 2017/2/25.
 */
public class IMServerLauncher extends ServerLauncher {

    private static IMServerLauncher launcher = null;

    public static IMServerLauncher getLauncher() throws IOException {
        if (launcher == null) {
            ServerLauncher.appKey = "5418023dfd98c579b6001741";
            QoS4SendDaemonS2C.DEBUG = true;
            IMServerLauncher.PORT = 7901;
            IMServerLauncher.setSenseMode(SenseMode.MODE_10S);
            launcher = new IMServerLauncher();
        }
        return launcher;
    }

    public IMServerLauncher() throws IOException {
        super();
    }

    @Override
    protected void initListeners() {
        this.setServerEventListener(new ServerEventListenerImpl());
        this.setServerMessageQoSEventListener(new MsgQoSEventS2CListener());
    }

    public static void main(String[] args) throws IOException {
        IMServerLauncher.getLauncher().startup();
    }
}
