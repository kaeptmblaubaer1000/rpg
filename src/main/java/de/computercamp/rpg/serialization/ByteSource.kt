package de.computercamp.rpg.serialization

interface ByteSource {
    /** Reads a byte from this source.
     * This function returns a [Short] because it's the smallest integer type that supports 255.
     */
    fun readByte(): Short
}
