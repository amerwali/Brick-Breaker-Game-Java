import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Blocks extends Rectangle{

	int id;
	int value=1;

	Blocks(int x, int y, int width, int height, int i, int v){
		super(x,y,width,height);
		id = i;
		value = v;
	}
	
	public void setValue(int v)
	{
		value = v;
	}
	public int negative( int num)
	{
		
		if(num >= 0)
		{
			return -(num);
		}
		
		return num;
	}
	
	public boolean blockColl(Ball b)
	{
		
		if(intersects(b))
		{
			
			//if the ball is below
			if(b.y >= y + 29 )
			{
				b.yVelocity = Math.abs(b.yVelocity);
				//System.out.println(b.y + " y  " + y);
				if(value <= 1)
				{
					return true;
				}
				else
				{
					value--;
				}
			}
			//if the ball is directly above
			else if(b.y+14  <= y)		{
				
				
				
				b.setYDirection(negative(b.yVelocity));
				
				
				
				if(value <= 1)
				{
					return true;
				}
				else
				{
					value--;
				}
			}
			//if the ball is to the left
			else if(b.x <= x )
			{
				
				b.setXDirection(negative(b.xVelocity));
				//System.out.println(b.x + " x " + x);
				if(value <= 1)
				{
					return true;
				}
				else
				{
					value--;
				}
			}
			//if the ball is to the right
			else if(b.x +8 >= x )
			{
				b.xVelocity = Math.abs(b.xVelocity);
				//System.out.println(b.x + " x " + x);
				if(value <= 1)
				{
					return true;
				}
				else
				{
					value--;
				}
			}
			
			}
					
		return false;
	
		
	}
	
	

	
	public void draw(Graphics g)
	{
		g.setColor(Color.white);
		g.fillRect(x, y, height, width);
		g.setColor(Color.blue);
		g.drawString(String.valueOf(value), x-1, y+20);
	}
	
}
