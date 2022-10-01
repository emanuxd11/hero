package com.emanuelmaia.hero;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private final int width, height;

    private List<Wall> walls;
    private List<Coin> coins;

    Hero hero = new Hero(10, 10);

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(TextGraphics graphics) {
        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');

        for(Wall wall : walls)
            wall.draw(graphics);

        for(Coin coin : coins){
            coin.draw(graphics);
        }

        hero.draw(graphics);
    }

    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        if(key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }

        if(key.getKeyType() == KeyType.EOF) {
            return false;
        }

        switch (key.getKeyType()) {
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

    public void moveHero(Position position) {
        if(canHeroMove(position)) {
            hero.setPosition(position);
            this.retrieveCoins();
        }
    }

    public boolean canHeroMove(Position position) {
        for(Wall wall: walls) {
            if(wall.getPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for(int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for(int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }
        
        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        Position coin_pos;
        boolean keep_searching = false;

        for(int i = 0; i < 5; i++) {
            //Make sure coin doesn't spawn on top of hero
            do {
                coin_pos = new Position(random.nextInt(width - 2) + 1,
                        random.nextInt(height - 2) + 1);

                //Make sure it also doesn't spawn on top of another coin
                for(Coin coin : coins) {
                  if(coin_pos.equals(coin.getPosition())) {
                      keep_searching = true;
                      break;
                  }
                }

            } while(coin_pos.equals(hero.getPosition()) && keep_searching);

            coins.add(new Coin(coin_pos.getX(), coin_pos.getY()));
        }

        return coins;
    }

    private void retrieveCoins() {
        for(Coin coin : coins) {
            if(hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }
}
