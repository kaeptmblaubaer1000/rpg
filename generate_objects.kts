import java.io.File

val walkthroughGeneratedDirectoryFile = File("src/main/java/de/computercamp/rpg/entities/generated/walkthrough")
val walkthroughDataFile = File("walkthrough_objects.csv")

data class WalkThroughObjectData(val className: String, val rendered: String)

val data = walkthroughDataFile.readLines().filter { !it.isBlank() }.map { it.splitToSequence(';', ignoreCase = false, limit = 2).toList() }.map { WalkThroughObjectData(it[0], it[1]) }

walkthroughGeneratedDirectoryFile.listFiles().map(File::delete)

for (data in data) {
    File(walkthroughGeneratedDirectoryFile, "${data.className}.kt").writeText("""// WARNING!!! Generated from walkthrough_objects.csv by generate_objects.kts
package de.computercamp.rpg.entities.generated.walkthrough

import de.computercamp.rpg.Game
import de.computercamp.rpg.Vector2D
import de.computercamp.rpg.entities.BaseObject
import de.computercamp.rpg.entities.Player

class ${data.className}(game: Game, position: Vector2D) : BaseObject(game, position) {

    override fun render() = '${data.rendered}'

    override fun onPlayerMove(player: Player) = true
}
""", charset = Charsets.UTF_8)
}
