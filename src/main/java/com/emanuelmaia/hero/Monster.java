package com.emanuelmaia.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element {
    private Position position;

    public Monster(int x, int y) {
        super(x, y);
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#800a0a"));
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "m");
    }

    public Position move() {
        return null;
    }

}
