package uet.oop.bomberman.entities;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.CustomKey;
import uet.oop.bomberman.graphics.Sprite;

import java.nio.channels.spi.SelectorProvider;
import java.util.ArrayList;
import java.util.List;

public class Bomber extends Entity {
    private double speed = 0.25;
    private boolean checkLive = true;
    private List<Bomb> bombList = new ArrayList<>();
    CustomKey customKey = BombermanGame.customKey;

    public Bomber(double x, double y, Image img) {
        super( x, y, img);
        rectangle = new Rectangle(x, y, 0.6, 0.6);
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public boolean moveLeft() {
        if (x - Math.floor(x) == 0) {
            if (BombermanGame.map[(int) y].charAt((int) x - 1) != '#'
                    && BombermanGame.map[(int) y].charAt((int) x - 1) != '*') {
                x = (double) Math.round((x - speed) * 1000) / 1000;
                setLocation(x, y);
                setImg(Sprite.player_left.getFxImage());
                return true;
            }
            return false;
        } else {
            x = (double) Math.round((x - speed) * 1000) / 1000;
            setLocation(x, y);
            setImg(Sprite.player_left.getFxImage());
            return true;
        }
    }

    public boolean moveRight() {
        if (x - Math.floor(x) == 0) {
            if (BombermanGame.map[(int) y].charAt((int) x + 1) != '#'
                    && BombermanGame.map[(int) y].charAt((int) x + 1) != '*') {
                x = (double) Math.round((x + speed) * 1000) / 1000;
                setLocation(x, y);
                setImg(Sprite.player_right.getFxImage());
                return true;
            }
            return false;
        } else {
            x = (double) Math.round((x + speed) * 1000) / 1000;
            setLocation(x, y);
            setImg(Sprite.player_right.getFxImage());
            return true;
        }
    }

    public boolean moveUp() {
        if (y - Math.floor(y) == 0) {
            if (BombermanGame.map[(int) y - 1].charAt((int) x) != '#'
                    && BombermanGame.map[(int) y - 1].charAt((int) x) != '*') {
                y = (double) Math.round((y - speed) * 1000) / 1000;
                setLocation(x, y);
                setImg(Sprite.player_up.getFxImage());
                return true;
            }
            return false;
        } else {
            y = (double) Math.round((y - speed) * 1000) / 1000;
            setLocation(x, y);
            setImg(Sprite.player_up.getFxImage());
            return true;
        }
    }

    public boolean moveDown() {
        if (y - Math.floor(y) == 0) {
            if (BombermanGame.map[(int) y + 1].charAt((int) x) != '#'
                    && BombermanGame.map[(int) y + 1].charAt((int) x) != '*') {
                y = (double) Math.round((y + speed) * 1000) / 1000;
                setLocation(x, y);
                setImg(Sprite.player_down.getFxImage());
                return true;
            }
            return false;
        } else {
            y = (double) Math.round((y + speed) * 1000) / 1000;
            setLocation(x, y);
            setImg(Sprite.player_down.getFxImage());
            return true;
        }
    }

    public Entity placeBomb() {
        Entity bomb = new Bomb(Math.floor(this.getX()), Math.floor(this.getY()), Sprite.bomb.getFxImage());
        return bomb;
    }

    @Override
    public void update() {
        if (customKey.left && checkLive) {
            moveLeft();
            customKey.left = false;
        } else if (customKey.right && checkLive) {
            moveRight();
            customKey.right = false;
        } else if (customKey.up && checkLive) {
            moveUp();
            customKey.up = false;
        } else if (customKey.down && checkLive) {
            moveDown();
            customKey.down = false;
        }
    }

    public void checkWin() {
        if (!checkLive) {
            speed = 0;
        }
    }

    public boolean isCheckLive() {
        return checkLive;
    }

    public void setCheckLive(boolean checkLive) {
        this.checkLive = checkLive;
    }
}
