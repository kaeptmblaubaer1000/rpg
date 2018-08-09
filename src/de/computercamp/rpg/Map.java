package de.computercamp.rpg;

import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Map {
    public static final char VERTICAL_LINE = '\u2500';
    public static final char HORIZONTAL_LINE = '\u2502';

    public static final char LEFT_UPPER_EDGE = '\u250c';
    public static final char RIGHT_UPPER_EDGE = '\u2510';
    public static final char LEFT_LOWER_EDGE = '\u2514';
    public static final char RIGHT_LOWER_EDGE = '\u2518';
    public static final char DOOR = '\u2593';


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

    public BaseObject getObjectByPosition(Vector2D position) {
        for (BaseObject baseObject : mapContents) {
            if (baseObject.getPosition().equals(position) && !(baseObject instanceof Player)) {
                return baseObject;
            }
        }
        return null;
    }

    public List<BaseObject> getMapContents() {
        return Collections.unmodifiableList(mapContents);
    }

    public boolean onPlayerMove(Player player) {
        for (BaseObject object : new ArrayList<>(mapContents)) {
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
        List<StringBuilder> strings = new ArrayList<>(maxY);

        for (int y = 0; y < maxY; y++) {
            StringBuilder row = new StringBuilder(maxX);
            for (int x = 0; x < maxX; x++) {
                row.append(" ");
            }

            strings.add(row);
        }

        for (BaseObject object : mapContents.stream().filter((object) -> !(object instanceof Player)).collect(Collectors.toList())) {
            strings.get(object.getPosition().y).setCharAt(object.getPosition().x, object.render());
        }

        for (BaseObject object : mapContents.stream().filter((object) -> object instanceof Player).collect(Collectors.toList())) {
            strings.get(object.getPosition().y).setCharAt(object.getPosition().x, object.render());
        }

        for (StringBuilder builder : strings.subList(0, strings.size() - 1)) {
            whole.append(builder);
            whole.append('\n');
        }
        if(strings.size() > 0) {
            whole.append(strings.get(strings.size() - 1));
        }

        return whole.toString();
    }


}
