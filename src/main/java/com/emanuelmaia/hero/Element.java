package com.emanuelmaia.hero;

import com.googlecode.lanterna.graphics.TextGraphics;
import org.w3c.dom.Text;

public abstract class Element {
    private Position position;

    public Element(int x, int y) {
        //position = new Position(x, y);
    }

    public abstract void draw(TextGraphics graphics);

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }
}