package de.computercamp.rpg.mapgenerator

import de.computercamp.rpg.Map
import de.computercamp.rpg.Vector2D
import de.computercamp.rpg.entities.*
import de.computercamp.rpg.entities.items.Coin

import java.util.ArrayList
import java.util.Collections

class MapBuilder {

    val map: Map
    val player: Player
    private val objects = ArrayList<BaseObject>()

    init {
        map = Map()
        player = Player(Vector2D(1, 1))

        objects.add(WallTile(Vector2D(0, 0), WallTile.Type.LEFT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(12, 0), WallTile.Type.RIGHT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(12, 2), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR))
        objects.add(WallTile(Vector2D(21, 2), WallTile.Type.RIGHT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(12, 1), WallTile.Type.VERTICAL))

        objects.add(WallTile(Vector2D(21, 6), WallTile.Type.RIGHT_LOWER_EDGE))
        objects.add(WallTile(Vector2D(0, 6), WallTile.Type.LEFT_LOWER_EDGE))
        for (i in 1..11) {
            objects.add(WallTile(Vector2D(i, 0), WallTile.Type.HORIZONTAL))
        }
        for (i in 13..20) {
            if (i != 15)
                objects.add(WallTile(Vector2D(i, 2), WallTile.Type.HORIZONTAL))
            else
                objects.add(WallTile(Vector2D(i, 2), WallTile.Type.HORIZONTAL_DOWN_T_CONNECTOR))
        }
        for (i in 3..5) {
            objects.add(WallTile(Vector2D(21, i), WallTile.Type.VERTICAL))
        }
        for (i in 3..5) {
            objects.add(WallTile(Vector2D(21, i), WallTile.Type.VERTICAL))
        }
        for (i in 1..4) {
            objects.add(WallTile(Vector2D(i, 6), WallTile.Type.HORIZONTAL))
        }
        objects.add(WallTile(Vector2D(5, 6), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR))
        for (i in 7..20) {
            objects.add(WallTile(Vector2D(i, 6), WallTile.Type.HORIZONTAL))
        }
        for (i in 1..5) {
            objects.add(WallTile(Vector2D(0, i), WallTile.Type.VERTICAL))
        }
        objects.add(WallTile(Vector2D(24, 4), WallTile.Type.LEFT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(25, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(26, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(27, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(28, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(29, 4), WallTile.Type.RIGHT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(29, 5), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(29, 6), WallTile.Type.RIGHT_LOWER_EDGE))
        objects.add(WallTile(Vector2D(28, 6), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(27, 6), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(25, 6), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(24, 6), WallTile.Type.LEFT_LOWER_EDGE))
        objects.add(WallTile(Vector2D(24, 5), WallTile.Type.VERTICAL))

        objects.add(WallTile(Vector2D(24, 5), WallTile.Type.VERTICAL))
        for (i in 3..11) {
            if (i != 8)
                objects.add(WallTile(Vector2D(i, 2), WallTile.Type.HORIZONTAL))
            else
                objects.add(WallTile(Vector2D(i, 2), WallTile.Type.HORIZONTAL_DOWN_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(8, 3), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(15, 3), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(5, 4), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(5, 5), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(8, 4), WallTile.Type.LEFT_LOWER_EDGE))
        objects.add(WallTile(Vector2D(15, 4), WallTile.Type.RIGHT_LOWER_EDGE))
        objects.add(WallTile(Vector2D(9, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(10, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(12, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(13, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(14, 4), WallTile.Type.HORIZONTAL))
        objects.add(WallTile(Vector2D(32, 0), WallTile.Type.LEFT_UPPER_EDGE))
        for (i in 33..57) {
            if (i != 43 && i != 53)
                objects.add(WallTile(Vector2D(i, 0), WallTile.Type.HORIZONTAL))
            else
                objects.add(WallTile(Vector2D(i, 0), WallTile.Type.HORIZONTAL_DOWN_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(58, 0), WallTile.Type.RIGHT_UPPER_EDGE))
        for (i in 1..13) {
            if (i != 7)
                objects.add(WallTile(Vector2D(58, i), WallTile.Type.VERTICAL))
            else
                objects.add(WallTile(Vector2D(58, i), WallTile.Type.VERTICAL_LEFT_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(58, 14), WallTile.Type.RIGHT_LOWER_EDGE))
        for (i in 33..57) {
            if (i != 44)
                objects.add(WallTile(Vector2D(i, 14), WallTile.Type.HORIZONTAL))
            else
                objects.add(WallTile(Vector2D(i, 14), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(32, 14), WallTile.Type.LEFT_LOWER_EDGE))
        for (i in 9..13) {
            if (i != 11)
                objects.add(WallTile(Vector2D(32, i), WallTile.Type.VERTICAL))
            else
                objects.add(WallTile(Vector2D(32, i), WallTile.Type.VERTICAL_RIGHT_T_CONNECTOR))
        }
        for (i in 1..7) {
            if (i != 5)
                objects.add(WallTile(Vector2D(32, i), WallTile.Type.VERTICAL))
            else
                objects.add(WallTile(Vector2D(32, i), WallTile.Type.VERTICAL_RIGHT_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(43, 1), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(43, 3), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(43, 4), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(43, 5), WallTile.Type.RIGHT_LOWER_EDGE))
        for (i in 33..42) {
            objects.add(WallTile(Vector2D(i, 5), WallTile.Type.HORIZONTAL))
        }
        objects.add(WallTile(Vector2D(53, 1), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(53, 3), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(53, 4), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(53, 5), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(53, 6), WallTile.Type.VERTICAL))
        for (i in 38..49) {
            objects.add(WallTile(Vector2D(i, 7), WallTile.Type.HORIZONTAL))
        }
        for (i in 51..57) {
            if (i != 53)
                objects.add(WallTile(Vector2D(i, 7), WallTile.Type.HORIZONTAL))
            else
                objects.add(WallTile(Vector2D(i, 7), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(37, 7), WallTile.Type.LEFT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(37, 8), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(37, 9), WallTile.Type.VERTICAL))
        objects.add(WallTile(Vector2D(37, 10), WallTile.Type.VERTICAL))
        for (i in 33..43) {
            if (i != 37)
                objects.add(WallTile(Vector2D(i, 11), WallTile.Type.HORIZONTAL))
            else
                objects.add(WallTile(Vector2D(i, 11), WallTile.Type.HORIZONTAL_UP_T_CONNECTOR))
        }
        objects.add(WallTile(Vector2D(44, 11), WallTile.Type.RIGHT_UPPER_EDGE))
        objects.add(WallTile(Vector2D(44, 13), WallTile.Type.VERTICAL))
        objects.add(Door(Vector2D(32, 8)))
        objects.add(Door(Vector2D(6, 6)))
        objects.add(SmallDoor(Vector2D(26, 6)))

        map.addObject(player)
        for (`object` in objects) {
            map.addObject(`object`)
        }

        val minPos = Vector2D(2, 2)
        val maxPos = Vector2D(58, 12)

        NPCSpawner.spawnRandomNPCs(player, map, minPos, maxPos)
        ItemSpawner.startSpawningItems(map, minPos, maxPos)

        for (i in 0..3) {
            map.addObject(Coin(ItemSpawner.getRandomLocation(map, minPos, maxPos)))
        }
    }

    fun getObjects(): List<BaseObject> {
        return Collections.unmodifiableList(objects)
    }
}
