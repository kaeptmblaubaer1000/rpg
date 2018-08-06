package de.computercamp.rpg;

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
}
