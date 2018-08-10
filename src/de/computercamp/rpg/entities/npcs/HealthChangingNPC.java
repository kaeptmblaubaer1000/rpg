package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;

public class HealthChangingNPC extends NPC {
	int minHealthChange;
	int maxHealthChange;
	public HealthChangingNPC(Player player, Vector2D position, MessageID message, int minHealthChange, int maxHealthChange, long delay) {
		super(player, position, message, delay);
		this.minHealthChange = minHealthChange;
		this.maxHealthChange = maxHealthChange;
	}
	@Override
	public void doNPCAction(Player player) {
		int healthChange = (int) Math.round((Math.random()*(maxHealthChange-minHealthChange))+minHealthChange);
		if (healthChange < 0) {
			player.decreaseHealth((-1)*healthChange);
		} else {
			player.increaseHealth(healthChange);
		}
	}

}
