package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.npcs.*;
import org.jetbrains.annotations.NotNull;

public class NPCSpawner {
    public static void spawnRandomNPCs(@NotNull Game game, @NotNull Player player, @NotNull Map map, @NotNull Vector2D minPos, @NotNull Vector2D maxPos) {
        WelcomeNPC welcomeNPC = new WelcomeNPC(game, getRandomLocation(map, minPos, maxPos));
        map.addObject(welcomeNPC);
        welcomeNPC.startMoving(player);

        MagicianNPC magicianNPC = new MagicianNPC(game, getRandomLocation(map, minPos, maxPos), 20);
        map.addObject(magicianNPC);
        magicianNPC.startMoving(player);

        BadMagicianNPC badMagicianNPC = new BadMagicianNPC(game, getRandomLocation(map, minPos, maxPos), 20, -6, 8);
        map.addObject(badMagicianNPC);
        badMagicianNPC.startMoving(player);

        WeaponSmithNPC weaponSmithNPC = new WeaponSmithNPC(game, getRandomLocation(map, minPos, maxPos));
        map.addObject(weaponSmithNPC);
        weaponSmithNPC.startMoving(player);

        CookNPC cookNPC = new CookNPC(game, getRandomLocation(map, minPos, maxPos), 20, 5);
        map.addObject(cookNPC);
        cookNPC.startMoving(player);
    }

    private static Vector2D getRandomLocation(@NotNull Map map, @NotNull Vector2D minPos, @NotNull Vector2D maxPos) {
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
