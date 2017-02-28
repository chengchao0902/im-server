package com.rong360.im;

import com.rong360.im.common.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by chengchao on 2017/2/28.
 */
public final class Config {
    private static final Logger logger = LoggerFactory.getLogger(Config.class);
    private static final Properties properties;

    static {
        properties = new Properties();
        InputStream is = new BufferedInputStream(Config.class.getResourceAsStream("/config.properties"));
        try {
            properties.load(is);
        } catch (IOException e) {
            logger.warn("[Config] config.properties 加载失败。。。");
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getString(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key, int def) {
        return TypeUtils.toInt(properties.getProperty(key), def);
    }

    public static boolean getBoolean(String key, boolean def) {
        return TypeUtils.toBoolean(properties.getProperty(key), def);
    }

    public static void main(String[] args) {
        System.out.println(getString("debug"));
    }

}
