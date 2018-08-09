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

    @Override
    public char render() {
        return symbol;
    }
    
    @Override
    public boolean onPlayerMove(Player player) {
    	if ((player.getPosition().x == position.x) && (player.getPosition().y == position.y)) {
    		player.collectItem(this);
    		player.getMap().removeObject(this);
    	}
    	return true;
    }
}
