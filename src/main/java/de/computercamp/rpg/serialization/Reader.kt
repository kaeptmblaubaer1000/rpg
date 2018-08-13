package de.computercamp.rpg.serialization

class Reader(private val source: ByteSource) : ByteSource {
    /** Reads a byte from this source.
     * This function returns a [Short] because it's the smallest integer type that supports 255.
     */
    override fun readByte(): Short = source.readByte()

    fun readBigInt(): Int {
        return 0
    }
}
