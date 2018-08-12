package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;

public abstract class Item extends BaseObject {

    protected char symbol;
    private boolean collectible;

    public Item(Vector2D position) {
        super(position);
        collectible = true;
    }

    public Item(Vector2D position, boolean collectible) {
        super(position);
        this.collectible = collectible;
    }

    /**
     * @param player the player who uses the item
     * @return Returns if the item should remove from the inventory. True: the item will be removed
     */
    public boolean use(Player player) {
        return true;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract String getDisplayName();

    public void onCollect(Player player) {
    }

    @Override
    public char render() {
        return symbol;
    }

    @Override
    public boolean onPlayerMove(Player player) {
        if (player.getPosition().equals(position)) {
            if (collectible) {
                player.collectItem(this);
            }
            onCollect(player);
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Item item = (Item) o;

        return getSymbol() == item.getSymbol();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (int) getSymbol();
        return result;
    }
}
