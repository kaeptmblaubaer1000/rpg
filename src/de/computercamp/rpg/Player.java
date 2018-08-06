package de.computercamp.rpg;

public class Player {

    private Vector2D position;

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void up() {
        position.y++;
    }

    public void down() {
        position.y--;
    }

    public void right() {
        position.x++;
    }

    public void left() {
        position.x--;
    }
}
