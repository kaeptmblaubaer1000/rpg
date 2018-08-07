package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Resources {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", Locale.getDefault());

    public static final String closeProgram = bundle.getString("closeProgram");

    private Resources() {
    }
}
