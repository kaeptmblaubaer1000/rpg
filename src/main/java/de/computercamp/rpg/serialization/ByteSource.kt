package de.computercamp.rpg.serialization

import java.util.*

interface ByteSource {
    /** Reads a byte from this source.
     * This function returns a [Short] because it's the smallest integer type that supports 255.
     *
     * If this returns -1, this source is used up.
     */
    fun readByte(): Short

    fun readBytes(amoumt: Int): ShortArray {
        val array = ShortArray(amoumt)
        for (i in 0..amoumt) {
            val byte = readByte()
            if (byte == (-1).toShort()) {
                return Arrays.copyOf(array, i)
            }
            array[i] = byte
        }
        return array
    }
}
