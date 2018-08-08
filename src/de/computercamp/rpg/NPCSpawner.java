package de.computercamp.rpg;
import de.computercamp.rpg.entities.items.HealingPotion;
import de.computercamp.rpg.entities.items.Sword;
import de.computercamp.rpg.entities.npcs.HealthChangingNPC;
import de.computercamp.rpg.entities.npcs.ItemGivingNPC;
import de.computercamp.rpg.entities.npcs.NPC;
import de.computercamp.rpg.resources.Messages;

public class NPCSpawner {
	public static void spawnRandomNPCs(Map map, Vector2D minPos, Vector2D maxPos) {
		NPC welcomeNPC = new NPC(getRandomLocation(minPos, maxPos), 0, 0);
		NPC magician = new ItemGivingNPC(getRandomLocation(minPos, maxPos), 1, new HealingPotion(new Vector2D(-999,-999)), (long)999999999);
		NPC badmagician = new HealthChangingNPC(new Vector2D(-999,-999), 2, -6, 8, 20000);
		NPC weaponsmith = new ItemGivingNPC(getRandomLocation(minPos, maxPos), 3, new Sword(new Vector2D(-999,-999)), (long)999999999);
		map.addObject(welcomeNPC);
		map.addObject(magician);
		map.addObject(badmagician);
		map.addObject(weaponsmith);
	}
	private static Vector2D getRandomLocation(Vector2D minPos, Vector2D maxPos) {
		int posX = (int) Math.round((Math.random()*maxPos.x)-minPos.x);
		int posY = (int) Math.round((Math.random()*maxPos.y)-minPos.y);
		return new Vector2D(posX, posY);
	}
}
