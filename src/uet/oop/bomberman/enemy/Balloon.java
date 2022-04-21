package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.enemy.Enemy;

public class Balloon extends Enemy {
    public Balloon(int x, int y, Image img) {
        super(x, y, img);
        speed = 0.025;
        checkDie = false;
    }

    @Override
    public boolean moveLeft() {
        this.img = Sprite.balloom_left1.getFxImage();
        return super.moveLeft();
    }

    @Override
    public boolean moveRight() {
        this.img = Sprite.balloom_right1.getFxImage();
        return super.moveRight();
    }

    @Override
    public boolean moveUp() {
        return super.moveUp();
    }

    public boolean moveDown() {
        return super.moveDown();
    }
}
