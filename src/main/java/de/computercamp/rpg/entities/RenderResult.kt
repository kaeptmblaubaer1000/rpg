package de.computercamp.rpg.entities

import de.computercamp.rpg.block

/**
 * WARNING: Do not use multiple characters!
 * This is only for UTF-16 surrogates!
 */
data class RenderResult(val char1: Char, val char2: Char) {
    constructor(str: String): this(str[0], block {-> String
        val len = str.length
        when(len) {
            1 -> '\u0000'
            2 -> str[1]
            else -> throw UnsupportedOperationException("RenderResult only supports two characters, but ${len} were given!")
        }
    }) {}
}
