package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class SuicideSword extends Item {

    public SuicideSword(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
        symbol = '\u2020';
    }

    @Override
    public boolean use(@NotNull Player player) {
        player.decreaseHealth(10);
        return false;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return Messages.suicideSword;
    }
}
