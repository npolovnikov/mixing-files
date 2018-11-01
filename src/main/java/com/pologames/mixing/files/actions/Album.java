package com.pologames.mixing.files.actions;

import com.pologames.mixing.files.logger.Logger;
import com.pologames.mixing.files.reader.Reader;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Album implements Startle {

    @Override
    public void start() {
        File albumDir = new File(Reader.readString("Путь до альбома"));
        while (!albumDir.isDirectory() || albumDir.list() == null) {
            Logger.error("Необходим указать путь до альбома");
            albumDir = new File(Reader.readString("Путь до альбома"));
        }

        final String artistName = Reader.readString("Имя исполнителя");
        final Integer symbols = Reader.readInt("Количесиво символов отвеленных на номер трека");

        File destDir = new File(Reader.readString("Путь до каталога"));
        while (!destDir.isDirectory() || destDir.list() == null) {
            Logger.error("Необходим указать путь до каталога");
            destDir = new File(Reader.readString("Путь до каталога"));
        }

        try {
            copyFromAlbum(artistName, symbols, albumDir, destDir);
            Logger.info("deleteDirectory: {}", albumDir.getAbsolutePath());
            FileUtils.deleteDirectory(albumDir);
        } catch (Exception e) {
            Logger.error(e.getMessage());
        }
    }

    private void copyFromAlbum(String artistName, Integer symbols, File albumDir, File destDir) throws IOException {
        Logger.info("Start coping from " + albumDir.getAbsolutePath() + " to " + destDir.getAbsolutePath());

        final List<File> files = new ArrayList<>(FileUtils.listFiles(albumDir, FileFileFilter.FILE, DirectoryFileFilter.DIRECTORY));
        files.sort(Comparator.comparing(File::getName));
        for (File srcFile: files) {
            if (srcFile.getName().endsWith(".mp3")) {
                final String  destFileName = artistName + " - " + srcFile.getName().substring(symbols);
                final File destFile = new File(destDir.getAbsolutePath(), destFileName);

                Logger.info("File:" + srcFile.getName() + ". Start copy");
                FileUtils.copyFile(srcFile, destFile);

                FileUtils.deleteQuietly(srcFile);
                Logger.info("File:" + srcFile.getName() + " was copy and Delete. Left size:" + files.size());

            }
        }
    }
}
