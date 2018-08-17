// WARNING!!! Generated from walkthrough_objects.csv by generate_objects.kts
package de.computercamp.rpg.entities.generated.walkthrough

import de.computercamp.rpg.Game
import de.computercamp.rpg.Vector2D
import de.computercamp.rpg.entities.BaseObject
import de.computercamp.rpg.entities.Player

class Door(game: Game, position: Vector2D) : BaseObject(game, position) {

    override fun render() = '\u258D'

    override fun onPlayerMove(player: Player) = true
}
