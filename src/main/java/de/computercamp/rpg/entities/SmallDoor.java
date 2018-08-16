package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;
import org.jetbrains.annotations.NotNull;

public class SmallDoor extends BaseObject {
    public SmallDoor(@NotNull Vector2D position) {
        super(position);
    }

    public char render() {
        return '\u2594';
    }

    @Override
    public boolean onPlayerMove(@NotNull Player player) {
        return true;
    }
}
