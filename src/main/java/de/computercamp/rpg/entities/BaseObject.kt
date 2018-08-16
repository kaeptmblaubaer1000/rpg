package de.computercamp.rpg.entities

import de.computercamp.rpg.Map
import de.computercamp.rpg.Vector2D

abstract class BaseObject(open var position: Vector2D) {
    var map: Map? = null

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

    abstract fun render(): Char

    open fun onPlayerMove(player: Player): Boolean {
        return player.position.x >= 0 && player.position.y >= 0 && player.position.x < 60 && player.position.y < 16 // TODO: move this away from onPlayerMove, maybe into Map.onPlayerMove
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val that = other as BaseObject

        if (position != that.position)
            return false
        return if (map != null) map == that.map else that.map == null
    }

    override fun hashCode(): Int {
        var result = position.hashCode()
        result = 31 * result + if (map != null) map!!.hashCode() else 0
        return result
    }
}
