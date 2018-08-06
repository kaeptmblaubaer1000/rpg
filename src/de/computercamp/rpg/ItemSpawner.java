package de.computercamp.rpg;

public class ItemSpawner {
	public Item getRandomItem(Vector2D minPos, Vector2D maxPos) {
		Item.Type type;
		double rand = Math.random();
		if (rand < (1/3)) {
			type = Item.Type.HEALING_POTION;
		} else if (rand < (2/3)) {
			type = Item.Type.KEY;
		} else {
			type = Item.Type.SWORD;
		}
		int posX = (int) Math.round((Math.random()*maxPos.x)-minPos.x);
		int posY = (int) Math.round((Math.random()*maxPos.y)-minPos.y);
		return new Item(new Vector2D(posX, posY), type);
	}
}
