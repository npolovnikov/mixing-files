package com.pologames.mixing.files.reader;

import com.pologames.mixing.files.logger.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {
    final static private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static String readString(String name) {
        Logger.info("Введите: {}", name);
        return readLine();
    }

    public static Integer readInt(String name) {
        Logger.info("Введите: {}", name);
        Integer result = null;
        while (result == null) {
            try {
                result = Integer.valueOf(readLine());
            } catch (NumberFormatException e) {
                Logger.error(e.getMessage());
            }
        }
        return result;
    }

    private static String readLine() {
        try{
            return reader.readLine();
        } catch (IOException e) {
            Logger.error(e.getMessage());
            return "";
        }
    }

}
