package com.sysdbg.fastrun.util;

import java.util.List;

/**
 * Created by crady on 8/5/2015.
 */
public class ArgumentsUtils {
    public static String argumentsListToString(List<String> arguments) {
        if (arguments == null || arguments.size() == 0) {
            return StringUtils.EMPTY_STRING;
        }

        StringBuilder sb = new StringBuilder();
        boolean first_arg = true;
        for(String s : arguments) {
            if (!first_arg) {
                sb.append(" ");
            }

            sb.append("\"");
            sb.append(s);
            sb.append("\"");

            first_arg = false;
        }

        return sb.toString();
    }
}
