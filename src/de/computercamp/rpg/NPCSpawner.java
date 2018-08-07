package de.computercamp.rpg;

import de.computercamp.rpg.entities.Item;
import de.computercamp.rpg.entities.NPC;

public class NPCSpawner {
	public void spawnRandomNPCs(Map map, Vector2D minPos, Vector2D maxPos) {
		NPC welcomeNPC = new NPC(getRandomLocation(minPos, maxPos), "Hello and welcome to our game!");
		NPC magician = new NPC(getRandomLocation(minPos, maxPos), "I brewed a healing potion for you. You will need it for sure!", new Item(getRandomLocation(minPos, maxPos), Item.Type.HEALING_POTION), 999999999);
		NPC badmagician = new NPC(getRandomLocation(minPos, maxPos), "I will try to heal you, but you may take damage instead. I'm not that good at healing people...", -10, 10, 30000);
		NPC weaponsmith = new NPC(getRandomLocation(minPos, maxPos), "Here's a sword I made for you. Use it wisely because I don't have enough ressources to make you a new one.", new Item(getRandomLocation(minPos, maxPos), Item.Type.SWORD), 999999999);
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
