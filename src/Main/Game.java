package Main;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JFrame;

public class Game {
	
	final static JFrame window = new JFrame("TAOKTD");
	static GraphicsDevice vc;
	
	public static void main(String[] args) {
		
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setVisible(true);
	}
	
	public static void fullScreen() {
		
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = ge.getDefaultScreenDevice();
		
		window.setExtendedState(window.MAXIMIZED_BOTH);
		vc.setFullScreenWindow(window);
	}
	
}
