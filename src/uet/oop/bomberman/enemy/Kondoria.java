package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.enemy.Enemy;

public class Kondoria extends Enemy {
    public Kondoria(int x, int y, Image img) {
        super(x, y, img);
        speed = 0.1;
        checkDie = false;
    }

    @Override
    public boolean moveLeft() {
        this.img = Sprite.kondoria_left1.getFxImage();
        if (x - Math.floor(x) == 0) {
            if (BombermanGame.map[(int) y].charAt((int) x - 1) != '#'
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

    @Override
    public boolean moveRight() {
        this.img = Sprite.kondoria_right1.getFxImage();
        if (x - Math.floor(x) == 0) {
            if (BombermanGame.map[(int) y].charAt((int) x + 1) != '#'
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

    @Override
    public boolean moveUp() {
        if (y - Math.floor(y) == 0) {
            if (BombermanGame.map[(int) y - 1].charAt((int) x) != '#'
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

    @Override
    public boolean moveDown() {
        if (y - Math.floor(y) == 0) {
            if (BombermanGame.map[(int) y + 1].charAt((int) x) != '#'
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

    public void update() {
        if (checkDie) {
            this.img = null;
        } else {
            if (direction == 1) {
                if (!moveLeft()) {
                    direction = (int) (Math.random() * 4 + 1);
                    if (Math.random() <= 0.5) {
                        speed = 0.05;
                    } else {
                        speed = 0.1;
                    }
                }
            }
            if (direction == 2) {
                if (!moveRight()) direction = (int) (Math.random() * 4 + 1);
            }
            if (direction == 3) {
                if (!moveUp()) {
                    direction = (int) (Math.random() * 4 + 1);
                    if (Math.random() <= 0.5) {
                        speed = 0.05;
                    } else {
                        speed = 0.1;
                    }
                }
            }
            if (direction == 4) {
                if (!moveDown()) direction = (int) (Math.random() * 4 + 1);
            }
        }
    }
}
