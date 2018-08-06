package de.computercamp.rpg;

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
        return mapContents.removeIf((object) -> base == object);
    }
}
