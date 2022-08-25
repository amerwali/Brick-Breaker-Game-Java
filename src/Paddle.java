import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public class Paddle extends Rectangle{

	
	
	int xVelocity;
	int speed = 10;
	int id;

	Paddle(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT){
		super(x,y,PADDLE_WIDTH,PADDLE_HEIGHT);
		id = 1;
	}

	public void draw(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(x, y, width, height);
	}
	
	
	public void keyPressed(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(-speed);
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(speed);
			}
			break;
		}
	}
	public void keyReleased(KeyEvent e) {
		switch(id) {
		case 1:
			if(e.getKeyCode()==KeyEvent.VK_A) {
				setXDirection(0);
			}
			if(e.getKeyCode()==KeyEvent.VK_D) {
				setXDirection(0);
			}
			break;
		
	}
	
	}


	public void setXDirection(int s)
	{
		xVelocity = s;
	}
	
	
	public void move()
	{
		x = x + xVelocity; 
	}
	
	
	
	
	
	
}	
	