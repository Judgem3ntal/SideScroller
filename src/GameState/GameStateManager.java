package GameState;

import java.util.ArrayList;

import Entity.Player;

public class GameStateManager {
	
	private GameState[] gameStates;
	private int currentState;
	
	public static final int NUMGAMESTATES = 6;
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int PAUSESTATE = 2;
	public static final int DEADSTATE = 3;
	public static final int HELPSTATE = 4;
	public static final int LEVEL1ASTATE = 5;
	public static int playerx = 100;
	public static int playery = 100;
	public static int playerdx = 0;
	public static int playerdy = 0;
	public static boolean isPaused;
	
	public GameStateManager() {
		
		gameStates = new GameState[NUMGAMESTATES];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}
	
	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this, isPaused, playerx, playery, playerdx, playerdy);
		if(state == PAUSESTATE) {
			gameStates[state] = new PauseState(this, isPaused, playerx, playery, playerdx, playerdy);
			System.out.println("The value is" + isPaused + " " + playerx + " " +  playery + " " + playerdx + " " + playerdy);
		}
		if(state == DEADSTATE) {
			gameStates[state] = new DeadState(this);
		}
		if(state == HELPSTATE) {
			gameStates[state] = new HelpState(this);
		}
		if(state == LEVEL1ASTATE) {
			gameStates[state] = new Level1AState(this, true, 3058, playery, playerdx, playerdy);
		}

	}
	
	public void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
		//gameStates[currentState].init();
	}
	
	public void update() {
		try {
			gameStates[currentState].update();
		} catch(Exception e) {}
	}
	
	public void draw(java.awt.Graphics2D g) {
		try {
			gameStates[currentState].draw(g);
		} catch(Exception e) {}
	}
	
	public void keyPressed(int k) {
		gameStates[currentState].keyPressed(k);
	}
	
	public void keyReleased(int k) {
		gameStates[currentState].keyReleased(k);
	}
	public int getState() {
		return currentState;
	}
	
}









