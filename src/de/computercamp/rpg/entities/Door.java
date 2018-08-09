package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class Door extends BaseObject {

    public Door(Vector2D position) {
        super(position);
    }

    public char render() {
        return '\u258D';
    }

    @Override
    public boolean onPlayerMove(Player player) {
        return true;
    }
}
