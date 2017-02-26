package com.rong360.im.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chao.cheng on 2016/4/15.
 */
public class SimpleHttpClient {

    public static SimpleHttpClient getDefaultHttpClient() {
        Map<String, String> requestProperty = new HashMap<>();
        requestProperty.put("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/49.0.2623.87 Safari/537.36");
        return new SimpleHttpClient(5000, 5000, requestProperty);
    }

    private int connectTimeOut;
    private int readTimeOut;
    private Map<String, String> requestProperty;

    public SimpleHttpClient(int connectTimeOut, int readTimeOut, Map<String, String> requestProperty) {
        this.connectTimeOut = connectTimeOut;
        this.readTimeOut = readTimeOut;
        this.requestProperty = requestProperty;
    }

    public HttpURLConnection getConnection(String url, String method) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setReadTimeout(this.readTimeOut);
            connection.setConnectTimeout(this.connectTimeOut);
            for (Map.Entry<String, String> entry : requestProperty.entrySet()) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
            connection.setUseCaches(false);
            connection.setRequestMethod(method);
            if (!"GET".equals(method)) {
                connection.setDoOutput(true);
            }
            return connection;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String invoke(HttpURLConnection connection) throws IOException {
        InputStream inputStream = connection.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        StringBuilder builder = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\r\n");
        }
        return builder.toString();
    }

    public String get(String url) {
        HttpURLConnection connection = getConnection(url, "GET");
        try {
            return invoke(connection);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }

    public String post(String url, Map<String, String> postParams) {
        HttpURLConnection connection = getConnection(url, "POST");
        try {
            OutputStream out = connection.getOutputStream();
            out.write(getQueryString(postParams).getBytes());
            out.flush();
            out.close();
            return invoke(connection);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            connection.disconnect();
        }
    }


    private String getQueryString(Map<String, String> postParams) {
        if (postParams == null || postParams.isEmpty()) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, String> entry : postParams.entrySet()) {
            builder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return builder.toString();
    }

}
