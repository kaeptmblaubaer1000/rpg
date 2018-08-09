package de.computercamp.rpg;
import de.computercamp.rpg.entities.items.HealingPotion;
import de.computercamp.rpg.entities.items.Sword;
import de.computercamp.rpg.entities.npcs.HealthChangingNPC;
import de.computercamp.rpg.entities.npcs.ItemGivingNPC;
import de.computercamp.rpg.entities.npcs.NPC;

public class NPCSpawner {
	public static void spawnRandomNPCs(Map map, Vector2D minPos, Vector2D maxPos) {
		NPC welcomeNPC = new NPC(getRandomLocation(map, minPos, maxPos), 0, 0);
		welcomeNPC.startMoving();
		NPC magician = new ItemGivingNPC(getRandomLocation(map, minPos, maxPos), 1, new HealingPotion(new Vector2D(0,0)), (long)-1);
		magician.startMoving();
		NPC badmagician = new HealthChangingNPC(getRandomLocation(map, minPos, maxPos), 2, -6, 8, 20000);
		badmagician.startMoving();
		NPC weaponsmith = new ItemGivingNPC(getRandomLocation(map, minPos, maxPos), 3, new Sword(new Vector2D(0,0)), (long) -1);
		weaponsmith.startMoving();
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
