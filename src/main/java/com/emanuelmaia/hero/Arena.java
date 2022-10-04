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
    private List<Monster> monsters;

    Hero hero = new Hero(10, 10);

    public Arena(int width, int height) {
        this.width = width;
        this.height = height;
        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(TextGraphics graphics) {

        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0),
                new TerminalSize(width, height), ' ');

        graphics.fillRectangle(new TerminalPosition(0, 0), new
                TerminalSize(width, height), ' ');

        for(Wall wall : walls) {
            wall.draw(graphics);
        }

        for(Coin coin : coins) {
            coin.draw(graphics);
        }

        for(Monster monster : monsters) {
            monster.draw(graphics);
        }

        hero.draw(graphics);
    }

    public boolean processKey(KeyStroke key, Screen screen) throws IOException {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            screen.close();
        }

        if (key.getKeyType() == KeyType.EOF) {
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
        if(insideArena(position)) {
            hero.setPosition(position);
            this.retrieveCoins();
            this.moveMonsters();
            this.verifyMonsterCollisions();
        }
    }

    public boolean insideArena(Position position) {
        for (Wall wall : walls) {
            if (wall.getPosition().equals(position)) {
                return false;
            }
        }

        return true;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        Position coin_pos;
        boolean keep_searching;

        for(int i = 0; i < 5; i++) {
            keep_searching = false;
            //Make sure coin doesn't spawn on top of hero
            do {
                coin_pos = new Position(random.nextInt(width - 2) + 1,
                        random.nextInt(height - 2) + 1);

                //Make sure it also doesn't spawn on top of another coin
                for (Coin coin : coins) {
                    if (coin_pos.equals(coin.getPosition())) {
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
        for (Coin coin : coins) {
            if (hero.getPosition().equals(coin.getPosition())) {
                coins.remove(coin);
                break;
            }
        }
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        Position monster_pos;
        boolean keep_searching_c, keep_searching_m;

        for(int i = 0; i < 5; i++) {
            keep_searching_c = false; keep_searching_m = false;
            //Make sure coin doesn't spawn on top of hero
            do {
                monster_pos = new Position(random.nextInt(width - 2) + 1,
                        random.nextInt(height - 2) + 1);

                //Make sure it doesn't spawn on top of a coin
                for (Coin coin : coins) {
                    if (monster_pos.equals(coin.getPosition())) {
                        keep_searching_c = true;
                        break;
                    }
                }

                //Make sure it also doesn't spawn on top of another monster
                for (Monster monster : monsters) {
                    if (monster_pos.equals(monster.getPosition())) {
                        keep_searching_m = true;
                        break;
                    }
                }

            } while(monster_pos.equals(hero.getPosition()) &&
                    keep_searching_c && keep_searching_m);

            monsters.add(new Monster(monster_pos.getX(), monster_pos.getY()));
        }

        return monsters;
    }

    public void moveMonsters() {
        boolean keep_searching_c, keep_searching_m;
        Position potential_pos;

        //can't be one of the coins' position, outside of map or on top of another monster
        for(int i = 0, monstersSize = monsters.size(); i < monstersSize; i++) {
            keep_searching_c = false; keep_searching_m = false;
            Monster monster = monsters.get(i);

            do {
                potential_pos = monster.move();

                //Comentado porque causa crash no jogo
                /*for(Coin coin : coins) {
                    if(coin.getPosition().equals(potential_pos)) {
                        keep_searching_c = true;
                        break;
                    }
                }*/

                /*for(int j = 0; j < i; j++) {
                    Monster curr_monster = monsters.get(j);
                    if(potential_pos.equals(curr_monster.getPosition())) {
                        keep_searching_m = true;
                        break;
                    }
                }*/

            } while(!insideArena(potential_pos));

            monster.setPosition(potential_pos);
        }
    }

    public void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (hero.getPosition().equals(monster.getPosition())) {
                System.out.println("Game over!");
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.exit(0);
                break;
            }
        }
    }
}
