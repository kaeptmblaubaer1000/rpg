package de.computercamp.rpg.entities;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import org.jetbrains.annotations.NotNull;

public class WallTile extends BaseObject {
    @NotNull
    private final Type type;

    public WallTile(@NotNull Game game, @NotNull Vector2D position, @NotNull Type type) {
        super(game, position);
        this.type = type;
    }

    public char oldRender() {
        switch (type) {
        case VERTICAL:
            return Map.VERTICAL_LINE;
        case HORIZONTAL:
            return Map.HORIZONTAL_LINE;
        case LEFT_LOWER_EDGE:
            return Map.LEFT_LOWER_EDGE;
        case RIGHT_LOWER_EDGE:
            return Map.RIGHT_LOWER_EDGE;
        case LEFT_UPPER_EDGE:
            return Map.LEFT_UPPER_EDGE;
        case RIGHT_UPPER_EDGE:
            return Map.RIGHT_UPPER_EDGE;
        case VERTICAL_RIGHT_T_CONNECTOR:
            return Map.VERTICAL_RIGHT_T_CONNECTOR;
        case VERTICAL_LEFT_T_CONNECTOR:
            return Map.VERTICAL_LEFT_T_CONNECTOR;
        case HORIZONTAL_DOWN_T_CONNECTOR:
            return Map.HORIZONTAL_DOWN_T_CONNECTOR;
        case HORIZONTAL_UP_T_CONNECTOR:
            return Map.HORIZONTAL_UP_T_CONNECTOR;
        default:
            return 'E';
        }
    }

    @Override
    public boolean onPlayerMove(@NotNull Player player) {
        return !player.getPosition().equals(getPosition());
    }

    public enum Type {
        VERTICAL,
        HORIZONTAL,

        LEFT_UPPER_EDGE,
        RIGHT_UPPER_EDGE,
        LEFT_LOWER_EDGE,
        RIGHT_LOWER_EDGE,

        VERTICAL_RIGHT_T_CONNECTOR,
        VERTICAL_LEFT_T_CONNECTOR,
        HORIZONTAL_DOWN_T_CONNECTOR,
        HORIZONTAL_UP_T_CONNECTOR,
    }
}
