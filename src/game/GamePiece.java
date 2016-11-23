package game;

import java.awt.Image;

import javax.swing.ImageIcon;

public class GamePiece {

	//Bild für Vorfürspielfigur
	private Image img;

	public GamePiece(int x, int y) {
		ImageIcon i = new ImageIcon(
				"C:/Users/Krystian/Desktop/Schwarzer_bauer_schach.png");
		img = i.getImage();
	}

	//Getter für Bild
	public Image getImage() {
		return img;
	}
}
