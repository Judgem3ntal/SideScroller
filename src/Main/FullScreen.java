package Main;

import java.awt.*;

import javax.swing.*;

public class FullScreen {
	
	GraphicsDevice vc;
	
	public FullScreen() {
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		vc = ge.getDefaultScreenDevice();
	}
	
	public void setFullScreen(DisplayMode dm, JFrame win) {
		win.dispose();
		win.setUndecorated(true);
		
		win.setResizable(false);
		
		vc.setFullScreenWindow(win);
		
		if(dm != null && vc.isDisplayChangeSupported()) {
			try {
				vc.setDisplayMode(dm);
				
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, ex.getMessage());
			}
		}
	}
	
	public void CloseFullScreen() {
		Window w = vc.getFullScreenWindow();
		if(w!=null) {
			w.dispose();
		}
		vc.setFullScreenWindow(null);
	}

}
