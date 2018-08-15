package de.computercamp.rpg.serialization

import java.util.*

class ByteSinkSource : ByteSink, ByteSource {
    val queue: Deque<Short> = LinkedList()

    override fun readByte(): Short {
        synchronized(queue) {
            return if (queue.size != 0) queue.removeFirst() else -1
        }
    }

    override fun readBytes(amoumt: Int): ShortArray {
        synchronized(queue) {
            return super.readBytes(amoumt)
        }
    }

    override fun writeByte(value: Short) {
        synchronized(queue) {
            queue.addLast(value)
        }
    }

    override fun writeBytes(values: ShortArray) {
        synchronized(queue) {
            queue.addAll(values.toTypedArray())
        }
    }
}