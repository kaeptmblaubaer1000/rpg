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

    public void remove(BaseObject base) {
        for (List<BaseObject> list : mapContents) {
            list.replaceAll((object) -> base == object ? null : object);
        }
    }


}
