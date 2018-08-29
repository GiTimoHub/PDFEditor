package core;

import javax.swing.SwingUtilities;

import gui.MainFrame;

public class Main {
	public static void main(String [] args) {
		App a = new App();
		MainFrame f = new MainFrame(a);
		f.setVisible(true);
	}
}
