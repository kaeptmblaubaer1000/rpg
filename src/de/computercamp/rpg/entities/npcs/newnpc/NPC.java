package de.computercamp.rpg.entities.npcs.newnpc;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.LivingBaseObject;
import de.computercamp.rpg.entities.Player;

public abstract class NPC extends LivingBaseObject {

    public NPC(Vector2D position) {
        super(position);
    }

    @Override
    public char render() {
        if (isDead()) {
            return 'X';
        }
        return '\uA66A';
    }

    protected abstract void doAction(Player player);

    @Override
    public boolean onPlayerMove(Player player) {
        if (player.getPosition().equals(position)) {
            doAction(player);
        }
        return false;
    }
}
