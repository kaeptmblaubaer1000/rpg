package de.computercamp.rpg;

import de.computercamp.rpg.entities.items.HealingPotion;
import de.computercamp.rpg.entities.items.Item;
import de.computercamp.rpg.entities.items.Key;
import de.computercamp.rpg.entities.items.Sword;

import java.util.Random;

public class ItemSpawner {

    //public Item getRandomItem(Map map, Vector2D minPos, Vector2D maxPos) {
    //    Item.Type type;
    //    double rand = Math.random();
    //    if (rand < (1 / 3)) {
    //        type = Item.Type.HEALING_POTION;
    //    } else if (rand < (2 / 3)) {
    //        type = Item.Type.KEY;
    //    } else {
    //        type = Item.Type.SWORD;
    //    }
    //    int posX = (int) Math.round((Math.random() * maxPos.x) - minPos.x);
    //    int posY = (int) Math.round((Math.random() * maxPos.y) - minPos.y);
    //    Item item = new Item(new Vector2D(posX, posY), type);
    //    map.addObject(item);
    //    return item;
    //}

    public static Item getRandomItem(Map map, Vector2D minPos, Vector2D maxPos) {
        Random random = new Random();
        int randomInt = random.nextInt(3);
        Vector2D randomPos = getRandomLocation(map, minPos, maxPos);
        Item item;

        if (randomInt == 0) {
            item = new HealingPotion(randomPos);
        } else if (randomInt == 1) {
            item = new Sword(randomPos);
        } else {
            item = new Key(randomPos);
        }
        map.addObject(item);
        return item;
    }
    private static Vector2D getRandomLocation(Map map, Vector2D minPos, Vector2D maxPos) {
		Vector2D randloc;
		do {
			int posX = (int) Math.round((Math.random()*(maxPos.x-minPos.x))+minPos.x);
			int posY = (int) Math.round((Math.random()*(maxPos.y-minPos.y))+minPos.y);
			randloc = new Vector2D(posX, posY);
		} while (map.getObjectByPosition(randloc) != null);
		return randloc;
	}
}
