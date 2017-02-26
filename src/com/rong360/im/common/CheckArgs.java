package com.rong360.im.common;

/**
 * Created by chengchao on 2017/2/26.
 */
public final class CheckArgs {
    public final static class Strings {
        public static boolean isEmpty(String str) {
            return str == null || str.trim().isEmpty();
        }

        public static boolean isNotEmpty(String str) {
            return !isEmpty(str);
        }

        public static boolean isAllEmpty(String... strings) {
            if (strings == null || strings.length == 0) {
                return true;
            }
            for (String str : strings) {
                if (isNotEmpty(str)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isAnyNotEmpty(String... strings) {
            return !isAllEmpty(strings);
        }

        public static boolean isAllNotEmpty(String... strings) {
            if (strings == null || strings.length == 0) {
                return false;
            }
            for (String str : strings) {
                if (isEmpty(str)) {
                    return false;
                }
            }
            return true;
        }

        public static boolean isAnyEmpty(String... strings) {
            return !isAllNotEmpty(strings);
        }
    }


}
