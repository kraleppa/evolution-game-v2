package io.github.kraleppa;

import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class Vector2DTest {
    @Test
    public void precedesTest1(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0, 1);

        //when
        boolean result = vector1.precedes(vector2);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void precedesTest2(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0, 1);

        //when
        boolean result = vector2.precedes(vector1);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void followsTest1(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0, 1);

        //when
        boolean result = vector1.follows(vector2);

        //then
        Assertions.assertTrue(result);
    }

    @Test
    public void followsTest2(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0, 1);

        //when
        boolean result = vector2.follows(vector1);

        //then
        Assertions.assertFalse(result);
    }

    @Test
    public void upperRightTest(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0,2);

        //when
        Vector2D result = vector1.upperRight(vector2);

        //then
        Assertions.assertEquals(new Vector2D(1,2), result);
    }

    @Test
    public void lowerLeftTest(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0,2);

        //when
        Vector2D result = vector1.lowerLeft(vector2);

        //then
        Assertions.assertEquals(new Vector2D(0,1), result);
    }

    @Test
    public void addTest(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0,2);

        //when
        Vector2D result = vector1.add(vector2);

        //then
        Assertions.assertEquals(new Vector2D(1,3), result);
    }

    @Test
    public void subtractTest(){
        //given
        Vector2D vector1 = new Vector2D(1,1);
        Vector2D vector2 = new Vector2D(0,2);

        //when
        Vector2D result = vector1.subtract(vector2);

        //then
        Assertions.assertEquals(new Vector2D(1,-1), result);
    }

    @Test
    public void oppositeTest(){
        //given
        Vector2D vector1 = new Vector2D(-1,1);

        //when
        Vector2D result = vector1.opposite();

        //then
        Assertions.assertEquals(new Vector2D(1,-1), result);
    }





}
