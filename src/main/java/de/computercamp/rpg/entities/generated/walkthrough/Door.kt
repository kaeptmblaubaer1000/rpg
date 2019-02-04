// WARNING!!! Generated from walkthrough_objects.csv by generate_objects.kts
package de.computercamp.rpg.entities.generated.walkthrough

import de.computercamp.rpg.Game
import de.computercamp.rpg.Vector2D
import de.computercamp.rpg.entities.BaseObject
import de.computercamp.rpg.entities.Player
import de.computercamp.rpg.entities.RenderResult

class Door(game: Game, position: Vector2D) : BaseObject(game, position) {
    companion object {
        val rendered: RenderResult = RenderResult.from('\u258D')
    }

    override fun render() = rendered

    override fun onPlayerMove(player: Player) = true
}
