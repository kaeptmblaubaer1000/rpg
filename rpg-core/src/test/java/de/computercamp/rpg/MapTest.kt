package de.computercamp.rpg

import kotlin.test.*

@Ignore("Breaks randomly due to unpredictable dynamic map extensions")
class MapTest {
    var game: Game = Game.create()
    var map: Map = game.currentRoom

    @BeforeTest
    fun setUp() {
        game = Game.create()
        map = game.currentRoom
        // Clear the map here, because this isn't the test for MapBuilder
        for (baseObject in map.mapContents.toTypedArray()) {
            map.removeObject(baseObject)
        }
    }

    @Test
    fun testRenderNothing() {
        assertEquals("${" ".repeat(60)}\n".repeat(16).dropLast(1), map.render());
    }
}
