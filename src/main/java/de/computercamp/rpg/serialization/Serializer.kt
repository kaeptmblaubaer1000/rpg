package de.computercamp.rpg.serialization

import de.computercamp.rpg.Game

object Serializer {
    fun writeSaveGame(sink: ByteSink, game: Game) {
        val writer = Writer(sink)
        writer.writeBytes(MAGIC)
        writer.writeBigInt(1)
    }
}
