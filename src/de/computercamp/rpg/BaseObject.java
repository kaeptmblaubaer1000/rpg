package de.computercamp.rpg;

public abstract class BaseObject {
    protected Vector2D position;
    protected Map map;

    public BaseObject(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    abstract public char render();
}
