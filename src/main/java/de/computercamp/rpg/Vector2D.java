package de.computercamp.rpg;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public final class Vector2D {

    public int x;
    public int y;

    public Vector2D(@NotNull Vector2D clone) {
        this.x = clone.x;
        this.y = clone.y;
    }

    public Vector2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @NotNull
    @Contract("_ -> new")
    public Vector2D withY(int y) {
        return new Vector2D(x, y);
    }

    @NotNull
    @Contract("_ -> new")
    public Vector2D withX(int x) {
        return new Vector2D(x, y);
    }

    @Contract(value = "null -> false", pure = true)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2D vector2D = (Vector2D) o;

        if (x != vector2D.x) return false;
        return y == vector2D.y;
    }

    @Contract(pure = true)
    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @NotNull
    @Contract(pure = true)
    @Override
    public String toString() {
        return "x=" + x + ", y=" + y;
    }
}
