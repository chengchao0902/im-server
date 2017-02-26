package com.rong360.im.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chengchao on 2017/2/25.
 */
public final class Utils {
    public static String dateToString(Date date) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Date stringToDate(String sDate) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(sDate);
        } catch (ParseException e) {
            return new Date();
        }
    }

    public static String joinList(List<?> list, String spliter) {
        if (list == null) {
            return "";
        }
        spliter = spliter == null ? "," : spliter;
        StringBuilder builder = new StringBuilder();
        for (Object item : list) {
            builder.append(String.valueOf(item)).append(spliter);
        }
        if (builder.length() >= spliter.length()) {
            builder.replace(builder.length() - spliter.length(), builder.length(), "");
        }
        return builder.toString();
    }

}
