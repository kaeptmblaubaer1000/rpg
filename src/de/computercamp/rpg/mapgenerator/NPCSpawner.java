package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Key;
import de.computercamp.rpg.entities.npcs.newnpc.*;

public class NPCSpawner {

    public static void spawnRandomNPCs(Player player, Map map, Vector2D minPos, Vector2D maxPos) {
        //NPC welcomeNPC = new NPC(player, getRandomLocation(map, minPos, maxPos), NPC.MessageID.npcWelcome, 0);
        //welcomeNPC.startMoving(player);
        //NPC magician = new ItemGivingNPC(player, getRandomLocation(map, minPos, maxPos), NPC.MessageID.npcMagician, new HealingPotion(new Vector2D(0, 0)), (long) -1);
        //magician.startMoving(player);
        //NPC badmagician = new HealthChangingNPC(player, getRandomLocation(map, minPos, maxPos), NPC.MessageID.npcBadMagician, -6, 8, 20000);
        //badmagician.startMoving(player);
        //NPC weaponsmith = new ItemGivingNPC(player, getRandomLocation(map, minPos, maxPos), NPC.MessageID.npcWeaponsmith, new Sword(new Vector2D(0, 0)), (long) -1);
        //weaponsmith.startMoving(player);
        //NPC cook = new ItemGivingNPC(player, getRandomLocation(map, minPos, maxPos), NPC.MessageID.npcCook, new Cucumber(new Vector2D(0, 0)), (long) 45);
        //cook.setRequiredItem(new Key(new Vector2D(0, 0)));
        //cook.startMoving(player);
        //map.addObject(welcomeNPC);
        //map.addObject(magician);
        //map.addObject(badmagician);
        //map.addObject(weaponsmith);
        //map.addObject(cook);

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

        CookNPC cookNPC = new CookNPC(getRandomLocation(map, minPos, maxPos), 20, new Key(null));
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
        while (map.getObjectByPosition(randloc) != null);
        return randloc;
    }
}
