package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.managers.GrowthManager;
import io.github.kraleppa.map.WorldMap;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class WorldMapTest {
    @Test
    public void initializationTest(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);

        //when
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));

        //then
        for (int x = 0; x < upperRight.x; x++){
            for (int y = 0; y < upperRight.y; y++){
                Assertions.assertNotNull(worldMap.getField(new Vector2D(x, y)));
            }
        }
    }

    @Test
    public void placeAnimalTest1(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D animalPosition = new Vector2D(1, 2);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));

        //when
        worldMap.placeAnimal(new Animal(animalPosition));

        //then
        Assertions.assertEquals(1, worldMap.getField(animalPosition).getAnimals().size());
        Assertions.assertEquals(0, worldMap.getField(new Vector2D(0, 0)).getAnimals().size());
    }

    @Test
    public void placeAnimalTest2(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D animalPosition = new Vector2D(4, 2);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));

        //when then
        Assertions.assertThrows(IllegalArgumentException.class, () -> worldMap.placeAnimal(new Animal(animalPosition)));
    }

    @Test
    public void isPositionLegalTest1(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));
        Vector2D position = new Vector2D(4, 3);

        //when
        boolean result = worldMap.isPositionLegal(position);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void isPositionLegalTest2(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));
        Vector2D position = new Vector2D(-1, 0);

        //when
        boolean result = worldMap.isPositionLegal(position);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void isPositionLegalTest3(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));
        Vector2D position = new Vector2D(0, 0);

        //when
        boolean result = worldMap.isPositionLegal(position);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void removeAnimalTest1(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal = new Animal(animalPosition);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));
        worldMap.placeAnimal(animal);

        //when
        worldMap.removeAnimal(animal);

        //then
        Assertions.assertEquals(0, worldMap.getField(animalPosition).getAnimals().size());
    }

    @Test
    public void removeAnimalTest2(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D animalPosition = new Vector2D(0, 0);
        Animal animal1 = new Animal(animalPosition);
        Animal animal2 = new Animal(animalPosition);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));
        worldMap.placeAnimal(animal1);
        worldMap.placeAnimal(animal2);

        //when
        worldMap.removeAnimal(animal1);

        //then
        Assertions.assertEquals(1, worldMap.getField(animalPosition).getAnimals().size());
    }


    @Test
    public void removeAnimalTest3(){
        //given
        Vector2D upperRight = new Vector2D(3, 3);
        Vector2D animalPosition = new Vector2D(7, 2);
        Animal animal = new Animal(animalPosition);
        WorldMap worldMap = new WorldMap(upperRight, new GrowthManager(new Vector2D(0, 0), new Vector2D(0, 0)));

        //when then
        Assertions.assertThrows(IllegalArgumentException.class, () -> worldMap.removeAnimal(animal));
    }
}
