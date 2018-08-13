package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.items.*;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class ItemSpawner {

    //public Item spawnRandomItem(Map map, Vector2D minPos, Vector2D maxPos) {
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

    public static void startSpawningItems(Map map, Vector2D minPos, Vector2D maxPos) {
        Timer itemSpawnTimer = new Timer(true);
        itemSpawnTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                ItemSpawner.spawnRandomItem(map, minPos, maxPos);
            }
        }, 0, 60 * 1000);
    }

    public static void spawnRandomItem(Map map, Vector2D minPos, Vector2D maxPos) {
        if (map.countObjectsOfType(Item.class) < 10) {
            Random random = new Random();
            int randomInt = random.nextInt(100);
            Vector2D randomPos = getRandomLocation(map, minPos, maxPos);
            Item item;

            if (randomInt < 10) {
                item = new Cucumber(randomPos);
            }
            else if (randomInt < 15) {
                item = new SuicideSword(randomPos);
            }
            else if (randomInt < 35) {
                item = new Key(randomPos);
            }
            else if (randomInt < 70) {
                item = new HealingPotion(randomPos);
            }
            else { //Percentage between 70 and 100: 30%
                item = new Sword(randomPos);
            }
            map.addObject(item);
        }
    }

    public static Vector2D getRandomLocation(Map map, Vector2D minPos, Vector2D maxPos) {
        Vector2D randloc;
        do {
            int posX = (int) Math.round((Math.random() * (maxPos.x - minPos.x)) + minPos.x);
            int posY = (int) Math.round((Math.random() * (maxPos.y - minPos.y)) + minPos.y);
            randloc = new Vector2D(posX, posY);
        }
        while (map.getObjectByPosition(randloc, false) != null);
        return randloc;
    }
}
