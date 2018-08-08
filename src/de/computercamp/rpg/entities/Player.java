package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

import java.util.*;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends BaseObject {

    public static final int MAX_HEALTH = 20;
    private static final int INVENTORY_SIZE = 10;

    private int health = MAX_HEALTH;
    private List<Item> inventory = new ArrayList<>(INVENTORY_SIZE);

    public Player(Vector2D position) {
        super(position);
        Timer healthTimer = new Timer(true);
        healthTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (health < MAX_HEALTH) {
                    health++;
                }
            }
        }, 0, 2000);
    }

    @Override
    public char render() {
        return 'X';
    }

    /**
     * Just use this method to teleport the player
     *
     * @param newPosition the new position to teleport to
     */
    @Override
    public void setPosition(Vector2D newPosition) {
        Vector2D oldPosition = position;
        super.setPosition(newPosition);
        if (!map.onPlayerMove(this)) {
            position = oldPosition;
        }
    }

    /**
     * Moves this player upwards.
     */
    public void up() {
        position.y--;
        if (position.y < 0 || !map.onPlayerMove(this)) {
            position.y++;
        }
    }

    /**
     * Moves the player downwards.
     */
    public void down() {
        position.y++;
        if (!map.onPlayerMove(this)) {
            position.y--;
        }
    }

    /**
     * Moves this player to the right.
     */
    public void right() {
        position.x++;
        if (!map.onPlayerMove(this)) {
            position.x--;
        }
    }

    /**
     * Moves this player to the left.
     */
    public void left() {
        position.x--;
        if (position.x < 0 || !map.onPlayerMove(this)) {
            position.x++;
        }
    }

    public void increaseHealth(int health) {
        this.health = this.health + health;
    }

    public void decreaseHealth(int health) {
        this.health = this.health - health;
    }

    /**
     * Sets the health to MAX_HEALTH
     */
    public void heal() {
        health = MAX_HEALTH;
    }

    /**
     * Sets the health to 0
     */
    public void kill() {
        health = 0;
    }

    public int getHealth() {
        return health;
    }

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

    public void printInventory() {
        System.out.println(Messages.inventory + ": ");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.println(i + ". " + item.getSymbol() + " (" + item.getDisplayName() + ")");
        }
    }

    public void useItem(Item item) {
        item.use(this);
        inventory.remove(item);
    }

    public void useItem(int inventoryIndex) {
        useItem(inventory.get(inventoryIndex));
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }
}
