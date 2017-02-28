package com.rong360.im.rest;

import org.json.JSONObject;
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
        Representation result = client.post(null) ;

        JsonRepresentation jr = new JsonRepresentation(result);
        JSONObject movie = jr.getJsonObject();
        System.out.printf("name:%s size:%d minutes:%d" ,
                movie.get("name") ,
                movie.get("size"),
                movie.get("minutes"));
    }
}
