package uet.oop.bomberman.entities;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;

public class Brick extends Entity {
    public Brick(int x, int y, Image img) {
        super(x, y, img);
        rectangle = new Rectangle(x, y, 1, 1);
    }


    @Override
    public void update() {

    }
}
