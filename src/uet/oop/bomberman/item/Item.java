package uet.oop.bomberman.item;

import javafx.scene.image.Image;
import javafx.scene.shape.Rectangle;
import uet.oop.bomberman.entities.Entity;

public abstract class Item extends Entity {

    public Item(double x, double y, Image img) {
        super(x, y, img);
        rectangle = new Rectangle(x, y, 1, 1);
    }




}
