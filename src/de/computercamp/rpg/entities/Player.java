package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends BaseObject {

    private static final int INVENTORY_SIZE = 10;

    private List<Item> inventory = new ArrayList<>(INVENTORY_SIZE);

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
        for (BaseObject object : map.getMapContents()) {
            object.onPlayerMove();
        }
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

    private void

    public void collectItem(Item item) {
        if (map == null) {
            throw new NullPointerException("Player removed from map");
        }
        if (inventory.size() < INVENTORY_SIZE) {
            map.removeObject(item);
            inventory.add(item);
        }
    }

    /**
     * @param item the item to removeObject
     * @return Returns true if the inventory contained the specified item.
     */
    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    public List<Item> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public void showInventory() {
        System.out.println("Inventory: ");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.println(i + ". " + item.getSymbol() + " (" + item.getDisplayName() + ")");
        }
    }
}
