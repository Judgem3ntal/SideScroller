package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Audio.AudioPlayer;
import TileMap.Background;

public class PauseState extends GameState {
	
	private int x;
	private int y;
	private int dx;
	private int dy;
	private boolean paused;
	
	private Background bg;
	private Color titleColor;
	private Font font;
	private Font titleFont;
	private AudioPlayer bgMusic;

	public PauseState(GameStateManager gsm, boolean paused, int x, int y, int dx, int dy) {
		this.gsm = gsm;
		this.x = x;
		this.y = y;
		this.paused = paused;
		this.dx = dx;
		this.dy = dy;
		
		
		try {
			
			bg = new Background("/Backgrounds/menubg.gif", 1);
			bg.setVector(-0.1, 0);
			
			titleColor = new Color(128, 0, 0);
			titleFont = new Font(
					"Century Gothic",
					Font.PLAIN,
					28);
			
			font = new Font("Arial", Font.PLAIN, 12);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		bg.update();
		
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);
		
	}

	@Override
	public void keyPressed(int k) {
		if(k == KeyEvent.VK_ESCAPE) gsm.setState(GameStateManager.LEVEL1STATE);
		GameStateManager.isPaused = false;
		
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		
	}

}
