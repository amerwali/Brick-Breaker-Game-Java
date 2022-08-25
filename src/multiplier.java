import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;
public class multiplier extends Rectangle{

	Random random;
	int yVelocity;
	int initialSpeed = 4;
	
	multiplier(int x, int y, int WIDTH, int HEIGHT){
		super(x,y,WIDTH,HEIGHT);	
		yVelocity = 4;
	}
	
	public void move()
	{
		y = y + yVelocity; 
	}
	
	
	
	
	public void draw(Graphics g) {
		g.setColor(Color.blue);
		g.fillRect(x, y, height, width);
	}
	
}
