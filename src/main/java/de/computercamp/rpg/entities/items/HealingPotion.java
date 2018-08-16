package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class HealingPotion extends Item {

    public HealingPotion(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
        symbol = '\u1e50';
    }

    @Override
    public boolean use(@NotNull Player player) {
        if (!player.isDead()) {
            player.heal();
            return true;
        }
        return false;
    }

    @Override
    public String getDisplayName() {
        return Messages.healing_potion;
    }
}
