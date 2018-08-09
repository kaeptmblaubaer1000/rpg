package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Sword extends Item {

    public Sword(Vector2D position) {
        super(position);
        symbol = '\u2e38';
    }

    @Override
    public boolean use(Player player) {
        //player.kill();
        player.decreaseHealth(10);
        return false;
    }

    @Override
    public String getDisplayName() {
        return Messages.sword;
    }
}
