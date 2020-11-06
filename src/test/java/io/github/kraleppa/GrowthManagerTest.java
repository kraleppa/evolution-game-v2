package io.github.kraleppa;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class GrowthManagerTest {
    @Test
    public void initializationTest(){
        //given
        Vector2D jungleLowerLeft = new Vector2D(3, 3);
        Vector2D jungleUpperRight = new Vector2D(7,7);
        GrowthManager growthManager = new GrowthManager(jungleLowerLeft, jungleUpperRight);

        //when
        WorldMap worldMap = new WorldMap(new Vector2D(10, 10), growthManager);

        //then
        Assertions.assertFalse(growthManager.isFieldInJungle(worldMap.getField(new Vector2D(3, 3))));
        Assertions.assertFalse(growthManager.isFieldInJungle(worldMap.getField(new Vector2D(0, 0))));
        Assertions.assertFalse(growthManager.isFieldInJungle(worldMap.getField(new Vector2D(7, 7))));
        Assertions.assertFalse(growthManager.isFieldInJungle(worldMap.getField(new Vector2D(3, 7))));
        Assertions.assertTrue(growthManager.isFieldInJungle(worldMap.getField(new Vector2D(4, 4))));
    }
}
