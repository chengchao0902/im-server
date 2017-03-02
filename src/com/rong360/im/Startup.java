package com.rong360.im;

import com.rong360.im.rest.MessageSenderResource;
import com.rong360.im.rest.RestMain;
import com.rong360.im.server.IMServerLauncher;
import net.openmob.mobileimsdk.server.ServerLauncher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by chengchao on 2017/2/28.
 */
public final class Startup {

    private static final Logger logger = LoggerFactory.getLogger(Startup.class);

    public static void main(String[] args) throws Exception {
        int imPort = Config.getInt("im.port", 7777);
        int restPort = Config.getInt("im.http.port", 8888);
        boolean debug = Config.getBoolean("debug", false);
        ServerLauncher.SenseMode mode = ServerLauncher.SenseMode.valueOf(Config.getString("mode"));
        ServerLauncher launcher = new IMServerLauncher(imPort, debug, mode);
        RestMain restMain = new RestMain(restPort);
        MessageSenderResource.launcher = launcher;
        restMain.attach("/api/sendmsg", MessageSenderResource.class);
        launcher.startup();
        logger.info("[Startup]im server start done.");
        restMain.startup();
        logger.info("[Startup]rest server start done.");
    }
}
