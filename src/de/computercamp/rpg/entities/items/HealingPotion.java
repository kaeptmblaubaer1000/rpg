package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class HealingPotion extends Item {

    public HealingPotion(Vector2D position) {
        super(position);
        symbol = '\u1e50';
    }

    @Override
    public boolean use(Player player) {
        player.heal();
        return true;
    }

    @Override
    public String getDisplayName() {
        return Messages.healing_potion;
    }
}
