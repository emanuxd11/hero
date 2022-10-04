package com.emanuelmaia.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Monster extends Element {
    private Position position;

    public Monster(int x, int y) {
        super(x, y);
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#800a0a"));
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "m");
    }

    public Position move() {
        Random random = new Random();
        int randX = this.getPosition().getX() + random.nextInt(1 - (-1) + 1) + (-1),
                randY = this.getPosition().getY() + random.nextInt(1 - (-1) + 1) + (-1);
        return new Position(randX, randY);
    }

}
