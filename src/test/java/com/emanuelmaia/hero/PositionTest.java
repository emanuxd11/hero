package com.emanuelmaia.hero;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import javax.sql.PooledConnection;

public class PositionTest {
    //penso que não é suposto testar construtores, getters e setters
    private int x, y;

    @BeforeEach
    public void helper() {
        x = 2;
        y = -5;
    }
    /*
    @Test
    public void Position() {
        Position position = new Position(x, y);

        int expectedX = 2;
        int expectedY = -5;

        Assertions.assertEquals(expectedX, position.getX());
        Assertions.assertEquals(expectedY, position.getY());
    }

    @Test
    public void setX() {
        Position position = new Position(x, y);
        position.setX(3);
        Assertions.assertEquals(3, position.getX());
    }

    @Test
    public void setY() {
        Position position = new Position(x, y);
        position.setY(5);
        Assertions.assertEquals(5, position.getY());
    }

    @Test
    public void getX() {
        Position positionStub = Mockito.mock(Position.class);
        Mockito.when(positionStub.setX(Mockito.anyInt())).thenReturn();
    }

    @Test
    public void getY() {

    }*/

    @Test
    public void equals_differ() {
        Position position1 = new Position(2, 5);
        Position position2 = new Position(5, 2);
        boolean expected = false;
        Assertions.assertEquals(expected, position1.equals(position2));
    }

    @Test
    public void equals_equals() {
        Position position1 = new Position(x, y);
        Position position2 = new Position(x, y);
        boolean expected = true;
        Assertions.assertEquals(expected, position1.equals(position2));
    }

    @Test
    public void equals_null() {
        Position position1 = new Position(x, y);
        Position position2 = null;
        boolean expected = false;
        Assertions.assertEquals(expected, position1.equals(position2));
    }

    @Test
    public void equals_same() {
        Position position = new Position(x, y);
        boolean expected = true;
        Assertions.assertEquals(expected, position.equals(position));
    }

    @Test
    public void equals_class() {
        Position position = new Position(x, y);
        Integer integer = 6;
        boolean expected = false;
        Assertions.assertEquals(expected, position.equals(integer));
    }
}
