package de.computercamp.rpg.resources;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static ResourceBundle bundle;
    public static Locale locale;

    public static String npcWelcome;
    public static String npcMagician;
    public static String npcBadMagician;
    public static String npcWeaponsmith;

    public static String closeProgram;
    public static String healing_potion;
    public static String itemRequired;
    public static String inventory;
    public static String key;
    public static String sword;
    public static String npcWaiting;
    public static String npcWaitingForever;
    public static String cucumber;
    public static String suicideSword;
    public static String youDied;

    private Messages() {
    }

    private static void reloadStrings() {
        bundle = ResourceBundle.getBundle("de.computercamp.rpg.resources.MessageBundle", locale);
        npcWelcome = bundle.getString("npcWelcome");
        npcMagician = bundle.getString("npcMagician");
        npcBadMagician = bundle.getString("npcBadMagician");
        npcWeaponsmith = bundle.getString("npcWeaponsmith");
        closeProgram = bundle.getString("closeProgram");
        healing_potion = bundle.getString("healing_potion");
        itemRequired = bundle.getString("itemRequired");
        inventory = bundle.getString("inventory");
        key = bundle.getString("key");
        sword = bundle.getString("sword");
        npcWaiting = bundle.getString("npcWaiting");
        npcWaitingForever = bundle.getString("npcWaitingForever");
        cucumber = bundle.getString("cucumber");
        suicideSword = bundle.getString("suicideSword");
        youDied = bundle.getString("youDied");
    }

    static {
        locale = Locale.getDefault();
        reloadStrings();
    }

    public static void changeLanguage(Locale locale) {
        Messages.locale = locale;
        reloadStrings();
    }
}
