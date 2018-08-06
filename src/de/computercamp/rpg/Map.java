package de.computercamp.rpg;

import java.util.ArrayList;
import java.util.List;

public class Map {
    public static final String VERTICAL_LINE = "|";
    public static final String HORIZONTAL_LINE = "-";

    public static final String LEFT_UPPER_EDGE = "+";
    public static final String RIGHT_UPPER_EDGE = "+";
    public static final String LEFT_LOWER_EDGE = "+";
    public static final String RIGHT_LOWER_EDGE = "+";

    private List<List<BaseObject>> mapContents = new ArrayList<>();

    public boolean remove(BaseObject base) {
        // TODO: implement
        return false;
    }

}
