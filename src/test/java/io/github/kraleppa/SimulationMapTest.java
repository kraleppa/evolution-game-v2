package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.entities.Entity;
import io.github.kraleppa.map.SimulationMap;
import io.github.kraleppa.util.Direction;
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
    public void placeEntityTest1(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D entityPosition = new Vector2D(1, 2);
        SimulationMap simulationMap = new SimulationMap(upperRight);

        //when
        simulationMap.placeEntity(new Animal(entityPosition));

        //then
        Assertions.assertEquals(1, simulationMap.getField(entityPosition).getEntities().size());
        Assertions.assertEquals(0, simulationMap.getField(new Vector2D(0, 0)).getEntities().size());
    }

    @Test
    public void placeEntityTest2(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D entityPosition = new Vector2D(4, 2);
        SimulationMap simulationMap = new SimulationMap(upperRight);

        //when then
        Assertions.assertThrows(IllegalArgumentException.class, () -> simulationMap.placeEntity(new Animal(entityPosition)));
    }

    @Test
    public void isPositionLegalTest1(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        SimulationMap simulationMap = new SimulationMap(upperRight);
        Vector2D position = new Vector2D(4, 3);

        //when
        boolean result = simulationMap.isPositionLegal(position);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void isPositionLegalTest2(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        SimulationMap simulationMap = new SimulationMap(upperRight);
        Vector2D position = new Vector2D(-1, 0);

        //when
        boolean result = simulationMap.isPositionLegal(position);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void isPositionLegalTest3(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        SimulationMap simulationMap = new SimulationMap(upperRight);
        Vector2D position = new Vector2D(0, 0);

        //when
        boolean result = simulationMap.isPositionLegal(position);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void removeEntityTest1(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D entityPosition = new Vector2D(0, 0);
        Entity entity = new Animal(entityPosition, Direction.N);
        SimulationMap simulationMap = new SimulationMap(upperRight);
        simulationMap.placeEntity(entity);

        //when
        simulationMap.removeEntity(entity);

        //then
        Assertions.assertEquals(0, simulationMap.getField(entityPosition).getEntities().size());
    }

    @Test
    public void removeEntityTest2(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D entityPosition = new Vector2D(0, 0);
        Entity entity1 = new Animal(entityPosition, Direction.N);
        Entity entity2 = new Animal(entityPosition, Direction.N);
        SimulationMap simulationMap = new SimulationMap(upperRight);
        simulationMap.placeEntity(entity1);
        simulationMap.placeEntity(entity2);

        //when
        simulationMap.removeEntity(entity1);
        simulationMap.removeEntity(entity1);

        //then
        Assertions.assertEquals(1, simulationMap.getField(entityPosition).getEntities().size());
    }


    @Test
    public void removeEntityTest3(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D entityPosition = new Vector2D(7, 2);
        Entity entity = new Animal(entityPosition, Direction.N);
        SimulationMap simulationMap = new SimulationMap(upperRight);

        //when then
        Assertions.assertThrows(IllegalArgumentException.class, () -> simulationMap.removeEntity(entity));
    }
}
