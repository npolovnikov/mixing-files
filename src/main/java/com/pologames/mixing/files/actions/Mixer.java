package com.pologames.mixing.files.actions;

import com.pologames.mixing.files.logger.Logger;
import com.pologames.mixing.files.reader.Reader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Mixer implements Startle {

    @Override
    public void start() {
        File srcDir = new File(Reader.readString("Путь до каталога с файлами"));
        while (!srcDir.isDirectory() || srcDir.list() == null) {
            Logger.error("Необходим указать каталог с файлами");
            srcDir = new File(Reader.readString("Путь до каталога с файлами"));
        }

        final File tmpDir = new File(srcDir.getAbsolutePath() + "_tmp");

        try {
            Logger.info("Create new Path: {}", tmpDir.getAbsolutePath());
            FileUtils.forceMkdir(tmpDir);
            copyPathWithRandomTime(srcDir, tmpDir);

            Logger.info("deleteDirectory: {}", srcDir.getAbsolutePath());
            FileUtils.deleteDirectory(srcDir);

            Logger.info("{} renameTo: {}", tmpDir.getName(), srcDir.getName());
            tmpDir.renameTo(srcDir);
        } catch (IOException e) {
            Logger.error(e.getMessage());
        }
    }

    private void copyPathWithRandomTime(File srcDir, File destDir) throws IOException {
        Logger.info("Start coping from " + srcDir.getAbsolutePath() + " to " + destDir.getAbsolutePath());

        final List<File> files = new ArrayList<>(FileUtils.listFiles(srcDir, FileFileFilter.FILE, DirectoryFileFilter.DIRECTORY));
        while (files.size() != 0) {
            final File srcFile = files.get((int) (Math.random() * files.size()));

            Logger.info("File:" + srcFile.getName() + ". Start copy");
            FileUtils.copyFileToDirectory(srcFile, destDir, false);

            files.remove(srcFile);
            FileUtils.deleteQuietly(srcFile);
            Logger.info("File:" + srcFile.getName() + " was copy and Delete. Left size:" + files.size());
        }
    }
}
