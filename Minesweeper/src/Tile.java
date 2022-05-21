import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Tile {
	private boolean isAMine, beenClicked, flagged;
	private int numMinesAround;
	private int Xloc;
	private int Yloc;
	private int side = 50;
	private boolean gameOver = false;
	private boolean won = false;

	public Tile(int x, int y){
		isAMine = false;
		beenClicked = false;
		flagged = false;
		Xloc = x * side;
		Yloc = y * side;
		numMinesAround = 0;
	}

	public void draw(Graphics g) {

		g.setColor(Color.lightGray);
		g.fillRect(Xloc, Yloc, side, side);
		g.setColor(Color.black);
		g.drawRect(Xloc, Yloc, side, side);
		if(won) {
			int R = (int) (Math.random( )*155 + 100);
			int G = (int)(Math.random( )*155 + 100);
			int B= (int)(Math.random( )*155 + 100);
			Color randomColor = new Color(R, G, B);
			g.setColor(randomColor);
			g.fillRect(Xloc, Yloc, side, side);
			g.setColor(Color.black);
			g.drawRect(Xloc, Yloc, side, side);
			Font font = new Font("Serif", Font.PLAIN, 100);
			g.setFont(font);
			g.setColor(Color.WHITE);
			g.drawString("You Win", 50, 250);
			g.setColor(randomColor);
		}
		if(flagged) {
			Font font = new Font("Serif", Font.PLAIN, 30);
			g.setFont(font);
			g.drawString("F", Xloc + 20, Yloc + 35);
		}
//						if(isAMine) { //for testing
//							g.setColor(Color.black);
//							g.fillRect(Xloc, Yloc, side, side);
//						}


		if (! won && gameOver) {
			//			g.setColor(Color.red);
			//			g.fillRect(Xloc, Yloc, side, side);

			g.setColor(Color.red);
			g.drawRect(Xloc, Yloc, side, side);

			if (beenClicked) {
				//System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
				String n = "";
				n += numMinesAround;

				g.setColor(Color.white);
				g.fillRect(Xloc, Yloc, side, side);

				g.setColor(Color.red);
				g.drawRect(Xloc, Yloc, side, side);
				Font font = new Font("Serif", Font.PLAIN, 30);
				g.setFont(font);
				g.setColor(Color.black);
				if(numMinesAround > 0) {
					g.drawString(n, Xloc + 20, Yloc + 35);
				}

			}

			if(isAMine) {
				g.setColor(Color.black);
				g.fillRect(Xloc, Yloc, side, side);
				g.setColor(Color.red);
				g.drawRect(Xloc, Yloc, side, side);


				//			    Font words = new Font("Serif", Font.PLAIN, 5);
				//			    g.setFont(words);
				//			    g.drawString("you degenerate", 1, 495);
			}
			if (flagged) {
				Font font = new Font("Serif", Font.PLAIN, 30);
				g.setColor(Color.white);
				if(isAMine) {
					g.setFont(font);
					g.drawString("O", Xloc + 20, Yloc + 35);
				}
				else {

					g.setColor(Color.lightGray);
					g.fillRect(Xloc, Yloc, side, side);

					g.setColor(Color.red);
					g.drawRect(Xloc, Yloc, side, side);


					g.setColor(Color.black);
					g.setFont(font);
					g.drawString("X", Xloc + 20, Yloc + 35);
				}
			}
			//			g.setColor(Color.black);
			//			Font font = new Font("Serif", Font.PLAIN, 110);
			//			g.setFont(font);
			//			g.drawString("You Lose", 45, 245);

			g.setColor(Color.red);
			Font font = new Font("Serif", Font.PLAIN, 100);
			g.setFont(font);
			g.drawString("You Lose", 50, 250);
		}

		else if (beenClicked) {
			//System.out.print("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
			String n = "";
			n += numMinesAround;

			g.setColor(Color.white);
			if(won) {
				int R = (int) (Math.random( )*155 + 100);
				int G = (int)(Math.random( )*155 + 100);
				int B= (int)(Math.random( )*155 + 100);
				Color randomColor = new Color(R, G, B);
				g.setColor(randomColor);
			}
			g.fillRect(Xloc, Yloc, side, side);

			g.setColor(Color.black);
			g.drawRect(Xloc, Yloc, side, side);
			if(numMinesAround > 0) {

				Font font = new Font("Serif", Font.PLAIN, 30);
				g.setFont(font);
				g.drawString(n, Xloc + 20, Yloc + 35);

			}

		}
	}
	public void gameOver() {
		gameOver = true;
	}

	public int side(){
		return side;
	}
	public void countNeighbors (int x){
		numMinesAround = x;
	}
	public int numMinesAround(){
		return numMinesAround;
	}
	public void placeMine(){
		isAMine = true;
	}
	public boolean isMine() {
		return isAMine;
	}

	public boolean beenClicked() {
		return beenClicked;
	}

	public boolean flagged() {
		return flagged;
	}
	public void flagTile(){
		if(!beenClicked) {

			if(!flagged) {
				flagged = true;
			}
			else {
				flagged = false;
			}

		}
	}

	public void click(){
		beenClicked = true;
	}
	public void win() {
		won = true;
	}
}
