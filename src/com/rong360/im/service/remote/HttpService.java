package com.rong360.im.service.remote;

import com.rong360.im.common.SimpleHttpClient;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chengchao on 2017/2/25.
 */
public abstract class HttpService {


    private SimpleHttpClient httpClient() {
        return SimpleHttpClient.getDefaultHttpClient();
    }

    public JSONObject get(String url) {
        String response = httpClient().get(url);
        return toMap(response);
    }

    public JSONObject post(String url, Map<String, String> params) {
        String response = httpClient().post(url, params);
        return toMap(response);
    }

    private JSONObject toMap(String response) {
        return new JSONObject(response);
    }

    public static Map<String, String> buildMapParams(String... kv) {
        if (kv == null) return null;
        Map<String, String> params = new HashMap<>();
        for (int i = 0; i < kv.length - 1; i++) {
            params.put(kv[i], kv[++i]);
        }
        if (kv.length % 2 != 0) {
            params.put(kv[kv.length - 1], "");
        }
        return params;

    }


}
