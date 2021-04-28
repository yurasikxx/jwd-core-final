package com.epam.jwd.core_final.context;

import com.epam.jwd.core_final.context.impl.NassaContext;
import com.epam.jwd.core_final.context.impl.NassaMenu;
import com.epam.jwd.core_final.util.PropertyReader;

public interface Application {

    static void start() {
        PropertyReader.getInstance().readProperties();
        NassaContext.getInstance().init();
        NassaMenu.getInstance().handleUserInput();
    }

}
