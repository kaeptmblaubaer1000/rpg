package de.computercamp.rpg.entities

/**
 * WARNING: Do not use multiple characters!
 * This is only for UTF-16 surrogates!
 */
data class RenderResult private constructor(val char1: Char, val char2: Char) {
    val string: String = if (char2 == '\u0000') "$char1" else "$char1$char2"

    override fun toString() = string

    companion object {
        /**
         * This function is currently only a constructor call, but it will get a cache.
         *
         * @param char1 the first character, may not be 0
         * @param char2 the second character, use 0 if none is present or use it as continuation of [char1]
         */
        @JvmStatic //TODO: remove this
        fun from(char1: Char, char2: Char): RenderResult {
            //XXX: add that cache
            return RenderResult(char1, char2)
        }

        @JvmStatic //TODO: remove this
        fun from(char1: Char) = from(char1, '\u0000')

        @JvmStatic //TODO: remove this
        fun from(str: String): RenderResult {
            val len = str.length
            return from(str[0], when (len) {
                1 -> '\u0000'
                2 -> str[1]
                else -> throw UnsupportedOperationException("RenderResult only supports two characters, but $len were given!")
            })
        }
    }
}
