package com.rong360.im.rest;

import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.resource.ServerResource;

/**
 * Created by chengchao on 2017/2/28.
 */
public final class RestMain {

    private int port;
    private Component component;

    public RestMain(int port) {
        this.port = port;
        this.component = new Component();
        this.component.getServers().add(Protocol.HTTP, this.port);
    }

    public void attach(String path, Class<? extends ServerResource> resourceClass) {
        this.component.getDefaultHost().attach(path, resourceClass);
    }

    public void startup() throws Exception {
        component.start();
    }

    public void stop() throws Exception {
        this.component.stop();
    }
}
