package de.computercamp.rpg.entities;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;

public class RunThroughObject extends BaseObject {
    public enum Type {
    	DOOR;
    }

    private Type type;

    public RunThroughObject(Vector2D position, Type type) {
        super(position);
        this.type = type;
    }

    public char render() {
        switch (type) {
            case DOOR:
                return Map.DOOR;
            default:
                return 'E';
        }
    }

    @Override
    public boolean onPlayerMove(Player player) {
        return true;
    }
}
