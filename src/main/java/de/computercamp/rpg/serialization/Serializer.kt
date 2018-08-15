package de.computercamp.rpg.serialization

import de.computercamp.rpg.mapgenerator.MapBuilder

object Serializer {
    fun writeSaveGame(sink: ByteSink, mapBuilder: MapBuilder) {
        val writer = Writer(sink)
        writer.writeBytes(MAGIC)
        writer.writeBigInt(1)
    }
}
