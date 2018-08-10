package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.HealingPotion;
import de.computercamp.rpg.entities.items.Sword;
import de.computercamp.rpg.entities.npcs.HealthChangingNPC;
import de.computercamp.rpg.entities.npcs.ItemGivingNPC;
import de.computercamp.rpg.entities.npcs.NPC;

public class NPCSpawner {
	public static void spawnRandomNPCs(Player player, Map map, Vector2D minPos, Vector2D maxPos) {
		NPC welcomeNPC = new NPC(player, getRandomLocation(map, minPos, maxPos), 0, 0);
		welcomeNPC.startMoving(player);
		NPC magician = new ItemGivingNPC(player, getRandomLocation(map, minPos, maxPos), 1, new HealingPotion(new Vector2D(0,0)), (long)-1);
		magician.startMoving(player);
		NPC badmagician = new HealthChangingNPC(player, getRandomLocation(map, minPos, maxPos), 2, -6, 8, 20000);
		badmagician.startMoving(player);
		NPC weaponsmith = new ItemGivingNPC(player, getRandomLocation(map, minPos, maxPos), 3, new Sword(new Vector2D(0,0)), (long) -1);
		weaponsmith.startMoving(player);
		map.addObject(welcomeNPC);
		map.addObject(magician);
		map.addObject(badmagician);
		map.addObject(weaponsmith);
	}
	private static Vector2D getRandomLocation(Map map, Vector2D minPos, Vector2D maxPos) {
		Vector2D randloc;
		do {
			int posX = (int) Math.round((Math.random()*(maxPos.x-minPos.x))+minPos.x);
			int posY = (int) Math.round((Math.random()*(maxPos.y-minPos.y))+minPos.y);
			randloc = new Vector2D(posX, posY);
		} while (map.getObjectByPosition(randloc) != null);
		return randloc;
	}
}
