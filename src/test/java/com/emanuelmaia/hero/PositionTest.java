package com.emanuelmaia.hero;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

public class PositionTest {
    private int x, y;

    @BeforeEach
    public void helper() {
        x = 2;
        y = -5;
    }

    /*@Test
    public void Position() {
        Position position = new Position(x, y);

        int expectedX = 2;
        int expectedY = -5;

        Assertions.assertEquals(expectedX, position.getX());
        Assertions.assertEquals(expectedY, position.getY());
    }*/

    @Test
    public void setX() {
        Position position = new Position(x, y);
        position.setX(3);
        Assertions.assertEquals(3, position.getX());
    }
}
