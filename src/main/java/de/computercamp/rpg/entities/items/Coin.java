package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

public class Coin extends Item {
    private final int worth;

    public Coin(@NotNull Game game, @NotNull Vector2D position, int worth) {
        super(game, position, false);
        this.worth = worth;
        symbol = '\u00A9';
    }

    public Coin(@NotNull Game game, @NotNull Vector2D position) {
        this(game, position, 1);
    }

    @Override
    public void onCollect(@NotNull Player player) {
        player.addCoins(worth);
        getMap().removeObject(this);
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return Messages.coin;
    }
}
