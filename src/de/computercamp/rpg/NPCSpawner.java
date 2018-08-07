package de.computercamp.rpg;

import de.computercamp.rpg.entities.Item;
import de.computercamp.rpg.entities.NPC;

public class NPCSpawner {
	public void spawnRandomNPCs(Map map, Vector2D minPos, Vector2D maxPos) {
		NPC welcomeNPC = new NPC(getRandomLocation(minPos, maxPos), Main.GetLanguageText("npcWelcome"));
		NPC magician = new NPC(getRandomLocation(minPos, maxPos), Main.GetLanguageText("npcMagician"), new Item(getRandomLocation(minPos, maxPos), Item.Type.HEALING_POTION), 999999999);
		NPC badmagician = new NPC(getRandomLocation(minPos, maxPos), Main.GetLanguageText("npcBadMagician"), -10, 10, 30000);
		NPC weaponsmith = new NPC(getRandomLocation(minPos, maxPos), Main.GetLanguageText("npcWeaponsmith"), new Item(getRandomLocation(minPos, maxPos), Item.Type.SWORD), 999999999);
		map.addObject(welcomeNPC);
		map.addObject(magician);
		map.addObject(badmagician);
		map.addObject(weaponsmith);
	}
	private Vector2D getRandomLocation(Vector2D minPos, Vector2D maxPos) {
		int posX = (int) Math.round((Math.random()*maxPos.x)-minPos.x);
		int posY = (int) Math.round((Math.random()*maxPos.y)-minPos.y);
		return new Vector2D(posX, posY);
	}
}
