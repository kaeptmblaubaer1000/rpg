package de.computercamp.rpg;

import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.WallTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MapBuilder {

    private Map map;
    private Player player;
    private List<WallTile> wall = new ArrayList<>();

    public MapBuilder() {
        map = new Map();
        player = new Player(new Vector2D(1, 1));

        wall.add(new WallTile(new Vector2D(10, 10), WallTile.Type.LEFT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(10, 11), WallTile.Type.LEFT_LOWER_EDGE));
        wall.add(new WallTile(new Vector2D(11, 10), WallTile.Type.RIGHT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(11, 11), WallTile.Type.RIGHT_LOWER_EDGE));

        wall.add(new WallTile(new Vector2D(0, 0), WallTile.Type.LEFT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(0, 14), WallTile.Type.LEFT_LOWER_EDGE));
        wall.add(new WallTile(new Vector2D(60, 0), WallTile.Type.RIGHT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(60, 14), WallTile.Type.RIGHT_LOWER_EDGE));
        for (int i = 1; i < 14; i++) {
            wall.add(new WallTile(new Vector2D(0, i), WallTile.Type.HORIZONTAL));
            wall.add(new WallTile(new Vector2D(60, i), WallTile.Type.HORIZONTAL));
        }
        for (int i = 1; i < 60; i++) {
            wall.add(new WallTile(new Vector2D(i, 0), WallTile.Type.VERTICAL));
            wall.add(new WallTile(new Vector2D(i, 14), WallTile.Type.VERTICAL));
        }

        map.addObject(player);
        for (WallTile wallTile : wall) {
            map.addObject(wallTile);
        }

        NPCSpawner.spawnRandomNPCs(map, new Vector2D(2,2), new Vector2D(58,12));
    }

    public Map getMap() {
        return map;
    }

    public Player getPlayer() {
        return player;
    }

    public List<WallTile> getWall() {
        return Collections.unmodifiableList(wall);
    }
}
