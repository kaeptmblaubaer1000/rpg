package de.computercamp.rpg.entities;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.resources.Messages;
import org.jetbrains.annotations.NotNull;

import java.util.*;

/**
 * Simple player class with position and up, down, right and left method
 */
public class Player extends LivingBaseObject {

    public static final int INVENTORY_SIZE = 10;

    private final List<Item> inventory = new ArrayList<>(INVENTORY_SIZE);
    private int coins = 0;

    private String message;
    private final Timer messageTimer = new Timer(true);
    private TimerTask messageTimerTask;

    public Player(@NotNull Game game, @NotNull Vector2D position) {
        super(game, position);
    }

    @Override
    public char oldRender() {
        if (health <= 0) {
            return 'X';
        }
        return '\uA66A';
    }

    /**
     * <b><i>Only</i></b> use this method to teleport the player
     *
     * @param newPosition the new position to teleport to
     */
    @Override
    public void setPosition(@NotNull Vector2D newPosition) {
        Vector2D oldPosition = getPosition();
        super.setPosition(newPosition);
        if (!getMap().onPlayerMove(this)) {
            setPosition(oldPosition);
        }
    }

    /**
     * Moves this player upwards.
     */
    public void up() {
        if (health > 0) {
            getPosition().y--;
            if (getPosition().y < 0 || !getMap().onPlayerMove(this)) {
                getPosition().y++;
            }
        }
    }

    /**
     * Moves the player downwards.
     */
    public void down() {
        if (health > 0) {
            getPosition().y++;
            if (!getMap().onPlayerMove(this)) {
                getPosition().y--;
            }
        }
    }

    /**
     * Moves this player to the right.
     */
    public void right() {
        if (health > 0) {
            getPosition().x++;
            if (!getMap().onPlayerMove(this)) {
                getPosition().x--;
            }
        }
    }

    /**
     * Moves this player to the left.
     */
    public void left() {
        if (health > 0) {
            getPosition().x--;
            if (getPosition().x < 0 || !getMap().onPlayerMove(this)) {
                getPosition().x++;
            }
        }
    }

    public boolean hasAnyItemOfType(Class<? extends Item> type) {
        return inventory.stream().anyMatch(type::isInstance);
    }

    public void collectItem(Item item) {
        if (inventory.size() < INVENTORY_SIZE) {
            getMap().removeObject(item);
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
        if (getMap().getObjectByPosition(getPosition().withY(getPosition().y - 1), false) == null) {
            item.setPosition(getPosition().withY(getPosition().y - 1));
        } else if (getMap().getObjectByPosition(getPosition().withX(getPosition().x + 1), false) == null) {
            item.setPosition(getPosition().withX(getPosition().x + 1));
        } else if (getMap().getObjectByPosition(getPosition().withY(getPosition().y + 1), false) == null) {
            item.setPosition(getPosition().withY(getPosition().y + 1));
        } else if (getMap().getObjectByPosition(getPosition().withX(getPosition().x - 1), false) == null) {
            item.setPosition(getPosition().withX(getPosition().x - 1));
        } else {
            return;
        }
        removeItem(item);
        getMap().addObject(item);
        item.onDrop(this);
        System.gc();
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
        string.deleteCharAt(string.length() - 1);
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

    public int getCoins() {
        return coins;
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
        } else {
            return "\n\u00bb " + message;
        }
    }
}
