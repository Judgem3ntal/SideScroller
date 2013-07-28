package GameState;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.AudioPlayer;
import Main.GamePanel;
import TileMap.Background;

public class DeadState extends GameState{
	
	private Background bg;
	
	private AudioPlayer bgMusic;
	
	public DeadState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			
			bg = new Background("/Backgrounds/dead.png", 0);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
		bgMusic = new AudioPlayer("/Music/level1-1.wav");
		bgMusic.stop();
	}

	public void update() {
		bg.update();
	}

	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		bg.draw(g);
	}

	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ENTER) {
			gsm.setState(GameStateManager.MENUSTATE);
		}
	}

	public void keyReleased(int k) {
		
	}

}
