package io.github.kraleppa.map;

import io.github.kraleppa.util.Vector2D;

import java.util.HashMap;
import java.util.Map;

public class SimulationMap {
    private final Map<Vector2D, Field> fields = new HashMap<>();
    private final Vector2D upperRight;

    public SimulationMap(Vector2D upperRight) {
        this.upperRight = upperRight;

        for (int x = 0; x < upperRight.x; x++){
            for (int y = 0; y < upperRight.y; y++){
                Vector2D vector2D = new Vector2D(x, y);
                fields.put(vector2D, new Field(vector2D));
            }
        }
    }
}
