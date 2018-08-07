package de.computercamp.rpg;

import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.WallTile;
import de.computercamp.rpg.entities.WallTile.Type;

public class MapBuilder {
	public static Map GetMap1(Player player) {
		Map map = new Map();
		map.addObject(new WallTile(new Vector2D(1, 1), Type.LEFT_LOWER_EDGE));
		map.addObject(player);
		return map;
	}
}
