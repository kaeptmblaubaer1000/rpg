package de.computercamp.rpg

import de.computercamp.rpg.entities.Player
import de.computercamp.rpg.mapgenerator.MapBuilder

class Game {
    companion object {
        fun create(): Game {
            val game = Game()

            return game
        }
    }

    val player = Player(this, Vector2D(1, 1))
    val mapBuilder = MapBuilder(this)
    var currentRoom = mapBuilder.getRoom(0)
        set(value) {
            player.map.removeObject(player)
            currentRoom.removeObject(player)
            value.addObject(player)
            field = value
        }

    init {
        val map = mapBuilder.getRoom(0)
        player.map = map
        // This is done twice because non-nullable fields require initialization
        // and the initializer doesn't call the custom setter
        currentRoom = map
    }
}
