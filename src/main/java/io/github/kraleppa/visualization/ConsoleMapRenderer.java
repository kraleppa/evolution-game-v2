package io.github.kraleppa.visualization;

import io.github.kraleppa.map.Field;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ConsoleMapRenderer {
    private static final String EMPTY_CELL = " ";
    private static final String FRAME_SEGMENT = "-";
    private static final String CELL_SEGMENT = "|";

    private final WorldMap worldMap;

    private String drawFrame(boolean innerSegment) {
        if (innerSegment) {
            return FRAME_SEGMENT + FRAME_SEGMENT;
        } else {
            return FRAME_SEGMENT;
        }
    }

    private String drawObject(Vector2D currentPosition) {
        Field field = worldMap.getField(currentPosition);
        return field.getAnimals().size() == 0 ? EMPTY_CELL : String.valueOf(field.getAnimals().size());
    }

    private String drawHeader(Vector2D lowerLeft, Vector2D upperRight) {
        StringBuilder builder = new StringBuilder();
        builder.append(" y\\x ");
        for (int j = lowerLeft.x; j < upperRight.x + 1; j++) {
            builder.append(String.format("%2d", j));
        }
        builder.append(System.lineSeparator());
        return builder.toString();
    }

    public String draw(Vector2D lowerLeft, Vector2D upperRight) {
        StringBuilder builder = new StringBuilder();
        for (int i = upperRight.y + 1; i >= lowerLeft.y - 1; i--) {
            if (i == upperRight.y + 1) {
                builder.append(drawHeader(lowerLeft, upperRight));
            }
            builder.append(String.format("%3d: ", i));
            for (int j = lowerLeft.x; j <= upperRight.x + 1; j++) {
                if (i < lowerLeft.y || i > upperRight.y) {
                    builder.append(drawFrame(j <= upperRight.x));
                } else {
                    builder.append(CELL_SEGMENT);
                    if (j <= upperRight.x) {
                        builder.append(drawObject(new Vector2D(j, i)));
                    }
                }
            }
            builder.append(System.lineSeparator());
        }
        return builder.toString();
    }

    public String draw(){
        return draw(new Vector2D(0, 0), worldMap.upperRight.subtract(new Vector2D(1, 1)));
    }
}
