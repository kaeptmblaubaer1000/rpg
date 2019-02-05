package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class Key extends Item {

    public Key(@NotNull Game game, @NotNull Vector2D pos) {
        super(game, pos);
        symbol = '\ua720';
    }

    @Override
    public boolean use(@NotNull Player player) {
        if (!player.isDead()) {
            player.setPosition(new Vector2D(1, 1));
            return true;
        }
        return false;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return Messages.key;
    }
}
