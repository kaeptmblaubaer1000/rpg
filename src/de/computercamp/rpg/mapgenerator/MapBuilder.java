package de.computercamp.rpg.mapgenerator;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Door;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.WallTile;

import java.util.*;

public class MapBuilder {

    private de.computercamp.rpg.Map map;
    private Player player;
    private List<BaseObject> objects = new ArrayList<>();

    public MapBuilder() {
        map = new de.computercamp.rpg.Map();
        player = new Player(new Vector2D(1, 1));

        objects.add(new WallTile(new Vector2D(0, 0), WallTile.Type.LEFT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(12, 0), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(12, 2), WallTile.Type.LEFT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(21, 2), WallTile.Type.RIGHT_UPPER_EDGE));
        objects.add(new WallTile(new Vector2D(12, 1), WallTile.Type.VERTICAL));

        objects.add(new WallTile(new Vector2D(21, 6), WallTile.Type.RIGHT_LOWER_EDGE));
        objects.add(new WallTile(new Vector2D(0, 6), WallTile.Type.LEFT_LOWER_EDGE));
        for (int i = 1; i < 12; i++) {
            objects.add(new WallTile(new Vector2D(i, 0), WallTile.Type.HORIZONTAL));
        }
        for (int i = 13; i < 21; i++) {
            objects.add(new WallTile(new Vector2D(i, 2), WallTile.Type.HORIZONTAL));
        }
        for (int i = 3; i < 6; i++) {
            objects.add(new WallTile(new Vector2D(21, i), WallTile.Type.VERTICAL));
        }
        for (int i = 3; i < 6; i++) {
            objects.add(new WallTile(new Vector2D(21, i), WallTile.Type.VERTICAL));
        }
        for (int i = 1; i < 6; i++) {
            objects.add(new WallTile(new Vector2D(i, 6), WallTile.Type.HORIZONTAL));
        }
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
