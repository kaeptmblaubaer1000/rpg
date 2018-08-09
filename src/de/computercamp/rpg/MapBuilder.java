package de.computercamp.rpg;

import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Door;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.WallTile;

import java.util.*;

public class MapBuilder {

    private Map map;
    private Player player;
    private List<BaseObject> objects = new ArrayList<>();

    public MapBuilder() {
        map = new Map();
        player = new Player(new Vector2D(1, 1));

        objects.add(new WallTile(new Vector2D(10, 10), WallTile.Type.LEFT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(10, 11), WallTile.Type.LEFT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(11, 10), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(11, 11), WallTile.Type.RIGHT_LOWER_EDGE));

        objects.add(new WallTile(new Vector2D(0, 0), WallTile.Type.LEFT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(0, 14), WallTile.Type.LEFT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(60, 0), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(60, 14), WallTile.Type.RIGHT_LOWER_EDGE));
        for (int i = 1; i < 14; i++) {
            objects.add(new WallTile(new Vector2D(0, i), WallTile.Type.HORIZONTAL));
            objects.add(new WallTile(new Vector2D(60, i), WallTile.Type.HORIZONTAL));
        }
        for (int i = 1; i < 60; i++) {
            objects.add(new WallTile(new Vector2D(i, 0), WallTile.Type.VERTICAL));
            objects.add(new WallTile(new Vector2D(i, 14), WallTile.Type.VERTICAL));
        }

        objects.add(new Door(new Vector2D(6, 6)));

        map.addObject(player);
        for (BaseObject object : objects) {
            map.addObject(object);
        }

        NPCSpawner.spawnRandomNPCs(player, map, new Vector2D(2,2), new Vector2D(58,12));
	    Timer itemspawnTimer = new Timer(true);		
	    itemspawnTimer.scheduleAtFixedRate(new TimerTask() {
		    @Override
	       	public void run() {
				ItemSpawner.getRandomItem(map, new Vector2D(2,2), new Vector2D(58,12));
			}
	    }, 0, 10000);
	        	
        
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
