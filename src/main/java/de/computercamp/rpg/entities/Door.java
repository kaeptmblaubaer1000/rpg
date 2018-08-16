package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;
import org.jetbrains.annotations.NotNull;

public class Door extends BaseObject {
    public Door(@NotNull Vector2D position) {
        super(position);
    }

    public char render() {
        return '\u258D';
    }

    @Override
    public boolean onPlayerMove(@NotNull Player player) {
        return true;
    }
}
