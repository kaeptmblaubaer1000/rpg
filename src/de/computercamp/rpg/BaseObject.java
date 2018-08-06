package de.computercamp.rpg;

public class BaseObject {
    private Vector2D position;

    public BaseObject(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }
}
