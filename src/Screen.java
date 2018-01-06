import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Screen extends JPanel implements ActionListener{
	Timer tm = new Timer(120, this);
	Board b = new Board();
	boolean a[][] = b.getBoard();
	int size = Board.size;
	int res = 1000/size;
	int iter = 0;
	
	public Screen(){
		repaint();
	}
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		
		//Draws Gridlines
//		for(int x = 0; x < size - 1; x++){
//			g.drawLine((x+1)*res, 0, (x+1)*res, 1000);
//		}	
//		for(int y = 0; y < size - 1; y++){
//			g.drawLine(0, (y+1)*res, 1000, (y+1)*res);
//		}
//		
		g.setFont(new Font("ComicSans", Font.PLAIN, 30)); 
		g.setColor(Color.GREEN);
		g.drawString(Integer.toString(iter), 30, 30);
		g.setColor(Color.BLACK);
		for(int i = 0; i < size; i++){
			for(int j = 0; j < size; j++){
				if(a[i][j]){
					g.fillRect(i*res, j*res, res, res);
				}
			}
		}
		tm.start();
	}
	
	public void actionPerformed(ActionEvent e){
		b.cycle();
		a = b.getBoard();
		iter++;
		repaint();
	}
}
