package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class SuicideSword extends Item {

    public SuicideSword(Vector2D position) {
        super(position);
        symbol = '\u2020';
    }

    @Override
    public boolean use(Player player) {
        player.decreaseHealth(10);
        return false;
    }

    @Override
    public String getDisplayName() {
        return Messages.suicideSword;
    }
}
