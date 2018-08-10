package de.computercamp.rpg;

public class Vector2D {

    public int x;
    public int y;

    public Vector2D(Vector2D clone) {
        this.x = clone.x;
        this.y = clone.y;
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D withY(int y) {
        return new Vector2D(x, y);
    }

    public Vector2D withX(int x) {
        return new Vector2D(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (x != vector2D.x) return false;
        return y == vector2D.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public String toString() {
        return "x=" + x + ", y=" + y + '}';
    }
}
