package game;

import java.awt.Color;

import javax.swing.*;

class Frame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2067297857235905585L;

	// Frame wird mit seinen Eigenschaften erstellt und Board angefügt.
	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(405, 600);
		f.setBackground(Color.WHITE);
		Board board = new Board(5);
		f.add(board);
		f.setVisible(true);
	}
}
