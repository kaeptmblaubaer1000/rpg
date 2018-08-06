package de.computercamp.rpg;

import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public static final char VERTICAL_LINE = '─';
    public static final char HORIZONTAL_LINE = '│';

    public static final char LEFT_UPPER_EDGE = '┌';
    public static final char RIGHT_UPPER_EDGE = '┐';
    public static final char LEFT_LOWER_EDGE = '└';
    public static final char RIGHT_LOWER_EDGE = '┘';


    private List<BaseObject> mapContents = new ArrayList<>();

    public boolean removeObject(BaseObject base) {
        if(base.getMap() == this)
            base.setMap(null);
        return mapContents.removeIf((object) -> base == object);
    }

    public void addObject(BaseObject object) {
        object.setMap(this);
        if(!mapContents.contains(object)) {
            mapContents.add(object);
        }
    }

    public void onPlayerMove(Player player) {
        for(BaseObject object: mapContents) {
            object.onPlayerMove(player);
        }
    }
}
