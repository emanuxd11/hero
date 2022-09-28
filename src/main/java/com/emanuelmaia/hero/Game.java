package com.emanuelmaia.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import javax.swing.*;
import java.io.IOException;
import java.security.Key;

public class Game {

    public Screen screen;

    Hero hero = new Hero(10, 10);

    Arena arena = new Arena(79, 23);

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);

            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.draw(screen);
        screen.refresh();
    }

    public void moveHero(Position position) {
        if(canHeroMove(position)) {
            hero.setPosition(position);
        }
    }

    public boolean canHeroMove(Position position) {
        if (position.getX() < 0 || position.getX() > arena.getWidth()
                || position.getY() < 0 || position.getY() > arena.getHeight()) {
            return false;
        } else {
            return true;
        }
    }

    private boolean processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        System.out.println(key);

        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }

        if(key.getKeyType() == KeyType.EOF) {
            return false;
        }

        switch(key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
        }

        return true;
    }

    public void run() throws IOException {
        com.googlecode.lanterna.input.KeyStroke key;
        do {
            draw();
            key = screen.readInput();
        } while (processKey(key));
    }
}
