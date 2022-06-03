package uet.oop.bomberman.sound;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sound {
    public static void play(String filename)
    {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filename)));
            clip.start();
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }
    public static void startGame() {
        try
        {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File("res/sound/main.wav")));
            clip.start();
            clip.loop(clip.LOOP_CONTINUOUSLY);
        }
        catch (Exception exc)
        {
            exc.printStackTrace(System.out);
        }
    }

}
