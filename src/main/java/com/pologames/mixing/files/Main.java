package com.pologames.mixing.files;

import com.pologames.mixing.files.actions.Menu;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Все просто: Перенос файлов во временное хранилище и обратно, для изменения "Даты изменения" - в плеере сортировка по этой дате((
 */
public class Main {
    private static final Logger LOG = Logger.getLogger("Main");

    public static void main(String args[]) throws Exception {
        final Menu menu = new Menu();
        menu.start();
//        LOG.info("Start main args:" + Arrays.toString(args));
//
//        if (args.length != 1) {
//            System.err.println("Необходим один параметр - Путь до каталога с файлами");
//            System.exit(0);
//        }
    }
}
