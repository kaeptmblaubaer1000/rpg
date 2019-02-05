package de.computercamp.rpg.serialization

interface ByteSink {
    fun writeByte(value: Short)

    fun writeBytes(values: List<Short>) {
        writeBytes(values.toShortArray())
    }

    fun writeBytes(values: ShortArray) {
        for (value in values) {
            writeByte(value)
        }
    }
}
