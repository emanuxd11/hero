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
    private int x = 10;
    private int y = 10;
    public Game() {
        try {
            TerminalSize terminalSize = new TerminalSize(40, 20);
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
        screen.setCharacter(x, y, TextCharacter.fromCharacter('X')[0]);
        screen.refresh();
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
                y--;
                break;
            case ArrowDown:
                y++;
                break;
            case ArrowLeft:
                x--;
                break;
            case ArrowRight:
                x++;
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