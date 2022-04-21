package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.SoundEffect;

public class Flame extends Entity {
    private int timeExplode = 50;

    public Flame(double x, double y, Image img) {
        super(x, y, img);
        rectangle = new Rectangle(x, y, 1, 1);
    }

    public boolean explode() {
        if (timeExplode != 0) {
            timeExplode -= 1;
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void update() {

    }
}
