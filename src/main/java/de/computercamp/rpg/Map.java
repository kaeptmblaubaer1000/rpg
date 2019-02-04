package de.computercamp.rpg;

import de.computercamp.rpg.entities.BaseObject;
import de.computercamp.rpg.entities.Player;
import de.computercamp.rpg.entities.RenderResult;
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


    private final List<BaseObject> mapContents = new ArrayList<>();

    /**
     * If you use this method, either move the object to a different {@link Map} or see it as destroyed because the {@link BaseObject} will still think it's in this {@link Map}.
     * If you just move it into the {@link Player}'s {@link Player#getInventory()}, that's ok.
     *
     * @param base the object to remove
     * @return same as {@link List#remove(Object)}
     */
    public synchronized boolean removeObject(BaseObject base) {
        return mapContents.removeIf(object -> base == object);
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

    /**
     * @param player the player that moves
     * @return if the move should be aborted
     */
    public boolean onPlayerMove(Player player) {
        if (!((player.getPosition().x >= 0) && (player.getPosition().y >= 0) && (player.getPosition().x < 60) && (player.getPosition().y < 16))) {
            return false;
        }
        for (BaseObject object : new ArrayList<>(mapContents)) {
            if (!object.onPlayerMove(player)) {
                return false;
            }
        }
        return true;
    }

    /**
     * TODO: Use {@link Collectors#joining(CharSequence)} to make the result
     * @return the rendered {@link Map}
     */
    public synchronized String render() {
        final int maxX = 60;
        final int maxY = 16;

        StringBuilder whole = new StringBuilder();
        List<List<RenderResult>> strings = new ArrayList<>(maxY);

        for (int y = 0; y < maxY; y++) {
            List<RenderResult> row = new ArrayList<>(maxX);
            for (int x = 0; x < maxX; x++) {
                row.add(RenderResult.NONE);
            }

            strings.add(row);
        }

        for (BaseObject object : mapContents) {
            strings.get(object.getPosition().y).set(object.getPosition().x, object.render());
        }

        for (BaseObject object : mapContents.stream().filter(NPC.class::isInstance).collect(Collectors.toList())) {
            strings.get(object.getPosition().y).set(object.getPosition().x, object.render());
        }

        for (BaseObject object : mapContents.stream().filter(Player.class::isInstance).collect(Collectors.toList())) {
            strings.get(object.getPosition().y).set(object.getPosition().x, object.render());
        }

        for (List<RenderResult> row : strings.subList(0, strings.size() - 1)) {
            for (RenderResult renderResult : row) {
                whole.append(renderResult);
            }
            whole.append('\n');
        }

        if (strings.size() > 0) {
            for (RenderResult renderResult : strings.get(strings.size() - 1)) {
                whole.append(renderResult);
            }
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
