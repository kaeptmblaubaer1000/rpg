package de.computercamp.rpg.entities.items;

import de.computercamp.rpg.Game;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;
import org.jetbrains.annotations.NotNull;

public abstract class Item extends BaseObject {

    protected char symbol;
    private boolean collectible;

    public Item(@NotNull Game game, @NotNull Vector2D position) {
        this(game, position, true);
        collectible = true;
    }

    public Item(@NotNull Game game, @NotNull Vector2D position, boolean collectible) {
        super(game, position);
        this.collectible = collectible;
    }

    /**
     * @param player the player who uses the item
     * @return Returns if the item should remove from the inventory. True: the item will be removed
     */
    public boolean use(@NotNull Player player) {
        return true;
    }

    public char getSymbol() {
        return symbol;
    }

    public abstract String getDisplayName();

    public void onCollect(@NotNull Player player) {
    }

    public void onDrop(@NotNull Player player) {
    }

    @Override
    public char render() {
        return symbol;
    }

    @Override
    public boolean onPlayerMove(@NotNull Player player) {
        if (player.getPosition().equals(getPosition())) {
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
