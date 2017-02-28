package com.rong360.im.rest;

import org.json.JSONObject;
import org.restlet.ext.json.JsonRepresentation;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

/**
 * Created by chengchao on 2017/2/28.
 */
public class MessageSenderResource extends ServerResource {

    @Get
    public Representation actionJson() {
        JSONObject mailEle = new JSONObject() ;
        mailEle.put("name", "速度与激情6") ;
        mailEle.put("size", 100000l) ;
        mailEle.put("minutes", 120) ;
        return new JsonRepresentation(mailEle) ;
    }


}
