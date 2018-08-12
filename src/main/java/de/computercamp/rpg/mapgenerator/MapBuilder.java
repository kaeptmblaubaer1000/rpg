package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.*;
import de.computercamp.rpg.entities.items.Coin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapBuilder {

    private Map map;
    private Player player;
    private List<BaseObject> objects = new ArrayList<>();

    public MapBuilder() {
        map = new Map();
        player = new Player(new Vector2D(1, 1));

        objects.add(new WallTile(new Vector2D(0, 0), WallTile.Type.LEFT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(12, 0), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(12, 2), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR));
        objects.add(new WallTile(new Vector2D(21, 2), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(12, 1), WallTile.Type.VERTICAL));

        objects.add(new WallTile(new Vector2D(21, 6), WallTile.Type.RIGHT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(0, 6), WallTile.Type.LEFT_LOWER_EDGE));
        for (int i = 1; i < 12; i++) {
            objects.add(new WallTile(new Vector2D(i, 0), WallTile.Type.HORIZONTAL));
        }
        for (int i = 13; i < 21; i++) {
            if (i != 15)
                objects.add(new WallTile(new Vector2D(i, 2), WallTile.Type.HORIZONTAL));
            else
                objects.add(new WallTile(new Vector2D(i, 2), WallTile.Type.HORIZONTAL_DOWN_T_CONNECTOR));
        }
        for (int i = 3; i < 6; i++) {
            objects.add(new WallTile(new Vector2D(21, i), WallTile.Type.VERTICAL));
        }
        for (int i = 3; i < 6; i++) {
            objects.add(new WallTile(new Vector2D(21, i), WallTile.Type.VERTICAL));
        }
        for (int i = 1; i < 5; i++) {
            objects.add(new WallTile(new Vector2D(i, 6), WallTile.Type.HORIZONTAL));
        }
        objects.add(new WallTile(new Vector2D(5, 6), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR));
        for (int i = 7; i < 21; i++) {
            objects.add(new WallTile(new Vector2D(i, 6), WallTile.Type.HORIZONTAL));
        }
        for (int i = 1; i < 6; i++) {
            objects.add(new WallTile(new Vector2D(0, i), WallTile.Type.VERTICAL));
        }
        objects.add(new WallTile(new Vector2D(24, 4), WallTile.Type.LEFT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(25, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(26, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(27, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(28, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(29, 4), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(29, 5), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(29, 6), WallTile.Type.RIGHT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(28, 6), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(27, 6), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(25, 6), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(24, 6), WallTile.Type.LEFT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(24, 5), WallTile.Type.VERTICAL));

        objects.add(new WallTile(new Vector2D(24, 5), WallTile.Type.VERTICAL));
        for (int i = 3; i < 12; i++) {
            if (i != 8)
                objects.add(new WallTile(new Vector2D(i, 2), WallTile.Type.HORIZONTAL));
            else
                objects.add(new WallTile(new Vector2D(i, 2), WallTile.Type.HORIZONTAL_DOWN_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(8, 3), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(15, 3), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(5, 4), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(5, 5), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(8, 4), WallTile.Type.LEFT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(15, 4), WallTile.Type.RIGHT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(9, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(10, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(12, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(13, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(14, 4), WallTile.Type.HORIZONTAL));
        objects.add(new WallTile(new Vector2D(32, 0), WallTile.Type.LEFT_UPPER_EDGE));
        for (int i = 33; i < 58; i++) {
            if (i != 43 && i != 53)
                objects.add(new WallTile(new Vector2D(i, 0), WallTile.Type.HORIZONTAL));
            else
                objects.add(new WallTile(new Vector2D(i, 0), WallTile.Type.HORIZONTAL_DOWN_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(58, 0), WallTile.Type.RIGHT_UPPER_EDGE));
        for (int i = 1; i < 14; i++) {
            if (i != 7)
                objects.add(new WallTile(new Vector2D(58, i), WallTile.Type.VERTICAL));
            else
                objects.add(new WallTile(new Vector2D(58, i), WallTile.Type.VERTICAL_LEFT_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(58, 14), WallTile.Type.RIGHT_LOWER_EDGE));
        for (int i = 33; i < 58; i++) {
            if (i != 44)
                objects.add(new WallTile(new Vector2D(i, 14), WallTile.Type.HORIZONTAL));
            else
                objects.add(new WallTile(new Vector2D(i, 14), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(32, 14), WallTile.Type.LEFT_LOWER_EDGE));
        for (int i = 9; i < 14; i++) {
            if (i != 11)
                objects.add(new WallTile(new Vector2D(32, i), WallTile.Type.VERTICAL));
            else
                objects.add(new WallTile(new Vector2D(32, i), WallTile.Type.VERTICAL_RIGHT_T_CONNECTOR));
        }
        for (int i = 1; i < 8; i++) {
            if (i != 5)
                objects.add(new WallTile(new Vector2D(32, i), WallTile.Type.VERTICAL));
            else
                objects.add(new WallTile(new Vector2D(32, i), WallTile.Type.VERTICAL_RIGHT_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(43, 1), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(43, 3), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(43, 4), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(43, 5), WallTile.Type.RIGHT_LOWER_EDGE));
        for (int i = 33; i < 43; i++) {
            objects.add(new WallTile(new Vector2D(i, 5), WallTile.Type.HORIZONTAL));
        }
        objects.add(new WallTile(new Vector2D(53, 1), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(53, 3), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(53, 4), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(53, 5), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(53, 6), WallTile.Type.VERTICAL));
        for (int i = 38; i < 50; i++) {
            objects.add(new WallTile(new Vector2D(i, 7), WallTile.Type.HORIZONTAL));
        }
        for (int i = 51; i < 58; i++) {
            if (i != 53)
                objects.add(new WallTile(new Vector2D(i, 7), WallTile.Type.HORIZONTAL));
            else
                objects.add(new WallTile(new Vector2D(i, 7), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(37, 7), WallTile.Type.LEFT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(37, 8), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(37, 9), WallTile.Type.VERTICAL));
        objects.add(new WallTile(new Vector2D(37, 10), WallTile.Type.VERTICAL));
        for (int i = 33; i < 44; i++) {
            if (i != 37)
                objects.add(new WallTile(new Vector2D(i, 11), WallTile.Type.HORIZONTAL));
            else
                objects.add(new WallTile(new Vector2D(i, 11), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR));
        }
        objects.add(new WallTile(new Vector2D(44, 11), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(44, 13), WallTile.Type.VERTICAL));
        objects.add(new Door(new Vector2D(32, 8)));
        objects.add(new Door(new Vector2D(6, 6)));
        objects.add(new SmallDoor(new Vector2D(26, 6)));

        map.addObject(player);
        for (BaseObject object : objects) {
            map.addObject(object);
        }

        Vector2D minPos = new Vector2D(2, 2);
        Vector2D maxPos = new Vector2D(58, 12);

        NPCSpawner.spawnRandomNPCs(player, map, minPos, maxPos);
        ItemSpawner.startSpawningItems(map, minPos, maxPos);

        for (int i = 0; i < 5; i++) {
            map.addObject(new Coin(ItemSpawner.getRandomLocation(map, minPos, maxPos)));
        }
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public List<BaseObject> getObjects() {
        return Collections.unmodifiableList(objects);
    }
}
