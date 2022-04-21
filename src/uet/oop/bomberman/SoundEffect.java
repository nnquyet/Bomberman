package uet.oop.bomberman;


import java.io.File;

import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class SoundEffect extends Application{
    //backsoundsrc\Sound\backSound.mp3

    public static Media mediaBackSound = new Media(new File("src\\Sound\\backgroundSound.mp3")
            .toURI().toString());
    public static MediaPlayer mediaPlayerbacksound = new MediaPlayer(mediaBackSound);

    //NextMap
    public static Media mediaLoadMap = new Media(new File("src\\Sound\\LoadMap.wav")
            .toURI().toString());
    public static MediaPlayer mediaNextMap = new MediaPlayer(mediaLoadMap);

    //Die
    public static Media mediaCollisionEnemy = new Media(new File("src\\Sound\\collision.wav")
            .toURI().toString());
    public static MediaPlayer mediaPlayerCollisionEnemy = new MediaPlayer(mediaCollisionEnemy);

    //BombExploded
    public static Media mediaBombExploded = new Media(new File("src\\Sound\\eatItem.wav")
            .toURI().toString());
    public static MediaPlayer mediaPlayerBombExploded = new MediaPlayer(mediaBombExploded);


    public static void sound(MediaPlayer mp) {
        mp.play();
        //    System.out.println("play");
    }

    @Override
    public void start(Stage arg0) throws Exception {
        //sound();
    }
}
