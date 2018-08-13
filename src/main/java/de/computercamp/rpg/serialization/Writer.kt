package de.computercamp.rpg.serialization

class Writer(private val sink: ByteSink) : ByteSink {
    override fun writeByte(value: Short) = sink.writeByte(value)

    fun writeInt(value: Int) {
    }
}