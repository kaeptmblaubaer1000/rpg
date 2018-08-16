package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class Cucumber extends Item {

    public Cucumber(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
        symbol = '(';
    }

    @Override
    public boolean use(@NotNull Player player) {
        player.increaseHealth(2);
        return true;
    }

    @Override
    public String getDisplayName() {
        return Messages.cucumber;
    }
}
