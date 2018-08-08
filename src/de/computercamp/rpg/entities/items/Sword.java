package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Sword extends Item {

    public Sword(Vector2D position) {
        super(position);
        symbol = '\u1e36';
        displayName = Messages.sword;
    }

    @Override
    public void use(Player player) {
        player.kill();
    }
}
