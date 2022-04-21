package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.enemy.Enemy;

public class Oneal extends Enemy {
    public Oneal(int x, int y, Image img) {
        super(x, y, img);
        speed = 0.05;
        checkDie = false;
    }

    @Override
    public boolean moveLeft() {
        this.img = Sprite.oneal_left1.getFxImage();
        return super.moveLeft();
    }

    @Override
    public boolean moveRight() {
        this.img = Sprite.oneal_right1.getFxImage();
        return super.moveRight();
    }

    @Override
    public boolean moveUp() {
        return super.moveUp();
    }

    public boolean moveDown() {
        return super.moveDown();
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
                if (!moveUp()) direction = (int) (Math.random() * 4 + 1);
            }
            if (direction == 4) {
                if (!moveDown()) direction = (int) (Math.random() * 4 + 1);
            }
        }
    }
}
