package de.computercamp.rpg;

import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;

import java.util.ArrayList;
import java.util.Collections;
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
        if (base.getMap() == this)
            base.setMap(null);
        return mapContents.removeIf((object) -> base == object);
    }

    public void addObject(BaseObject object) {
        object.setMap(this);
        if (!mapContents.contains(object)) {
            mapContents.add(object);
        }
    }

    public List<BaseObject> getMapContents() {
        return Collections.unmodifiableList(mapContents);
    }

    public boolean onPlayerMove(Player player) {
        for (BaseObject object : mapContents) {
            if (!object.onPlayerMove(player)) {
                return false;
            }
        }
        return true;
    }

    public String render() {
        int maxX = mapContents.stream().mapToInt((object) -> object.getPosition().x).max().orElse(-1) + 1;
        int maxY = mapContents.stream().mapToInt((object) -> object.getPosition().y).max().orElse(-1) + 1;

        StringBuilder whole = new StringBuilder();
        List<StringBuilder> strings = new ArrayList<>();

        for (int y = 0; y < maxY; y++) {
            StringBuilder row = new StringBuilder();
            for (int x = 0; x < maxX; x++) {
                row.append(" ");
            }

            strings.add(row);
        }


        for (StringBuilder builder : strings) {
            whole.append(builder);
            whole.append('\n');
        }

        return whole.toString();
    }
}
