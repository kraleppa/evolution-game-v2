package io.github.kraleppa;
import io.github.kraleppa.entities.Animal;
import io.github.kraleppa.entities.Entity;
import io.github.kraleppa.entities.Grass;
import io.github.kraleppa.map.Field;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class FieldTest {
    @Test
    public void addEntityTest1(){
        //given
        Field field = new Field();
        Entity entity = new Animal(new Vector2D(0, 0));

        //when
        field.addEntity(entity);

        //then
        Assertions.assertEquals(1, field.getEntities().size());
    }

    @Test
    public void addEntityTest2(){
        //given
        Field field = new Field();
        Entity entity1 = new Animal(new Vector2D(0, 0));
        Entity entity2 = new Grass(new Vector2D(0, 0));

        //when
        field.addEntity(entity1);
        field.addEntity(entity2);

        //then
        Assertions.assertEquals(2, field.getEntities().size());
    }

    @Test
    public void removeEntityTest(){
        //given
        Field field = new Field();
        Entity entity1 = new Animal(new Vector2D(0, 0));
        Entity entity2 = new Animal(new Vector2D(0, 0));

        //when
        field.addEntity(entity1);
        field.addEntity(entity2);
        field.removeEntity(entity2);

        //then
        Assertions.assertEquals(1, field.getEntities().size());
    }
}
