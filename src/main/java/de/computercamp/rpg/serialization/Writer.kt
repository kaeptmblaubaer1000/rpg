package de.computercamp.rpg.serialization

class Writer(private val sink: ByteSink) : ByteSink by sink {
    fun writeBigInt(value: Int) {
        var tempValue = value
        val bytes = mutableListOf<Short>()

        while (tempValue > 0) {
            bytes.add(0, (value and 255).toShort())
            tempValue = tempValue ushr 8
        }
    }
}
