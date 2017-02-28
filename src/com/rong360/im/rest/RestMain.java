package com.rong360.im.rest;

import com.rong360.im.common.TypeUtils;
import org.json.JSONObject;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.ClientResource;

import java.io.IOException;

/**
 * Created by chengchao on 2017/2/28.
 */
public final class RestMain {

    public static void main(String[] args) throws IOException {
        ClientResource client = new ClientResource("http://localhost:8888/sender");

        //获取返回结果
        Representation result = client.post(null);

        JsonRepresentation jr = new JsonRepresentation(result);
        JSONObject movie = jr.getJsonObject();
        System.out.printf("name:%s size:%d minutes:%d",
                movie.get("name"),
                movie.get("size"),
                movie.get("minutes"));


    }

    public void main1(String[] args) throws Exception {
        int port = 8888;
        if (args != null && args.length != 0) {
            for (int i = 0; i < args.length; i++) {
                if ("-p".equals(args[i]) && i < args.length - 1) {
                    port = TypeUtils.toInt(args[i + 1], port);
                }
            }
        }

        Component component = new Component();
        component.getServers().add(Protocol.HTTP, port);

        component.getDefaultHost().attach("/sender", MessageSenderResource.class);
        component.start();
    }
}
