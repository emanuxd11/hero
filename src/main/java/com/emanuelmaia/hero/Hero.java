package com.emanuelmaia.hero;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends Element {
    public Hero(int x, int y) {
        super(x, y);
    }

    public void setPosition(Position position) {
        position.setX(position.getX());
        position.setY(position.getY());
    }

    public Position moveUp() {
        return new Position(getPosition().getX(), getPosition().getY() - 1);
    }

    public Position moveDown() {
        return new Position(getPosition().getX(), getPosition().getY() + 1);
    }

    public Position moveLeft() {
        return new Position(getPosition().getX() - 1, getPosition().getY());
    }

    public Position moveRight() {
        return new Position(getPosition().getX() + 1, getPosition().getY());
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);

        //For bigger hero
        /*graphics.putString(new TerminalPosition(position.getX() * 2,
                position.getY() * 2), "\\/");
        graphics.putString(new TerminalPosition(position.getX() * 2,
                position.getY() * 2 + 1), "/\\");*/

        graphics.putString(new TerminalPosition(getPosition().getX(), getPosition().getY()), "X");
    }
}
