package de.computercamp.rpg.entities.npcs;

import java.text.MessageFormat;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

public class ItemGivingNPC extends NPC{
	private Item toGive;
	public ItemGivingNPC(Vector2D position, int message, Item item, long delay) {
        super(position, message, delay);
        this.toGive = item;
        
    }
	@Override
	public void doNPCAction(Player player) {
        player.collectItem(toGive);
	}
	
}
