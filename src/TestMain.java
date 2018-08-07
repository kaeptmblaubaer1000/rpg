import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;
import de.computercamp.rpg.entities.WallTile;

public class TestMain {
    public static void main(String[] args) {
        Map map = new Map();
        map.addObject(new WallTile(new Vector2D(10, 20), WallTile.Type.HORIZONTAL));
        System.out.println(map.render());
    }
}
