package de.computercamp.rpg.entities;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;

public class Door extends BaseObject {
    public Door(Vector2D position) {
        super(position);
    }

    public char render() {
        return Map.DOOR;
    }

    @Override
    public boolean onPlayerMove(Player player) {
        return true;
    }
}
