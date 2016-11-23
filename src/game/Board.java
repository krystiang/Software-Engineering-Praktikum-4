package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements MouseListener, ActionListener {

	private static final long serialVersionUID = 8155140574736109939L;
	
	//Größe des Boards in beide Richtungen (z.b. 5x5)
	private int fieldCount;
	//Liste mit den Spielfeldern
	private List<GameField> gameFields = new ArrayList<GameField>();
	// Timer für die Akktualisierungsrate
	private Timer time;
	// zuletzt angewähltes Spielfeld (für mouseClicked und mouseReleased)
	private GameField lastClickedGameField;
	private List<GameField> reachableGameFields;
	private boolean enemyTurn;
	private ObjectInputStream in;
	private ObjectOutputStream out;
	
	public Board(int fieldCount, ObjectInputStream in, ObjectOutputStream out, DataOutputStream dout, DataInputStream din) throws IOException {
		boolean test = din.readBoolean();
		this.in = in;
		this.out = out;
		addMouseListener(this);
		this.fieldCount = fieldCount;
		this.time = new Timer(200, this);
		//Starten des Timers
		time.start();
		//Erzeugen der Spielfelder und hinzufügen zur gameFields Liste
		setGameFields();
		//Setzen des Spielsteins auf ein Feld (als Beispiel)
		setGamePieces();
	}

	//Setzen eines Spielsteins für Beispielzwecke
	public void setGamePieces() {
		gameFields.get(7).setPiece(
				new GamePiece(gameFields.get(7).getX(), gameFields.get(7)
						.getY()));
	}

	//Erzeugen der Spielfelder
	public void setGameFields() {
		int höhe = 50;
		int kante = 50;
		int mx = kante;
		int my = höhe;
		int flag = 0;
		for (int j = 0; j < fieldCount; j++) {
			for (int i = 0; i < fieldCount; i++) {
				gameFields.add(new GameField(mx, my));

				my += höhe * 2;
			}
			if (flag == 0) {
				my = 2 * höhe;
				flag = 1;
			} else {
				my = höhe;
				flag = 0;
			}
			mx += (int) (kante * 1.5);
		}
	}

	//Zeichnenmethode
	public void paint(Graphics g) {
	if(enemyTurn){
		input
		// Figur suchen und ersetzen
		
	}else{ // wir sind drann
		
	}

		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		int höhe = 48;
		int kante = 48;
		//Zeichnenmethode zeichnet für die gameField Liste alle Hexagone ausgehend von ihrem Mittelpunkt
		for (GameField gf : gameFields) {
			int ax = (gf.getX() + kante); // rechter Punkt
			int ay = (gf.getY());
			int bx = (int) (gf.getX() + (kante * 0.5)); // unterer rechter Punkt
			int by = (gf.getY() + höhe);
			int cx = (int) (gf.getX() - (kante * 0.5)); // unterer linker Punkt
			int cy = (gf.getY() + höhe);
			int dx = (gf.getX() - kante); // linker Punkt
			int dy = (gf.getY());
			int ex = (int) (gf.getX() - (kante * 0.5)); // oberer linker Punkt
			int ey = (gf.getY() - höhe);
			int fx = (int) (gf.getX() + (kante * 0.5)); // oberer rechter Punkt
			int fy = (gf.getY() - höhe);

			Polygon polygon = new Polygon();

			polygon.addPoint(ax, ay);
			polygon.addPoint(bx, by);
			polygon.addPoint(cx, cy);
			polygon.addPoint(dx, dy);
			polygon.addPoint(ex, ey);
			polygon.addPoint(fx, fy);

			g2.setColor(Color.RED);
			g2.fillPolygon(polygon);
		
		}

		//Durchgehen der Spielfelder und prüfen wo ein Spielstein momentan drauf steht => Diesen Spielstein an dem jeweiligen Ort zeichnen
		for (GameField gf : gameFields) {
			if (gf.getPiece() != null) {
				// Zeichnen des Spielstein Bildes
				// g2.drawImage(gf.getPiece().getImage(), gf.getX()-20,
				// gf.getY()-20, null);
				//Zeichnen eines schwarzen Vierecks als Ersatz für Bild
				g2.setColor(Color.BLACK);
				g2.fillRect(gf.getX() - 20, gf.getY() - 20, 40, 40);
				g2.drawRect(gf.getX() - 20, gf.getY() - 20, 40, 40);
			} else {
				g2.setColor(Color.WHITE);
				g2.fillRect(gf.getX() - 20, gf.getY() - 20, 40, 40);
				g2.drawRect(gf.getX() - 20, gf.getY() - 20, 40, 40);
			}
		}

	}

	public void mousePressed(MouseEvent event) {
		if(!enemyTurn){

		}
		prüfenächtesfeld(event);
		reachableGameFields =getReachableGamefields(lastClickedGameField.getPiece().getReach());
		setHighlightedgameFields(reachableGameFields);
		
	}
	
	public List<GameField> getReachableGamefields(int reach){
		
		
		return gameFields;
		
	}

	public void prüfenächtesfeld(MouseEvent event){
		// Prüfen welches Spielfeld am nächesten an der Maus war als geklickt wurde
		GameField nearestGameField = gameFields.get(0);
		for (GameField gf : gameFields) {
			// Satz des Pythagoras
			if ((Math.sqrt(Math.pow((gf.getX() - event.getX()), 2)
					+ Math.pow((gf.getY() - event.getY()), 2))) < (Math
					.sqrt(Math.pow((nearestGameField.getX() - event.getX()), 2)
							+ Math.pow(
									(nearestGameField.getY() - event.getY()), 2)))) {
				nearestGameField = gf;
			}
		}
		// Das nächeste Spielfeld vom Mausklick in lastClickedGameField speichern
		lastClickedGameField = nearestGameField;
		}

	
	public void mouseReleased(MouseEvent event) {
		if(!enemyTurn){

		}
		unsetHighlightedgameFields(reachableGameFields);
		prüfenächtesfeld(event);
		wenn lastClickedGameField in reachableGameFields
		
		// Übertragen des Spielsteins vom Spielfeld auf das geklickt wurde zum Spielfeld auf dem die Maus wieder losgelassen wurde <---- VERÄNDERUNG das müsste beim Anderen Spieler auch passieren
		nearestGameField.setPiece(lastClickedGameField.getPiece());
		if (lastClickedGameField != nearestGameField) {
			// Löschen des Spielsteins vom Spielfeld auf das geklickt wurde (da es ja verschoben wurde) <---- VERÄNDERUNG das müsste beim Anderen Spieler auch passieren
			lastClickedGameField.setPiece(null);
		}
	}
	}

	// nach der Akktualisierungsrate vom Timer wird immer wieder Repaint aufgerufen
	@Override
	public void actionPerformed(ActionEvent e) {
		this.repaint();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
