package de.computercamp.rpg.serialization

import de.computercamp.rpg.Game

interface Deserializer {
    companion object {
        @Throws(WrongMagicException::class, UnsupportedSaveGameVersion::class)
        fun getDeserializer(source: ByteSource): Deserializer {
            val reader = Reader(source)
            val testMagic = source.readBytes(MAGIC.size)
            if (MAGIC.contentEquals(testMagic)) {
                val version = reader.readBigInt()
                when (version) {
                    1 -> return V1Deserializer
                    else -> throw UnsupportedSaveGameVersion(version.toString())
                }
            } else {
                throw WrongMagicException(testMagic.toList().map { it.toByte() }.toByteArray().toString(Charsets.ISO_8859_1))
                // I've used ISO-8859-1 because it represents the whole range and doesn't make any decode errors.
            }
        }
    }

    fun readSaveGame(source: ByteSource): Game

    @Suppress("UNUSED") // The additional constructors should be there once they are used.
    class WrongMagicException : Exception {
        constructor() : super()
        constructor(message: String) : super(message)
        constructor(message: String, cause: Throwable) : super(message, cause)
        constructor(cause: Throwable) : super(cause)
    }

    @Suppress("UNUSED") // The additional constructors should be there once they are used.
    class UnsupportedSaveGameVersion : Exception {
        constructor() : super()
        constructor(message: String) : super(message)
        constructor(message: String, cause: Throwable) : super(message, cause)
        constructor(cause: Throwable) : super(cause)
    }
}
