import java.awt.Color;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GamePanel panel;
	
	GameFrame()
	{
		panel = new GamePanel();
		this.add(panel);
		this.setBounds(10, 10, 600, 600);
		this.setTitle("Brick Breaker");
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBackground(Color.black);
		this.pack();
		this.setVisible(true);
		
	}
	
}
