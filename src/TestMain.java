import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.NPC;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.WallTile;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map();
        map.addObject(new WallTile(new Vector2D(10, 20), WallTile.Type.HORIZONTAL));
        map.addObject(new Player(new Vector2D(0, 0)));
        map.addObject(new NPC(new Vector2D(0, 1), ""));
        System.out.println(map.render());
    }
}
