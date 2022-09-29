package com.emanuelmaia.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
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

    Arena arena = new Arena(80, 24);

    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(arena.getWidth(), arena.getHeight());
            DefaultTerminalFactory terminalFactory = new
                    DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
            Terminal terminal = terminalFactory.createTerminal();

            screen = new TerminalScreen(terminal);

            TextGraphics graphics = screen.newTextGraphics();
            graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
            graphics.fillRectangle(new TerminalPosition(0, 0),
                    new TerminalSize(arena.getWidth(), arena.getHeight()), ' ');

            screen.setCursorPosition(null); // we don't need a cursor
            screen.startScreen(); // screens must be started
            screen.doResizeIfNecessary(); // resize screen if necessary
            /*screen.clear();
            screen.refresh();*/

        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    private boolean processKey(com.googlecode.lanterna.input.KeyStroke key) throws IOException {
        return arena.processKey(key, screen);
    }

    public void run() throws IOException {
        com.googlecode.lanterna.input.KeyStroke key;
        do {
            draw();
            key = screen.readInput();
        } while(processKey(key));
    }
}
