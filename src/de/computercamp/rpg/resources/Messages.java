package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static ResourceBundle bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", Locale.getDefault());

    public static String npcWelcome = bundle.getString("npcWelcome");
    public static String npcMagician = bundle.getString("npcMagician");
    public static String npcBadMagician = bundle.getString("npcBadMagician");
    public static String npcWeaponsmith = bundle.getString("npcWeaponsmith");
    public static String closeProgram = bundle.getString("closeProgram");
    public static String healing_potion = bundle.getString("healing_potion");
    public static String inventory = bundle.getString("inventory");
    public static String key = bundle.getString("key");
    public static String sword = bundle.getString("sword");
    public static String npcWaiting = bundle.getString("npcWaiting");

    private Messages() {
    }
}
