package com.pologames.mixing.files.logger;

public class Logger {

    public static void info(String msg, Object... args) {
        System.out.println(format(msg, args));
    }

    public static void error(String msg, Object... args) {
        System.err.println(format(msg, args));
    }

    private static String format(String msg, Object... args) {
        final StringBuilder sb = new StringBuilder(msg.length());
        int beginIndex = 0;
        for (Object arg : args) {
            int endIndex = msg.indexOf("{}", beginIndex);
            if (endIndex != -1) {
                sb.append(msg, beginIndex, endIndex);
                sb.append(arg);
                beginIndex = endIndex + 2;
            }
        }

        if (beginIndex < msg.length()) {
            sb.append(msg, beginIndex, msg.length());
        }

        return sb.toString();
    }
}
