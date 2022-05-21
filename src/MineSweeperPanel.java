import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MineSweeperPanel extends JPanel {

	private final int SIZE_PANEL = 451;
	private JFrame frame = new JFrame("Minesweeper!");
	private Board board = new Board();
//	private JPanel panel = new JPanel();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MineSweeperPanel().start();
		
	}
	private void start() {
		// TODO Auto-generated method stub

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add( new MineSweeperPanel() );
		frame.pack();
		frame.setVisible(true);
//		panel.setPreferredSize( new Dimension(501,501) );
//		frame.add(panel);
//		frame.pack();
	}
	public MineSweeperPanel() {
		this.setPreferredSize(new Dimension(this.SIZE_PANEL,SIZE_PANEL));
		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				//System.out.print("?");
				
			}

			@Override
			public void mouseEntered(MouseEvent arg0) {
				//System.out.println("You just entered!! "+arg0);
				
			}

			@Override
			public void mouseExited(MouseEvent arg0) {
				//System.out.println("You just exited!! "+arg0);
				
			}

			@Override
			public void mousePressed(MouseEvent arg0) {
				// write your clicking code here!!
				//System.out.println("You just clicked: "+arg0);
				//int side = Tile.side();
				int x = arg0.getX();
				int y = arg0.getY();
				int button = arg0.getButton();
				//System.out.println(button);
				if(button == 1) {
					board.placeMines(x/50,y/50);
				board.checkMines(x/50,y/50); 	
				}
				else {
					board.flag(x/50, y/50);
				}
				
				repaint();
			}

			@Override
			public void mouseReleased(MouseEvent arg0) {
				//System.out.print("released");

			}
			
		});
	}
	
	public void paintComponent(Graphics g) {
		System.out.print("!");
		board.draw(g);
	}
	
	

}
