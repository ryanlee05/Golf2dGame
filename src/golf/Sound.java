package golf;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {
	Clip clip;
	URL soundURL[] = new URL[5];

	public Sound() {
		// collision sound!
		soundURL[0] = getClass().getResource("/music/Collision.wav");

		// songtrack
		soundURL[1] = getClass().getResource("/music/BeachVibes.wav");
		
		soundURL[2] = getClass().getResource("/music/golf_hit.wav");
		
		soundURL[3] = getClass().getResource("/music/ClubSwapRight.wav");
		
		soundURL[4] = getClass().getResource("/music/ClubSwapLeft.wav");
	}

	public void setFile(int i) {
		try {
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
		} catch (Exception e) {

		}

	}

	public void play() {
		clip.start();
	}

	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}

	public void stop() {
		clip.stop();
	}

}
