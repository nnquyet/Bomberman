package uet.oop.bomberman.entities;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.BombermanGame;
import uet.oop.bomberman.SoundEffect;
import uet.oop.bomberman.graphics.Sprite;

import java.util.ArrayList;
import java.util.List;

public class Bomb extends Entity {
    public int timeToExplode;
    public int timeExplode = 200;
    public boolean exploded = false;
    public List<Flame> flameList = new ArrayList<>();
    public int animate = 0;
    boolean remove = false;



    public Bomb(double x, double y, Image img) {
        super(x, y, img);
        rectangle = new Rectangle(x, y, 0.7, 0.7);
    }

    @Override
    public void update() {
        if (explode()) {
            setFlame();
        }
    }

    public boolean explode() {
        if (timeExplode != 0) {
            timeExplode -= 1;

            return false;
        } else {
            SoundEffect.mediaPlayerBombExploded.stop();

            SoundEffect.sound(SoundEffect.mediaPlayerBombExploded);
            return true;
        }
    }

    public void setFlame() {
        int _x = (int) x;
        int _y = (int) y;
        flameList.add(new Flame(_x, _y, Sprite.bomb_exploded1.getFxImage()));
        if (BombermanGame.map[_y].charAt(_x - 1) != '#' ) {
            flameList.add(new Flame(_x - 1, _y, Sprite.explosion_horizontal_left_last1.getFxImage()));
        }
        if (BombermanGame.map[_y].charAt(_x + 1) != '#' ) {
            flameList.add(new Flame(_x + 1, _y, Sprite.explosion_horizontal_right_last1.getFxImage()));
        }
        if (BombermanGame.map[_y - 1].charAt(_x) != '#') {
            flameList.add(new Flame(_x, _y - 1, Sprite.explosion_vertical_top_last1.getFxImage()));
        }
        if (BombermanGame.map[_y + 1].charAt(_x) != '#') {
            flameList.add(new Flame(_x, _y + 1, Sprite.explosion_vertical_down_last1.getFxImage()));
        }
    }

    public List<Flame> getFlameList() {
        return flameList;
    }
}
