package de.computercamp.rpg.entities;

import de.computercamp.rpg.Map;
import de.computercamp.rpg.Vector2D;

public abstract class BaseObject {
    protected Vector2D position;
    protected Map map;

    public BaseObject(Vector2D position) {
        this.position = position;
    }

    public Vector2D getPosition() {
        return position;
    }

    public void setPosition(Vector2D position) {
        this.position = position;
    }

    public void up() {
        position.y--;
    }

    public void down() {
        position.y++;
    }

    public void left() {
        position.x--;
    }

    public void right() {
        position.x++;
    }

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    abstract public char render();

    public boolean onPlayerMove(Player player) {
        //return true;
        return (player.position.x >= 0 && player.position.y >= 0 && player.position.x < 60 && player.position.y < 16);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BaseObject that = (BaseObject) o;

        if (getPosition() != null ? !getPosition().equals(that.getPosition()) : that.getPosition() != null)
            return false;
        return getMap() != null ? getMap().equals(that.getMap()) : that.getMap() == null;
    }

    @Override
    public int hashCode() {
        int result = getPosition() != null ? getPosition().hashCode() : 0;
        result = 31 * result + (getMap() != null ? getMap().hashCode() : 0);
        return result;
    }
}
