package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Coin extends Item {
    private final int worth;

    public Coin(Vector2D position, int worth) {
        super(position, false);
        this.worth = worth;
        symbol = '\u00A9';
    }

    public Coin(Vector2D position) {
        this(position, 1);
    }

    @Override
    public void onCollect(Player player) {
        player.addCoins(worth);
        map.removeObject(this);
    }

    @Override
    public String getDisplayName() {
        return Messages.coin;
    }
}
