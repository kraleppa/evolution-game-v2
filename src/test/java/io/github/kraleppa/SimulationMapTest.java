package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.map.SimulationMap;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class SimulationMapTest {
    @Test
    public void initializationTest(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);

        //when
        SimulationMap simulationMap = new SimulationMap(upperRight);

        //then
        for (int x = 0; x < upperRight.x; x++){
            for (int y = 0; y < upperRight.y; y++){
                Assertions.assertNotNull(simulationMap.getField(new Vector2D(x, y)));
            }
        }
    }

    @Test
    public void putEntityTest(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D entityPosition = new Vector2D(1, 2);
        SimulationMap simulationMap = new SimulationMap(upperRight);

        //when
        simulationMap.putEntity(new Animal(entityPosition));

        //then
        Assertions.assertEquals(1, simulationMap.getField(entityPosition).getEntities().size());
        Assertions.assertEquals(0, simulationMap.getField(new Vector2D(0, 0)).getEntities().size());
    }
}
