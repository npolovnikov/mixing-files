package com.pologames.mixing.files.actions;

import com.pologames.mixing.files.logger.Logger;
import com.pologames.mixing.files.reader.Reader;

import java.util.ArrayList;
import java.util.List;

public class Menu implements Startle {
    private static final List<Startle> actions = new ArrayList<Startle>() {{
        add(new Mixer());
        add(new Album());
    }};

    @Override
    public void start() {
        Logger.info("Выбирите действие:");
        Logger.info("1) Перемешать файлы внутри одного каталого. (сортировка по дате)");
        Logger.info("2) Скопировать альбом в общий каталог");
//        Logger.info("3) Присвоить корректные имена");

        int i = Reader.readInt("Пункт меню");

        if (i <= actions.size()) {
            actions.get(i - 1).start();
        } else {
            start();
        }
    }
}
