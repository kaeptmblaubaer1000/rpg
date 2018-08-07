package de.computercamp.rpg;

import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.WallTile;
import de.computercamp.rpg.entities.WallTile.Type;

public class MapBuilder {
	public static Map getMap1(Player player) {
		Map map = new Map();
		map.addObject(new WallTile(new Vector2D(0,0), Type.LEFT_UPPER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(2, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(3, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(4, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(5, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(6, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(7, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(8, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(9, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(10, 0), Type.VERTICAL));
		map.addObject(new WallTile(new Vector2D(11, 0), Type.VERTICAL));;
		map.addObject(new WallTile(new Vector2D(12, 0), Type.RIGHT_UPPER_EDGE));
		map.addObject(new WallTile(new Vector2D(12, 1), Type.HORIZONTAL));
		map.addObject(new WallTile(new Vector2D(12, 3), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(player);
		return map;
	}
}
