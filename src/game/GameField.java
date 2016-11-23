package game;

public class GameField {

	// umgebende Spielfelder (für Vorfürversion noch nicht nötig)
	// private GameField upperRight;
	// private GameField lowerRight;
	// private GameField lower;
	// private GameField lowerLeft;
	// private GameField upperLeft;
	// private GameField up;

	// Koordinaten des Spielfeldes
	private int x;
	private int y;

	// Auf dem Spielfeld liegende Spielfigur (null falls keine darauf liegt)
	private GamePiece gamePiece;

	// Konstruktor
	public GameField(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Getter und Setter für die Koordinaten
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setPiece(GamePiece gp) {
		gamePiece = gp;
	}

	public GamePiece getPiece() {
		return gamePiece;
	}
}
