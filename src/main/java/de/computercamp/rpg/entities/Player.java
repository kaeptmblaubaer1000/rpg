package de.computercamp.rpg.entities;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;

import java.util.*;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends LivingBaseObject {

    public static final int INVENTORY_SIZE = 10;

    private List<Item> inventory = new ArrayList<>(INVENTORY_SIZE);
    private int coins = 0;

    private String message;
    private Timer messageTimer = new Timer(true);
    private TimerTask messageTimerTask;

    public Player(Vector2D position) {
        super(position);
    }

    @Override
    public char render() {
        if (health <= 0) {
            return 'X';
        }
        return '\uA66A';
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
        if (health > 0) {
            position.y--;
            if (position.y < 0 || !map.onPlayerMove(this)) {
                position.y++;
            }
        }
    }

    /**
     * Moves the player downwards.
     */
    public void down() {
        if (health > 0) {
            position.y++;
            if (!map.onPlayerMove(this)) {
                position.y--;
            }
        }
    }

    /**
     * Moves this player to the right.
     */
    public void right() {
        if (health > 0) {
            position.x++;
            if (!map.onPlayerMove(this)) {
                position.x--;
            }
        }
    }

    /**
     * Moves this player to the left.
     */
    public void left() {
        if (health > 0) {
            position.x--;
            if (position.x < 0 || !map.onPlayerMove(this)) {
                position.x++;
            }
        }
    }

    public boolean hasAnyItemOfType(Class<? extends Item> type) {
        return inventory.stream().anyMatch(item -> item.getClass().equals(type));
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

    /**
     * @param type  the type of the item which will be removed
     * @param count how many items will be removed
     */
    public void removeItemsOfType(Class<? extends Item> type, int count) {
        int counter = 0;
        for (Item item : inventory) {
            if (item.getClass().equals(type)) {
                inventory.remove(item);
                if (++counter >= count) {
                    return;
                }
            }
        }
    }

    public void dropItem(int inventoryIndex) {
        if (inventory.size() > inventoryIndex) {
            dropItem(inventory.get(inventoryIndex));
        }
    }

    public void dropItem(Item item) {
        if (map != null) {
            if (map.getObjectByPosition(position.withY(position.y + 1)) == null) {
                item.setPosition(position.withY(position.y + 1));
            }
            else if (map.getObjectByPosition(position.withY(position.y - 1)) == null) {
                item.setPosition(position.withY(position.y - 1));
            }
            else if (map.getObjectByPosition(position.withX(position.x + 1)) == null) {
                item.setPosition(position.withX(position.x + 1));
            }
            else if (map.getObjectByPosition(position.withX(position.x - 1)) == null) {
                item.setPosition(position.withX(position.x - 1));
            }
            else {
                return;
            }
            removeItem(item);
            map.addObject(item);
            System.gc();
        }
    }

    public List<Item> getInventory() {
        return Collections.unmodifiableList(inventory);
    }

    public String renderInventory() {
        StringBuilder string = new StringBuilder(Messages.inventory + ": \n");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            string.append(i).append(". ").append(item.getSymbol()).append(" (").append(item.getDisplayName()).append(")\n");
        }
        return string.toString();
    }

    public void useItem(Item item) {
        if (item.use(this)) {
            inventory.remove(item);
        }
    }

    public void useItem(int inventoryIndex) {
        if (inventory.size() > inventoryIndex) {
            useItem(inventory.get(inventoryIndex));
        }
    }

    public void addCoins(int coins) {
        this.coins = this.coins + coins;
    }

    public void removeCoins(int coins) {
        this.coins = this.coins - coins;
    }

    public String renderCoins() {
        return Messages.coins + ": " + coins;
    }

    public void sendMessage(String message) {
        this.message = message;
        if (messageTimerTask != null) {
            messageTimerTask.cancel();
        }
        messageTimerTask = new TimerTask() {
            @Override
            public void run() {
                Player.this.message = null;
            }
        };
        messageTimer.schedule(messageTimerTask, 10 * 1000);
    }

    public String getMessage() {
        return message;
    }

    public String renderMessage() {
        if (isDead()) {
            return "\n\u00bb " + Messages.youDied;
        }

        if (message == null) {
            return "\n\u00bb ";
        }
        else {
            return "\n\u00bb " + message;
        }
    }
}
