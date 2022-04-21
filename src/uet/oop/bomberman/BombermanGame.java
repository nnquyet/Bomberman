package uet.oop.bomberman;

import com.sun.deploy.net.MessageHeader;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import sun.dc.pr.PRError;
import uet.oop.bomberman.enemy.Balloon;
import uet.oop.bomberman.enemy.Boss;
import uet.oop.bomberman.enemy.Enemy;
import uet.oop.bomberman.enemy.Oneal;
import uet.oop.bomberman.entities.*;
import uet.oop.bomberman.graphics.Sprite;
import uet.oop.bomberman.item.BombItem;
import uet.oop.bomberman.item.FlameItem;
import uet.oop.bomberman.item.Portal;
import uet.oop.bomberman.item.SpeedItem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BombermanGame extends Application {

    public static final int WIDTH = 31;
    public static final int HEIGHT = 13;

    private GraphicsContext gc;
    private GraphicsContext gcEntity;
    private Canvas canvas;
    private Canvas canvasEntity;
    public static String[] map;
    public boolean flameExplode;

    public static CustomKey customKey = new CustomKey();

    public static List<Entity> entities = new ArrayList<>();
    public static List<Entity> backgroundEntitis = new ArrayList<>();
    public static List<Entity> stillObjects = new ArrayList<>();
    public static List<Entity> flames = new ArrayList<>();

    private int level;
    private int rowNum;
    private int colNum;
    Entity bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());

    public static void main(String[] args) {
        Application.launch(BombermanGame.class);
    }

    @Override
    public void start(Stage stage) {
        // Tao Canvas
        canvas = new Canvas(Sprite.SCALED_SIZE * WIDTH, Sprite.SCALED_SIZE * HEIGHT);
        gc = canvas.getGraphicsContext2D();

        // Tao root container
        Group root = new Group();
        root.getChildren().add(canvas);

        // Tao scene
        Scene scene = new Scene(root);

        // Them scene vao stage
        stage.setScene(scene);
        stage.show();

        map = createMap(1);
        entities.add(bomberman);
        SoundEffect.sound(SoundEffect.mediaPlayerbacksound);

        // Nhan key tu ban phim
        scene.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT: {
                    customKey.left = true;
                    break;
                }
                case RIGHT: {
                    customKey.right = true;
                    break;
                }
                case UP: {
                    customKey.up = true;
                    break;
                }
                case DOWN: {
                    customKey.down = true;
                    break;
                }
                case SPACE: {

                    break;
                }
            }
        });
        scene.setOnKeyReleased(keyEvent -> {
            switch (keyEvent.getCode()) {
                case LEFT: {
                    customKey.left = false;
                    break;
                }
                case RIGHT: {
                    customKey.right = false;
                    break;
                }
                case UP: {
                    customKey.up = false;
                    break;
                }
                case DOWN: {
                    customKey.down = false;
                    break;
                }
                case SPACE: {
                    Bomber bomber = (Bomber) bomberman;
                    if (bomber.isCheckLive()) {
                        Entity bomb = bomber.placeBomb();
                        String s = map[(int) bomb.getY()].substring(0, (int) bomb.getX()) + "t" + map[(int) bomb.getY()].substring((int) bomb.getX() + 1);
                        map[(int) bomb.getY()] = s;
                        entities.add(bomb);
                    }
                    break;
                }
            }
        });

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                render();
                update();
            }
        };
        timer.start();

    }

    public String[] createMap(int lv) {
        try {
            FileReader fileReader = new FileReader("res/levels/Level" + lv + ".txt");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            Scanner sc = new Scanner(bufferedReader);

            level = sc.nextInt();
            rowNum = sc.nextInt();
            colNum = sc.nextInt();

            String[] map = new String[rowNum];
            String s = sc.nextLine();

            for (int i = 0; i < rowNum; i++) {
                map[i] = sc.nextLine();
                System.out.println(map[i]);
                for (int j = 0; j < colNum; j++) {
                    Entity entity;
                    char a = map[i].charAt(j);
                    switch (a) {
                        case '#': {
                            backgroundEntitis.add(new Wall(j, i, Sprite.wall.getFxImage()));
                            break;
                        }
                        case '*': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            break;
                        }
                        case 'f': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(new FlameItem(j, i, Sprite.powerup_flames.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            map[i] = map[i].replace('f', '*');
                            break;
                        }
                        case 'b': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(new BombItem(j, i, Sprite.powerup_bombs.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            map[i] = map[i].replace('b', '*');
                            break;
                        }
                        case 's': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(new SpeedItem(j, i, Sprite.powerup_speed.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            map[i] = map[i].replace('s', '*');
                            break;
                        }
                        case 'x': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            stillObjects.add(new Portal(j, i, Sprite.portal.getFxImage()));
                            stillObjects.add(new Brick(j, i, Sprite.brick.getFxImage()));
                            map[i] = map[i].replace('x', '*');
                            break;
                        }
                        case '1': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            entities.add(new Balloon(j, i, Sprite.balloom_left1.getFxImage()));
                            break;
                        }
                        case '2': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            entities.add(new Oneal(j, i, Sprite.oneal_left1.getFxImage()));
                            break;
                        }
                        case '3': {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            entities.add(new Boss(j, i, Sprite.doll_right1.getFxImage()));
                            break;
                        }
                        default: {
                            backgroundEntitis.add(new Grass(j, i, Sprite.grass.getFxImage()));
                            break;
                        }
                    }
                }
            }
            return map;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void update() {
        checkWin();
        updateEntity();
        updateBomb();
        updateFlame();
        checkDamagedFlame();
        checkDamagedBomber();
    }

    public void render() {
        gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        backgroundEntitis.forEach(g -> g.render(gc));
        stillObjects.forEach(g -> g.render(gc));
        entities.forEach(g -> g.render(gc));
        flames.forEach(g -> g.render(gc));
    }

    public void updateBomb() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomb) {
                Bomb bomb = (Bomb) entities.get(i);
                if (bomb.explode()) {
                    entities.remove(i);
                    String s = map[(int) bomb.getY()].substring(0, (int) bomb.getX()) + " " + map[(int) bomb.getY()].substring((int) bomb.getX() + 1);
                    map[(int) bomb.getY()] = s;
                    List<Flame> flame_clone = new ArrayList<>();
                    flame_clone = bomb.getFlameList();
                    for (int j = 0; j < flame_clone.size(); j++) {
                        flames.add(flame_clone.get(j));
                    }
                }
            }
        }
    }

    public void updateFlame() {
        for (int i = 0; i < flames.size(); i++) {
            Flame flame = (Flame) flames.get(i);
            if (flame.explode()) {
                flames.removeAll(flames);
            }
        }
    }

    public void checkDamagedFlame() {
        for (int i = 0; i < flames.size(); i++) {
            int xFlame = (int) flames.get(i).getX();
            int yFlame = (int) flames.get(i).getY();
            for (int j = 0; j < stillObjects.size(); j++) {
                if (stillObjects.get(j) instanceof Brick) {
                    if (stillObjects.get(j).getX() == xFlame
                            && stillObjects.get(j).getY() == yFlame) {
                        String s = map[(int) stillObjects.get(j).getY()].substring(0, (int) stillObjects.get(j).getX()) + " "
                                + map[(int) stillObjects.get(j).getY()].substring((int) stillObjects.get(j).getX() + 1);
                        map[(int) stillObjects.get(j).getY()] = s;
                        stillObjects.remove(j);
                    }
                }
            }
            for (int k = 0; k < entities.size(); k++) {
                if (entities.get(k) instanceof Enemy) {
                    if (entities.get(k).getX() == xFlame
                            && entities.get(k).getY() == yFlame) {
                        entities.remove(k);
                    }
                }
            }
        }
    }

    public void checkDamagedBomber() {
        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i) instanceof Bomber) {
                int xBomber = (int) entities.get(i).getX();
                int yBomber = (int) entities.get(i).getY();
                for (int j = 0; j < flames.size(); j++) {
                    if (flames.get(j).getX() == xBomber
                            && flames.get(j).getY() == yBomber) {
                        Bomber bomber = (Bomber) entities.get(i);
                        bomber.setCheckLive(false);
                        bomber.checkWin();
                        SoundEffect.mediaPlayerbacksound.stop();
                        entities.get(i).setImg(Sprite.player_dead1.getFxImage());
                        SoundEffect.sound(SoundEffect.mediaPlayerCollisionEnemy);
                    }
                }
                for (int k = 0; k < entities.size(); k++) {
                    if (entities.get(k) instanceof Enemy) {
                        if (entities.get(k).getX() == xBomber
                                && entities.get(k).getY() == yBomber) {
                            Bomber bomber = (Bomber) entities.get(i);
                            bomber.setCheckLive(false);
                            bomber.checkWin();
                            SoundEffect.mediaPlayerbacksound.stop();
                            entities.get(i).setImg(Sprite.player_dead1.getFxImage());
                            SoundEffect.sound(SoundEffect.mediaPlayerCollisionEnemy);
                        }
                    }
                }
                for (int m = 0; m < stillObjects.size(); m++) {
                    if (stillObjects.get(m) instanceof BombItem ||
                            stillObjects.get(m) instanceof FlameItem) {
                        if(stillObjects.get(m).getX() == xBomber
                            && stillObjects.get(m).getY() ==yBomber) {
                            stillObjects.remove(m);
                        }
                    }
                    if (stillObjects.get(m) instanceof SpeedItem) {
                        if(stillObjects.get(m).getX() == xBomber
                                && stillObjects.get(m).getY() ==yBomber) {
                            Bomber bomber = (Bomber) entities.get(i);
                            bomber.setSpeed(0.5);
                            stillObjects.remove(m);
                        }
                    }
                }
            }
        }
    }

    public void checkWin() {
        if (entities.size() == 1) {
            for (int i = 0; i < entities.size(); i++) {
                if (entities.get(i) instanceof Bomber) {
                    int xBomber = (int) entities.get(i).getX();
                    int yBomber = (int) entities.get(i).getY();
                    for (int j = 0; j < stillObjects.size(); j++) {
                        if (stillObjects.get(j) instanceof Portal) {
                            if (xBomber == stillObjects.get(j).getX() && yBomber == stillObjects.get(j).getY()) {
                                SoundEffect.sound(SoundEffect.mediaNextMap);
                                checkForNewLevel();
                            }
                        }
                    }
                }
            }
        }
    }

    public void checkForNewLevel() {
        entities.clear();
        backgroundEntitis.clear();
        stillObjects.clear();
        flames.clear();
        map = createMap(2);
        bomberman = new Bomber(1, 1, Sprite.player_right.getFxImage());
        entities.add(bomberman);
        render();
    }

    public void updateEntity() {
        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
            if (entities.get(i) instanceof Boss) {
                ((Boss) entities.get(i)).randomEnemy();
            }
        }
    }
}
