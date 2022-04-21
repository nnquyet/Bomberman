package uet.oop.bomberman.enemy;

import javafx.scene.image.Image;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.graphics.Sprite;

public class Boss extends Enemy {
    private int numEnemyRandom = 0;

    public Boss(int x, int y, Image img) {
        super(x, y, img);
        speed = 0.025;
    }

    @Override
    public boolean moveLeft() {
        this.img = Sprite.doll_left1.getFxImage();
        return super.moveLeft();
    }

    @Override
    public boolean moveRight() {
        this.img = Sprite.doll_right1.getFxImage();
        return super.moveRight();
    }

    @Override
    public boolean moveUp() {
        return super.moveUp();
    }

    @Override
    public boolean moveDown() {
        return super.moveDown();
    }

    public void randomEnemy() {
        if (Math.random() < 0.001 && numEnemyRandom < 2) {
            BombermanGame.entities.add(new Kondoria((int) this.getX(), (int) this.getY(), Sprite.kondoria_left1.getFxImage()));
            numEnemyRandom++;
        }
    }
}
