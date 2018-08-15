package de.computercamp.rpg.serialization

class ArrayByteSource(values: ShortArray) : ByteSource {
    private val iterator = values.iterator()

    override fun readByte(): Short {
        return if (iterator.hasNext()) iterator.next() else -1
    }
}
