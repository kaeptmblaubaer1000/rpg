package de.computercamp.rpg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends BaseObject {

    private List<Item> inventory = new ArrayList<>();

    public Player(Vector2D position) {
        super(position);
    }

    @Override
    public char render() {
        return 'X';
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

    public void collectItem(Item item) {
        getMap().remove(item);
        inventory.add(item);
    }

    /**
     * @param item the item to remove
     * @return Returns true if the inventory contained the specified item.
     */
    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    public List<Item> getInventory() {
        return Collections.unmodifiableList(inventory);
    }
}
