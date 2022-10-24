package com.emanuelmaia.hero;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.verify;

public class HeroTest {
    private int x, y;

    @BeforeEach
    public void Helper() {
        x = 5;
        y = -10;
    }

    @Test
    public void moveUp() {
        Hero hero = new Hero(x, y);
        hero.setPosition(hero.moveUp());
        int expected = -11;
        Assertions.assertEquals(expected, hero.getPosition().getY());
    }

    @Test
    public void moveDown() {
        Hero hero = new Hero(x, y);
        hero.setPosition(hero.moveDown());
        int expected = -9;
        Assertions.assertEquals(expected, hero.getPosition().getY());
    }

    @Test
    public void moveLeft() {
        Hero hero = new Hero(x, y);
        hero.setPosition(hero.moveLeft());
        int expected = 4;
        Assertions.assertEquals(expected, hero.getPosition().getX());
    }

    @Test
    public void moveRight() {
        Hero hero = new Hero(x, y);
        hero.setPosition(hero.moveRight());
        int expected = 6;
        Assertions.assertEquals(expected, hero.getPosition().getX());
    }

    @Test
    public void drawVerify() {
        Hero hero = new Hero(x, y);
        Screen screen = new TerminalScreen(terminal)
        hero.draw(new TextGraphics());
        //verify();
    }


}
