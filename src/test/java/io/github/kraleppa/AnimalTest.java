package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.map.SimulationMap;
import io.github.kraleppa.util.Direction;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {
    @Test
    public void moveTest1(){
        //given
        SimulationMap simulationMap = new SimulationMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition, Direction.N);

        simulationMap.placeEntity(animal);

        //when
        animal.move();

        //then
        Assertions.assertEquals(new Vector2D(0, 1), animal.position);
        Assertions.assertEquals(1, simulationMap.getField(animal.position).getEntities().size());
        Assertions.assertEquals(0, simulationMap.getField(animalPosition).getEntities().size());
    }

    @Test
    public void moveTest2(){
        //given
        SimulationMap simulationMap = new SimulationMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(1, 1);
        Animal animal = new Animal(animalPosition, Direction.N);

        simulationMap.placeEntity(animal);

        //when
        animal.move();

        //then
        Assertions.assertEquals(animalPosition, animal.position);
        Assertions.assertEquals(1, simulationMap.getField(animalPosition).getEntities().size());
    }

    @Test
    public void moveTest3(){
        //given
        SimulationMap simulationMap = new SimulationMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition, Direction.SW);

        simulationMap.placeEntity(animal);

        //when
        animal.move();

        //then
        Assertions.assertEquals(animalPosition, animal.position);
        Assertions.assertEquals(1, simulationMap.getField(animalPosition).getEntities().size());
    }

    @Test
    public void moveTest4(){
        //given
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition, Direction.SW);

        //when then
        Assertions.assertThrows(IllegalStateException.class, animal::move);
    }
}
