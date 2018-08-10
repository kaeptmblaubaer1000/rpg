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
}
