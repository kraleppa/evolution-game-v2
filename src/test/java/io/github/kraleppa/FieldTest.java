package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.map.Field;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FieldTest {
    @Test
    public void addAnimalTest(){
        //given
        Field field = new Field(new Vector2D(0, 0));
        Animal animal = new Animal(new Vector2D(0, 0));

        //when
        field.addAnimal(animal);

        //then
        Assertions.assertEquals(1, field.getAnimals().size());
    }


    @Test
    public void removeAnimalTest(){
        //given
        Field field = new Field(new Vector2D(0, 0));
        Animal animal1 = new Animal(new Vector2D(0, 0));
        Animal animal2 = new Animal(new Vector2D(0, 0));

        //when
        field.addAnimal(animal1);
        field.addAnimal(animal2);
        field.removeAnimal(animal1);

        //then
        Assertions.assertEquals(1, field.getAnimals().size());
    }
}
