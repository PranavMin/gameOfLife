import java.awt.GridLayout;
import javax.swing.JFrame;

public class Frame extends JFrame {
	Screen s;
	
	public Frame(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1000, 1035);
		setResizable(false);
		setTitle("Pranav's Game of Life");
		
		init();
	}
	
	public void init(){
		setLocationRelativeTo(null);
		
		setLayout(new GridLayout(1, 1, 0, 0));
		
		s = new Screen();
		add(s);
		
		setVisible(true);
		
	}
}

