package de.computercamp.rpg.entities;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;

public class WallTile extends BaseObject {
    public enum Type {
        VERTICAL,
        HORIZONTAL,

        LEFT_UPPER_EDGE,
        RIGHT_UPPER_EDGE,
        LEFT_LOWER_EDGE,
        RIGHT_LOWER_EDGE,

    }

    private Type type;

    public WallTile(Vector2D position, Type type) {
        super(position);
        this.type = type;
    }

    public char render() {
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
            default:
                return 'E';
        }
    }
}
