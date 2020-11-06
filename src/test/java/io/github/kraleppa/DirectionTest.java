package io.github.kraleppa;
import io.github.kraleppa.animal.Direction;
import io.github.kraleppa.util.Vector2D;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

public class DirectionTest {
    @Test
    public void nextTest(){
        //given
        Direction direction = Direction.N;

        //when
        Direction result = direction.next();

        //then
        Assertions.assertEquals(Direction.NE, result);
    }

    @Test
    public void toVector2DTest(){
        //given
        Direction direction = Direction.N;

        //when
        Vector2D result = direction.toVector2D();

        //then
        Assertions.assertEquals(new Vector2D(0, 1), result);
    }
}
