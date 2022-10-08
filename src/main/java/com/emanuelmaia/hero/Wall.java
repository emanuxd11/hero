package com.emanuelmaia.hero;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.List;

public class Wall extends Element {
    private final Position position;

    public Wall(int x, int y) {
        super(x, y);
        position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#3e0fd9"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(),
                position.getY()), "#");
    }
}
