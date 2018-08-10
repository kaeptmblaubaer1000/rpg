package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

public class DogDoor extends BaseObject {
    public DogDoor(Vector2D position) {
        super(position);
    }

    public char render() {
        return '\u2594';
    }

    @Override
    public boolean onPlayerMove(Player player) {
        return true;
    }
}
