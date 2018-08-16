package de.computercamp.rpg;

import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.npcs.NPC;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Map {
    public static final char VERTICAL_LINE = '\u2502';
    public static final char HORIZONTAL_LINE = '\u2500';

    public static final char LEFT_UPPER_EDGE = '\u250c';
    public static final char RIGHT_UPPER_EDGE = '\u2510';
    public static final char LEFT_LOWER_EDGE = '\u2514';
    public static final char RIGHT_LOWER_EDGE = '\u2518';

    public static final char VERTICAL_RIGHT_T_CONNECTOR = '\u251C';
    public static final char VERTICAL_LEFT_T_CONNECTOR = '\u2524';
    public static final char HORIZONTAL_DOWN_T_CONNECTOR = '\u252C';
    public static final char HORIZONTAL_UP_T_CONNECTOR = '\u2534';


    private List<BaseObject> mapContents = new ArrayList<>();

    /**
     * If you use this method, either move the object to a different {@link Map} or see it as destroyed because the {@link BaseObject} will still think it's in this {@link Map}.
     * If you just move it into the {@link Player}'s {@link Player#inventory}, that's ok.
     *
     * @param base the object to remove
     * @return same as {@link List#remove(Object)}
     */
    public synchronized boolean removeObject(BaseObject base) {
        return mapContents.removeIf((object) -> base == object);
    }

    public synchronized void addObject(BaseObject object) {
        object.setMap(this);
        if (!mapContents.contains(object)) {
            mapContents.add(object);
        }
    }

    public synchronized BaseObject getObjectByPosition(Vector2D position, boolean includingPlayer) {
        if (includingPlayer) {
            for (BaseObject baseObject : mapContents) {
                if (baseObject.getPosition().equals(position)) {
                    return baseObject;
                }
            }
        } else {
            for (BaseObject baseObject : mapContents) {
                if (baseObject.getPosition().equals(position) && !(baseObject instanceof Player)) {
                    return baseObject;
                }
            }
        }
        return null;
    }

    public synchronized List<BaseObject> getMapContents() {
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

    public synchronized String render() {
        int maxX = 60;
        int maxY = 16;

        StringBuilder whole = new StringBuilder();
        List<StringBuilder> strings = new ArrayList<>(maxY);

        for (int y = 0; y < maxY; y++) {
            StringBuilder row = new StringBuilder(maxX);
            for (int x = 0; x < maxX; x++) {
                row.append(" ");
            }

            strings.add(row);
        }

        for (BaseObject object : mapContents) {
            strings.get(object.getPosition().y).setCharAt(object.getPosition().x, object.render());
        }

        for (BaseObject object : mapContents.stream().filter((object) -> object instanceof NPC).collect(Collectors.toList())) {
            strings.get(object.getPosition().y).setCharAt(object.getPosition().x, object.render());
        }

        for (BaseObject object : mapContents.stream().filter((object) -> object instanceof Player).collect(Collectors.toList())) {
            strings.get(object.getPosition().y).setCharAt(object.getPosition().x, object.render());
        }

        for (StringBuilder builder : strings.subList(0, strings.size() - 1)) {
            whole.append(builder);
            whole.append('\n');
        }
        if (strings.size() > 0) {
            whole.append(strings.get(strings.size() - 1));
        }

        return whole.toString();
    }

    public synchronized int countObjectsOfType(Class<? extends BaseObject> type) {
        return (int) mapContents.stream().filter(type::isInstance).count();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Map map = (Map) o;

        return getMapContents() != null ? getMapContents().equals(map.getMapContents()) : map.getMapContents() == null;
    }

    @Override
    public int hashCode() {
        return getMapContents() != null ? getMapContents().hashCode() : 0;
    }
}
