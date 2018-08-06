package de.computercamp.rpg;

public class Player {

    private Vector2D position;

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    /**
     * Moves this player upwards.
     */
    public void up() {
        position.y++;
    }

    /**
     * Moves the player downwards.
     */
    public void down() {
        position.y--;
    }

    /**
     * Moves this player to the right.
     */
    public void right() {
        position.x++;
    }

    /**
     * Moves this player to the left.
     */
    public void left() {
        position.x--;
    }
}
