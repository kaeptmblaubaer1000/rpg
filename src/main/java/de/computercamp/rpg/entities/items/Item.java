package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;

public abstract class Item extends BaseObject {

    protected char symbol;

    public Item(Vector2D position) {
        super(position);
    }

    /**
     * @param player the player who uses the item
     * @return Returns if the item should remove from the inventory. True: the item will be removed
     */
    public abstract boolean use(Player player);

    public char getSymbol() {
        return symbol;
    }

    public abstract String getDisplayName();

    public boolean isSameType(Item item) {
        return item.getClass().getName().equals(getClass().getName());
    }

    @Override
    public char render() {
        return symbol;
    }

    @Override
    public boolean onPlayerMove(Player player) {
        if ((player.getPosition().x == position.x) && (player.getPosition().y == position.y)) {
            player.collectItem(this);
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
