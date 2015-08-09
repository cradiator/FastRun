package com.sysdbg.fastrun.util;

/**
 * Created by crady on 8/5/2015.
 */
public class StringUtils {
    public static final String EMPTY_STRING = "";

    public static boolean isStringEmpty(String s) {
        return (s == null || s.length() == 0);
    }

    public static boolean isStringNotEmpty(String s) {
        return !isStringEmpty(s);
    }

}
