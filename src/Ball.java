import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

public class Ball extends Rectangle {


	Random random;
	int xVelocity;
	int yVelocity;
	int initialSpeed = 4;
	
	
	
	Ball(int x, int y, int width, int height){
		super(x,y,width,height);
		random = new Random();
		int randomXDirection = random.nextInt(2);
		if(randomXDirection == 0)
			randomXDirection--;
		setXDirection(randomXDirection*initialSpeed);
		
		int randomYDirection = random.nextInt(2);
		if(randomYDirection == 0)
			randomYDirection--;
		setYDirection(randomYDirection*initialSpeed);
		
	}
	
	public void setXDirection(int s)
	{
		xVelocity = s;
	}
	public int getXVelocity()
	{
		return xVelocity;
	}
	public void setYDirection(int s)
	{
		yVelocity = s;
	}
	
	public void move() {
		x = x + xVelocity;
		y = y + yVelocity;
		
		
		
	}
	
	public double getY()
	{
		return y;
	}
	
	public double getX()
	{
		return x;
	}
	
	
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillOval(x, y, height, width);
	}

	public boolean intersects(Object block) {
		// TODO Auto-generated method stub
		return false;
	}
}
