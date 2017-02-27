package com.rong360.im.service.remote;

import com.google.gson.Gson;
import com.rong360.im.common.CheckArgs;
import com.rong360.im.common.SimpleHttpClient;
import com.rong360.im.request.HttpRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public abstract class HttpService {


    private SimpleHttpClient httpClient() {
        return SimpleHttpClient.getDefaultHttpClient();
    }

    public Map<String, Object> get(String url) {
        String response = httpClient().get(url);
        return toMap(response);
    }

    public Map<String, Object> post(String url, Map<String, String> params) {
        String response = httpClient().post(url, params);
        return toMap(response);
    }

    private Map<String, Object> toMap(String response) {
        return new Gson().fromJson(response, HashMap.class);
    }

    public static Map<String, String> buildMapParams(String... kv) {
        if (kv == null) return null;
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < kv.length - 1; i++) {
            params.put(kv[i], kv[++i]);
        }
        if (kv.length % 2 != 0) {
            params.put(kv[kv.length -1], "");
        }
        return params;

    }




}
