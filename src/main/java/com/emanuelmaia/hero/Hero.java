package com.emanuelmaia.hero;

import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position position;

    public Hero(int x, int y) {
        position = new Position(x, y);
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    public Position moveUp() {
        return new Position(position.getX(), position.getY() - 1);
    }

    public Position moveDown() {
        return new Position(position.getX(), position.getY() + 1);
    }

    public Position moveLeft() {
        return new Position(position.getX() - 1, position.getY());
    }

    public Position moveRight() {
        return new Position(position.getX() + 1, position.getY());
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);

        //For bigger hero
        /*graphics.putString(new TerminalPosition(position.getX() * 2,
                position.getY() * 2), "\\/");
        graphics.putString(new TerminalPosition(position.getX() * 2,
                position.getY() * 2 + 1), "/\\");*/

        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }

}
