package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    public static final ResourceBundle bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", Locale.getDefault());

    public static final String closeProgram = bundle.getString("closeProgram");
    public static final String healing_potion = bundle.getString("healing_potion");
    public static final String inventory = bundle.getString("inventory");
    public static final String key = bundle.getString("key");
    public static final String sword = bundle.getString("sword");

    private Messages() {
    }
}
