package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", Locale.getDefault());

    public static final String closeProgram = bundle.getString("closeProgram");

    private Messages() {
    }
}
