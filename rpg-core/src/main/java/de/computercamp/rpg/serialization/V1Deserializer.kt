package de.computercamp.rpg.serialization

import de.computercamp.rpg.Game

object V1Deserializer : Deserializer {
    override fun readSaveGame(source: ByteSource): Game {
        val game = Game()

        return game
    }
}
