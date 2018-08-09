package de.computercamp.rpg;

import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.RunThroughObject;
import de.computercamp.rpg.entities.WallTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MapBuilder {

    private Map map;
    private Player player;
    private List<WallTile> wall = new ArrayList<>();
    private List<RunThroughObject> through = new ArrayList<>();

    public MapBuilder() {
        map = new Map();
        player = new Player(new Vector2D(1, 1));

        wall.add(new WallTile(new Vector2D(0, 0), WallTile.Type.LEFT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(12, 0), WallTile.Type.RIGHT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(12, 2), WallTile.Type.LEFT_LOWER_EDGE));
        wall.add(new WallTile(new Vector2D(21, 2), WallTile.Type.RIGHT_UPPER_EDGE));
        wall.add(new WallTile(new Vector2D(12, 1), WallTile.Type.VERTICAL));

        wall.add(new WallTile(new Vector2D(21, 6), WallTile.Type.RIGHT_LOWER_EDGE));
        wall.add(new WallTile(new Vector2D(0, 6), WallTile.Type.LEFT_LOWER_EDGE));
        for (int i = 1; i < 12; i++) {
            wall.add(new WallTile(new Vector2D(i, 0), WallTile.Type.HORIZONTAL));
        }
        for (int i = 13; i < 21; i++) {
            wall.add(new WallTile(new Vector2D(i, 2), WallTile.Type.HORIZONTAL));
        }
        for (int i = 3; i < 6; i++) {
            wall.add(new WallTile(new Vector2D(21, i), WallTile.Type.VERTICAL));
        }
        for (int i = 3; i < 6; i++) {
            wall.add(new WallTile(new Vector2D(21, i), WallTile.Type.VERTICAL));
        }
        for (int i = 1; i < 6; i++) {
            wall.add(new WallTile(new Vector2D(i, 6), WallTile.Type.HORIZONTAL));
        }
        for (int i = 7; i < 21; i++) {
            wall.add(new WallTile(new Vector2D(i, 6), WallTile.Type.HORIZONTAL));
        }
        for (int i = 1; i < 6; i++) {
            wall.add(new WallTile(new Vector2D(0, i), WallTile.Type.VERTICAL));
        }
        
        through.add(new RunThroughObject(new Vector2D(6,6), RunThroughObject.Type.DOOR));

        map.addObject(player);
        for (WallTile wallTile : wall) {
            map.addObject(wallTile);
        }
        for (RunThroughObject runThrough : through) {
            map.addObject(runThrough);
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

    public List<WallTile> getWall() {
        return Collections.unmodifiableList(wall);
    }
}
