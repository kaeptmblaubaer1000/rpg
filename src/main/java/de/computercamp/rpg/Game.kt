package de.computercamp.rpg

import de.computercamp.rpg.mapgenerator.MapBuilder

class Game {
    // TODO: Add a game field to BaseObject

    companion object {
        fun create(): Game {
            val game = Game()

            return game
        }
    }
    val mapBuilder = MapBuilder(this)
    var currentRoom = mapBuilder.getRoom(0)
    val player = mapBuilder.player
}