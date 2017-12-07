package com.pologames.mixing.files;

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
        LOG.info("Start main args:" + Arrays.toString(args));

        if (args.length != 1) {
            System.err.println("Необходим один параметр - Путь до каталога с файлами");
            System.exit(0);
        }
        final File pathSrc = new File(args[0]);
        if (!pathSrc.isDirectory()) {
            System.err.println("Необходим указать каталог с файлами");
            System.exit(0);
        }

        final File pathTmp = new File(pathSrc.getAbsolutePath() + "_tmp");

        LOG.info("Create new Path:" + pathTmp.getAbsolutePath());

        if (pathTmp.mkdir()) {
            copyPathWithRandomTime(pathSrc, pathTmp, 0);

            pathSrc.delete();
            pathTmp.renameTo(pathSrc);
        }
    }

    public static void copyPathWithRandomTime(File pathOut, File pathIn, long delay) throws Exception {
        final List<String> files = new ArrayList<>(Arrays.asList(pathOut.list()));
        LOG.info("Start coping from " + pathOut.getAbsolutePath() + " to " + pathIn.getAbsolutePath());
        while (files.size() != 0) {
            final String fileName = files.get((int) (Math.random() * files.size()));
            LOG.info("File:" + fileName + ". Start copy");
            try (final InputStream in = new FileInputStream(new File(pathOut.getPath(), fileName));
                 final OutputStream out = new FileOutputStream(new File(pathIn.getPath(), fileName))) {
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
            }
            files.remove(fileName);
            new File(pathOut.getPath(), fileName).delete();
            LOG.info("File:"+fileName + " was copy and Delete. Left size:" + files.size());
            Thread.sleep(delay);
        }
    }
}
