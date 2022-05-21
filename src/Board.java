import java.awt.Color;
import java.awt.Graphics;

public class Board {
	Tile[][] board = new Tile[9][9];
	boolean clicked = false;
	boolean gameOver = false;
	boolean win = false;
	int safeSpaces;


	public Board(){
		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++){
				board[x][y] = new Tile(x, y);
				clicked = false;
				safeSpaces++;
			}
		}
	}
	public void placeMines(int clickX, int clickY){
		System.out.println(clickX + ", " + clickY);
		if ( !board[clickX][clickY].flagged() ) {
			
			if (clicked == false){
				int mineCount = 0;
				int x = 0;
				int y = 0;
				while(mineCount < 10){
					x = (int) (Math.random() * 9);
					y = (int) (Math.random() * 9);
					if( (x != clickX || y != clickY) && board[x][y].isMine() == false && (clickX - x > 1 || x - clickX > 1 && clickY - y > 1 || y - clickY > 1) ){
						board[x][y].placeMine();
						mineCount++;
						safeSpaces--;
					}
				}
				clicked = true;
				print();

			}
			
		}


	}
	private int count(int x, int y) {
		int neighbors = 0;

		if( (x-1)>-1 && (y-1)>-1 && board[x-1][y-1].isMine() ){
			neighbors += 1;
		}
		if( (y-1)>-1 && board[x][y-1].isMine() ){
			neighbors += 1;
		}
		if( (x+1)<board.length && (y-1)>-1 && board[x+1][y-1].isMine() ){
			neighbors += 1;
		}
		if( (x-1)>-1 && board[x-1][y].isMine() ){
			neighbors += 1;
		}
//		if( board[x][y].isMine() ){
//			neighbors += 1;
//		}
		if( (x+1)<board.length && board[x+1][y].isMine() ){
			neighbors += 1;
		}
		if( (x-1)>-1 && (y+1)<board.length && board[x-1][y+1].isMine() ){
			neighbors += 1;
		}
		if( (y+1)<board.length && board[x][y+1].isMine() ){
			neighbors += 1;
		}
		if( (x+1)<board.length && (y+1)<board.length && board[x+1][y+1].isMine() ){
			neighbors += 1;
		}
		return neighbors;
	}
	public void flag(int x, int y) {
		board[x][y].flagTile();
	}
	
	public void checkMines(int clickX, int clickY) {
		int neighbors;
		if( !board[clickX][clickY].flagged() ){
			
			board[clickX][clickY].click();
			if (clicked == true && gameOver == false) {
				if (board[clickX][clickY].isMine() == false) {
					safeSpaces--;
					System.out.println("Safe");
					neighbors = count(clickX, clickY);
					board[clickX][clickY].countNeighbors(neighbors);
					reveal(clickX,clickY);
					print();
				}
				else {
					System.out.println("You Lose");
					gameOver = true;
				}
			}
			if(safeSpaces <= 0) {
				win();
			}
			
		}
		
	}
	
	private void win() {
		System.out.println("You Win!");
		
		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++){
				board[x][y].win();
			}
		}
		
	}
	
private void reveal(int clickX, int clickY) {
	int neighbors = board[clickX][clickY].numMinesAround();
		if (neighbors == 0) {
			if(clickX > 0 && clickY > 0) {
				if( !board[clickX - 1][clickY - 1].beenClicked() )
				checkMines(clickX - 1, clickY -1);
			}
			if (clickY > 0) {
				if( !board[clickX][clickY - 1].beenClicked() )
				checkMines(clickX, clickY -1);
			}
			if (clickX + 1 < board.length && clickY > 0) {
				if( !board[clickX + 1][clickY - 1].beenClicked() )
				checkMines(clickX + 1, clickY -1);
			}
			if (clickX > 0) {
				if( !board[clickX - 1][clickY].beenClicked() )
				checkMines(clickX - 1, clickY);
			}
			if (clickX + 1 < board.length) {
				if( !board[clickX + 1][clickY].beenClicked() )
				checkMines(clickX + 1, clickY);
			}
			if(clickX > 0 && clickY + 1 < board[0].length) {
				if( !board[clickX - 1][clickY + 1].beenClicked() )
				checkMines(clickX - 1, clickY + 1);
			}
			if (clickY + 1< board[0].length) {
				if ( !board[clickX][clickY + 1].beenClicked() )
				checkMines(clickX, clickY + 1);
			}
			if (clickX + 1 < board.length) {
				if( clickY + 1 < board[0].length && !board[clickX + 1][clickY + 1].beenClicked() )
				checkMines(clickX + 1, clickY + 1);
			}
		}
		else {
			return;
		}
		
	
	
}

	public void print(){
//		for (int x = 0; x < 9; x++){
//			System.out.println("");
//			for (int y = 0; y < 9; y++){
//				if(board[x][y].isMine() == true){
//					System.out.print("B");
//				}
//				else if(board[x][y].beenClicked() == true) {
//					System.out.print( board[x][y].numMinesAround() );
//				}
//				else{
//					System.out.print("*");
//				}
//			}
//		}
		System.out.print(" " + safeSpaces + " ");
	}

	public void draw(Graphics g) {
		//System.out.print("BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB");
		if(gameOver) {

			for (int x = 0; x < 9; x++){
				for (int y = 0; y < 9; y++){
					board[x][y].gameOver();
				}
			}

		}

		for (int x = 0; x < 9; x++){
			for (int y = 0; y < 9; y++){
				board[x][y].draw(g);
			}
		}
	}


}
