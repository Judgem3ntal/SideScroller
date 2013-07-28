package Audio;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URL;

import javax.sound.sampled.*;

public class AudioPlayer implements Runnable {
	
	private Clip clip;
	
	public FloatControl gainControl = null;
	
	public AudioPlayer(String s) {
		
		try {
			InputStream audioSrc2 = getClass().getResourceAsStream(s);
			InputStream bufferedIn2 = new BufferedInputStream(audioSrc2);
			AudioInputStream ais = AudioSystem.getAudioInputStream(bufferedIn2);
			AudioFormat baseFormat = ais.getFormat();
			AudioFormat decodeFormat = new AudioFormat(
				AudioFormat.Encoding.PCM_SIGNED,
				baseFormat.getSampleRate(),
				16,
				baseFormat.getChannels(),
				baseFormat.getChannels() * 2,
				baseFormat.getSampleRate(),
				false
			);
			InputStream audioSrc = getClass().getResourceAsStream(s);
			InputStream bufferedIn = new BufferedInputStream(audioSrc);
			AudioInputStream dais = AudioSystem.getAudioInputStream(bufferedIn);
			clip = AudioSystem.getClip();
			clip.open(dais);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void play() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
	}
	public void playBGM() {
		if(clip == null) return;
		stop();
		clip.setFramePosition(0);
		clip.start();
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	public void stop() {
		if(clip.isRunning()) clip.stop();
	}
	
	public void close() {
		stop();
		clip.close();
	}
	public void setVolume(double value) {
	    // value is between 0 and 1
	        float dB = (float)(Math.log(value)/Math.log(10.0)*20.0);
	        gainControl.setValue(dB);
	        System.out.println(gainControl);

	}
	
	Float currDB = 0F;
	float targetDB = 0F;
	float fadePerStep = .1F;   // .1 works for applets, 1 is okay for apps
	boolean fading = false;

	public void shiftVolumeTo(double value) {
	    // value is between 0 and 1
	    value = (value<=0.0)? 0.0001 : ((value>1.0)? 1.0 : value);
	    targetDB = (float)(Math.log(value)/Math.log(10.0)*20.0);
	    if (!fading) {
	        Thread t = new Thread(this);  // start a thread to fade volume
	        t.start();  // calls run() below
	    }
	}

	public void run()
	{
	    fading = true;   // prevent running twice on same sound
	    if (currDB > targetDB) {
	        while (currDB > targetDB) {
	            currDB -= fadePerStep;
	            gainControl.setValue(currDB);
	            try {Thread.sleep(10);} catch (Exception e) {}
	        }
	    }
	    else if (currDB < targetDB) {
	        while (currDB < targetDB) {
	            currDB += fadePerStep;
	            gainControl.setValue(currDB);
	            try {Thread.sleep(10);} catch (Exception e) {}
	        }
	    }
	    fading = false;
	    currDB = targetDB;  // now sound is at this volume level
	}
	
}














