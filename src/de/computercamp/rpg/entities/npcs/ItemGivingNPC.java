package de.computercamp.rpg.entities.npcs;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.items.Item;

public class ItemGivingNPC extends NPC {
    private Item toGive;

    public ItemGivingNPC(Player player, Vector2D position, MessageID message, Item item, long delay) {
        super(player, position, message, delay);
        this.toGive = item;

    }

    @Override
    public void doNPCAction(Player player) {
        if (player.getInventory().size() >= Player.INVENTORY_SIZE) {
            toGive.setPosition(position);
            map.addObject(toGive);
        }
        else
            player.collectItem(toGive);
    }

}
