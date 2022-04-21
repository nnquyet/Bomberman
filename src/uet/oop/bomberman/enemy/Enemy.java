package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.entities.Entity;

import javax.imageio.ImageTranscoder;


public abstract class Enemy extends Entity {
    protected double speed;
    protected boolean damaged;
    protected boolean checkDie = false;
    protected int direction = 1;

    public Enemy(int x, int y, Image img) {
        super(x, y, img);
        rectangle =new Rectangle(x, y, 1, 1);
        damaged = false;
    }

    public boolean moveLeft() {
        if (x - Math.floor(x) == 0) {
            if (BombermanGame.map[(int) y].charAt((int) x - 1) != '#'
                    && BombermanGame.map[(int) y].charAt((int) x - 1) != '*'
                    && BombermanGame.map[(int) y].charAt((int) x - 1) != 't') {
                x = (double) Math.round((x - speed) * 1000) / 1000;
                setLocation(x, y);
                return true;
            }
            return false;
        } else {
            x = (double) Math.round((x - speed) * 1000) / 1000;
            setLocation(x, y);
            return true;
        }
    }

    public boolean moveRight() {
        if (x - Math.floor(x) == 0) {
            if (BombermanGame.map[(int) y].charAt((int) x + 1) != '#'
                    && BombermanGame.map[(int) y].charAt((int) x + 1) != '*'
                    && BombermanGame.map[(int) y].charAt((int) x + 1) != 't') {
                x = (double) Math.round((x + speed) * 1000) / 1000;
                setLocation(x, y);
                return true;
            }
            return false;
        } else {
            x = (double) Math.round((x + speed) * 1000) / 1000;
            setLocation(x, y);
            return true;
        }
    }

    public boolean moveUp() {
        if (y - Math.floor(y) == 0) {
            if (BombermanGame.map[(int) y - 1].charAt((int) x) != '#'
                    && BombermanGame.map[(int) y - 1].charAt((int) x) != '*'
                    && BombermanGame.map[(int) y - 1].charAt((int) x) != 't') {
                y = (double) Math.round((y - speed) * 1000) / 1000;
                setLocation(x, y);
                return true;
            }
            return false;
        } else {
            y = (double) Math.round((y - speed) * 1000) / 1000;
            setLocation(x, y);
            return true;
        }
    }

    public boolean moveDown() {
        if (y - Math.floor(y) == 0) {
            if (BombermanGame.map[(int) y + 1].charAt((int) x) != '#'
                    && BombermanGame.map[(int) y + 1].charAt((int) x) != '*'
                    && BombermanGame.map[(int) y + 1].charAt((int) x) != 't') {
                y = (double) Math.round((y + speed) * 1000) / 1000;
                setLocation(x, y);
                return true;
            }
            return false;
        } else {
            y = (double) Math.round((y + speed) * 1000) / 1000;
            setLocation(x, y);
            return true;
        }
    }

    @Override
    public void update() {
        if (checkDie) {
            this.img = null;
        } else {
            if (direction == 1) {
                if (!moveLeft()) direction = (int) (Math.random() * 4 + 1);
            }
            if (direction == 2) {
                if (!moveRight()) direction = (int) (Math.random() * 4 + 1);
            }

            if (direction == 3) {
                if (!moveUp()) direction = (int) (Math.random() * 4 + 1);
            }

            if (direction == 4) {
                if (!moveDown()) direction = (int) (Math.random() * 4 + 1);
            }
        }
    }
}
