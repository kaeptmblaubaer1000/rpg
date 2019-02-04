// WARNING!!! Generated from walkthrough_objects.csv by generate_objects.kts
package de.computercamp.rpg.entities.generated.walkthrough

import de.computercamp.rpg.Game
import de.computercamp.rpg.Vector2D
import de.computercamp.rpg.entities.BaseObject
import de.computercamp.rpg.entities.Player
import de.computercamp.rpg.entities.RenderResult

class SmallDoor(game: Game, position: Vector2D) : BaseObject(game, position) {
    companion object {
        val rendered: RenderResult = RenderResult.from('\u2594')
    }

    override fun render() = rendered

    override fun onPlayerMove(player: Player) = true
}
