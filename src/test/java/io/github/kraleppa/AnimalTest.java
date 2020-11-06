package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Direction;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class AnimalTest {
    @Test
    public void moveTest1(){
        //given
        WorldMap worldMap = new WorldMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition, Direction.N);

        worldMap.placeAnimal(animal);

        //when
        boolean result = animal.move(0);

        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(new Vector2D(0, 1), animal.position);
        Assertions.assertEquals(1, worldMap.getField(animal.position).getAnimals().size());
        Assertions.assertEquals(0, worldMap.getField(animalPosition).getAnimals().size());
    }

    @Test
    public void moveTest2(){
        //given
        WorldMap worldMap = new WorldMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(1, 1);
        Animal animal = new Animal(animalPosition, Direction.N);

        worldMap.placeAnimal(animal);

        //when
        boolean result = animal.move(0);

        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(animalPosition, animal.position);
        Assertions.assertEquals(1, worldMap.getField(animalPosition).getAnimals().size());
    }

    @Test
    public void moveTest3(){
        //given
        WorldMap worldMap = new WorldMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition, Direction.SW);

        worldMap.placeAnimal(animal);

        //when
        boolean result = animal.move(0);

        //then
        Assertions.assertTrue(result);
        Assertions.assertEquals(animalPosition, animal.position);
        Assertions.assertEquals(1, worldMap.getField(animalPosition).getAnimals().size());
    }

    @Test
    public void moveTest4(){
        //given
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition, Direction.N);

        //when then
        Assertions.assertThrows(IllegalStateException.class, () -> animal.move(0));
    }

    @Test
    public void moveTest5(){
        //given
        WorldMap worldMap = new WorldMap(new Vector2D(2, 2));
        Vector2D animalPosition = new Vector2D(1, 1);
        Animal animal = new Animal(animalPosition, Direction.N);

        worldMap.placeAnimal(animal);

        //when
        boolean result = animal.move(Integer.MAX_VALUE);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void turnAroundTest1(){
        //given
        Animal animal = new Animal(new Vector2D(3, 3), Direction.N);

        //when
        animal.turnAround(3);

        //then
        Assertions.assertEquals(Direction.SE, animal.direction);
    }

    @Test
    public void turnAroundTest2(){
        //given
        Animal animal = new Animal(new Vector2D(3, 3), Direction.N);
        //when
        animal.turnAround(9);

        //then
        Assertions.assertEquals(Direction.NE, animal.direction);
    }
}
