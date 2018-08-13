package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.npcs.*;

public class NPCSpawner {

    public static void spawnRandomNPCs(Player player, Map map, Vector2D minPos, Vector2D maxPos) {
        WelcomeNPC welcomeNPC = new WelcomeNPC(getRandomLocation(map, minPos, maxPos));
        map.addObject(welcomeNPC);
        welcomeNPC.startMoving(player);

        MagicianNPC magicianNPC = new MagicianNPC(getRandomLocation(map, minPos, maxPos), 20);
        map.addObject(magicianNPC);
        magicianNPC.startMoving(player);

        BadMagicianNPC badMagicianNPC = new BadMagicianNPC(getRandomLocation(map, minPos, maxPos), 20, -6, 8);
        map.addObject(badMagicianNPC);
        badMagicianNPC.startMoving(player);

        WeaponSmithNPC weaponSmithNPC = new WeaponSmithNPC(getRandomLocation(map, minPos, maxPos));
        map.addObject(weaponSmithNPC);
        weaponSmithNPC.startMoving(player);

        CookNPC cookNPC = new CookNPC(getRandomLocation(map, minPos, maxPos), 20, 3);
        map.addObject(cookNPC);
        cookNPC.startMoving(player);
    }

    private static Vector2D getRandomLocation(Map map, Vector2D minPos, Vector2D maxPos) {
        Vector2D randloc;
        do {
            int posX = (int) Math.round((Math.random() * (maxPos.x - minPos.x)) + minPos.x);
            int posY = (int) Math.round((Math.random() * (maxPos.y - minPos.y)) + minPos.y);
            randloc = new Vector2D(posX, posY);
        }
        while (map.getObjectByPosition(randloc, false) != null);
        return randloc;
    }
}
