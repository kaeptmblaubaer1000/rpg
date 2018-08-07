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
        player = new Player(new Vector2D(0, 0));
        wall.add(new WallTile(new Vector2D(0, 1), WallTile.Type.HORIZONTAL));
        map.addObject(player);
        for (WallTile wallTile : wall) {
            map.addObject(wallTile);
        }

        NPCSpawner.spawnRandomNPCs(map, new Vector2D(0,0), new Vector2D(5,20));
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
