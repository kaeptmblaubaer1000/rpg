package de.computercamp.rpg.serialization

class Reader(private val source: ByteSource) : ByteSource by source {
    fun readBigInt(): Int {
        val length = readByte()
        var value: Int = 0

        for (_ignored in 0..length) {
            value = value shl 8
            value = value or readByte().toInt()
        }
        return value
    }
}
