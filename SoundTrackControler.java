import javax.sound.sampled.*;
import java.io.File;

public class SoundTrackControler extends Thread {
    private String path;

    public SoundTrackControler(String path) {
        this.path = path;
    }

    @Override
    public void run() {
        playBackgroundMusic(path);
    }

    private void playBackgroundMusic(String path) {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(new File(path));
            clip.open(inputStream);
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

