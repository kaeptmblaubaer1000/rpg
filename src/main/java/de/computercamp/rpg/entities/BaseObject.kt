package de.computercamp.rpg.entities

import de.computercamp.rpg.Game
import de.computercamp.rpg.Map
import de.computercamp.rpg.Vector2D
import org.jetbrains.annotations.Contract

abstract class BaseObject(game: Game, position: Vector2D) {
    val game = game
        @Contract(pure = true) get
    open var position = position
        @Contract(pure = true) get() = field
    lateinit var map: Map
        @Contract(pure = true) get

    open fun up() {
        position.y--
    }

    open fun down() {
        position.y++
    }

    open fun left() {
        position.x--
    }

    open fun right() {
        position.x++
    }

    abstract fun oldRender(): Char

    open fun onPlayerMove(player: Player): Boolean = true

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as BaseObject

        if (position != that.position)
            return false
        return map == that.map
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + map.hashCode()
        return result
    }
}
