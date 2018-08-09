package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Cucumber extends Item {

    public Cucumber(Vector2D position) {
        super(position);
        symbol = '(';
    }

    @Override
    public boolean use(Player player) {
        player.increaseHealth(2);
        return true;
    }

    @Override
    public String getDisplayName() {
        return Messages.cucumber;
    }
}
