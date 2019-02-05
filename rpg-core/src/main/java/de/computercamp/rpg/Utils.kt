package de.computercamp.rpg

import kotlin.contracts.*

internal inline fun <T> block(block: () -> T): T {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    return block()
}
