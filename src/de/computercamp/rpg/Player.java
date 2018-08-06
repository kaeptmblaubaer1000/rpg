package de.computercamp.rpg;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends BaseObject {

    public Player(Vector2D position) {
        super(position);
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
