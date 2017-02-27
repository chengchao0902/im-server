package tools.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class TypeUtils {
	private static final SimpleDateFormat DEF_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public static Date toDate(String target, DateFormat format) throws ParseException {
		try {
			return format.parse(target);
		} catch (ParseException e) {
			return DEF_FORMAT.parse(target);
		}
	}
	
	public static String toString(Object o) {
		if(o == null) {
			return "";
		}
		return o.toString();
	}
	
	public static String toString(Date date, DateFormat format) {
		return format.format(date);
	}
	
	public static Date toDate(String target, String pattern) throws ParseException {
		if(pattern == null || pattern.isEmpty()) {
			return DEF_FORMAT.parse(target);
		}
		return new SimpleDateFormat(pattern).parse(target);
	}
	
	public static String toString(Date date, String format) {
		if(format == null || format.isEmpty()) {
			return DEF_FORMAT.format(date);
		}
		return new SimpleDateFormat(format).format(date);
	}
	
	public static int toInt(Object o, int def) {
		if(o == null)
			return def;
		try {
			return Integer.parseInt(o.toString());
		} catch (Exception e) {
			return def;
		}
	}
	
	public static double toDouble(Object o, double def) {
		if(o == null)
			return def;
		try {
			return Double.parseDouble(o.toString());
		} catch (Exception e) {
			return def;
		}
	}
	
	public static long toLong(Object o, long def) {
		if(o == null)
			return def;
		try {
			return Long.parseLong(o.toString());
		} catch (Exception e) {
			return def;
		}
	}

	public static boolean toBoolean(Object o, boolean def) {
		if(o == null) {
			return def;
		}
		try {
			return Boolean.parseBoolean(o.toString().trim());
		} catch (Exception e) {
			return def;
		}
	}
}
