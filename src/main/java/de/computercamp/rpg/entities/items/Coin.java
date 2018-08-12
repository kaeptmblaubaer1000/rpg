package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.resources.Messages;

public class Coin extends Item {

    public Coin(Vector2D position) {
        super(position, false);
        symbol = '\u00A9';
    }

    @Override
    public void onCollect(Player player) {
        player.addCoins(1);
        map.removeObject(this);
    }

    @Override
    public String getDisplayName() {
        return Messages.coin;
    }
}
