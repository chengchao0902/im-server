package tools.utils;

import java.util.Collection;
import java.util.Map;

public final class CheckArgs {
	public static <T> boolean isEmpty(T[] columnNames) {
		return columnNames == null || columnNames.length == 0;
	}
	
	public static boolean isTrimEmpty(String str) {
		return isEmpty(str) || str.trim().isEmpty();
	}

	public static boolean isEmpty(String string) {
		return string == null || string.isEmpty();
	}

	public static <T> boolean isNull(T o) {
		return o == null;
	}

	public static boolean isEmpty(Collection<?> columns) {
		return columns == null || columns.isEmpty();
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return map == null || map.isEmpty();
	}
}
