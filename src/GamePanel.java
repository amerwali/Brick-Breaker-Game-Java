import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;



public class GamePanel extends JPanel implements Runnable{

	Paddle paddle;
	ArrayList<Ball> ball = new ArrayList<Ball>();
	Thread gameThread;
	Random random;
	ArrayList<Blocks> blo = new ArrayList<Blocks>();
	Blocks b;
	ArrayList<multiplier> mult = new ArrayList<multiplier>();
	int size = 112;
	boolean running = false;
	boolean won = false;
	int score = 0;

	
	GamePanel()
	{
		newPaddles();
		newBall();
		newBlocks();
		this.setPreferredSize(new Dimension(600,600));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new AL());
		gameThread = new Thread(this);
		gameThread.start();
		running = true;
		
	}
	
	public void  newBlocks() 
	{
		int j = 0;
		int v = 80;
		for(int i = 2; i <= 11; i++)
		{
			
			for(int k = 2; k < 16; k++)
			{
				
				blo.add(new Blocks(k*35,i*35,30,30,1,v));
				//System.out.println(blo.get(j) + " " +  j);
				j++;
			}
			v -= 10;
		}
		

	}
	
	public void newBall() {
		random = new Random();
		ball.add(new Ball(300,400,16,16));
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	
	public void draw(Graphics g)
	{
		
		if(score == 112)
		{
			running = false;
			won = true;
			g.setColor(Color.blue);
			g.setFont(new Font("Ink Free", Font.BOLD, 35));
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Winner Winner Chicken Dinner", (600 - metrics.stringWidth("Winner Winner Chicken Dinner"))/2, 600/2);
		}
		
		if(running)
		{
		score(g);
		//draw the grid to help
		/*
		for(int i=0; i<600/25;i++)
		{
			g.drawLine(i*25, 0, i*25, 600);
			g.drawLine(0, i*25, 600, i*25);
		}
		*/
		//draw the paddle
		paddle.draw(g);
		//draws ball
		for(int i = 0; i < ball.size(); i++)
		{
			ball.get(i).draw(g);
		}
	
		for(int i = 0; i<size; i++)
		{
			blo.get(i).draw(g);
			//((Blocks) block[i]).draw(g);
		}
		if(mult.size() > 0)
		{
			for(int i = 0; i < mult.size(); i++)
			{
				mult.get(i).draw(g);
			}
		}
		
		Toolkit.getDefaultToolkit().sync();
		}
		else if(!won && !running)
		{
		gameOver(g);	
		}
		
	}
	
	public void score(Graphics g)
	{
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 25));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Score: " + score, (600 - metrics.stringWidth("Score: " + score)), 20);
	}
	
	public void gameOver(Graphics g)
	//Game over text
	{
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 75));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("Game Over", (600 - metrics.stringWidth("Game Over"))/2, 600/2);
	
		g.setColor(Color.red);
		g.setFont(new Font("Ink Free", Font.BOLD, 40));
		FontMetrics metrics1 = getFontMetrics(g.getFont());
		g.drawString("Score: " + score, (600 - metrics1.stringWidth("Score: " + score))/2, g.getFont().getSize());
		
	}
	
	public void newPaddles() {
		paddle = new Paddle(600/2, 575, 125,25);
	}
	
	public void move()
	{
		paddle.move();
		for(int i = 0; i < ball.size(); i++)
		{
			ball.get(i).move();
		}
		for(int i = 0; i < mult.size(); i++)
		{
			mult.get(i).move();
		}
	}
	
		
		
	
	
	public void checkCollision()
	{
		//checks paddle if hitting wall
		
		if(paddle.x <= 0)
		{
			paddle.x = 0;
		}
		
		if(paddle.x >= 600 - 125)
		{
			paddle.x = 600-125;
		}
		
		//check if ball hits paddle
		for(int i = 0; i < ball.size(); i++)
		{
			boolean remove = false;
		
		if(ball.get(i).intersects(paddle))
		{
			ball.get(i).yVelocity = -ball.get(i).yVelocity;
			ball.get(i).setXDirection(ball.get(i).xVelocity + 1);
		}
		
		//check if ball hits the walls
		
		if(ball.get(i).x >= 600 - 25)
		{
			ball.get(i).xVelocity = -ball.get(i).xVelocity;
		}
		if(ball.get(i).y <= 0)
		{
			ball.get(i).yVelocity = Math.abs(ball.get(i).yVelocity);
		}
		if(ball.get(i).x <= 0)
		{
			ball.get(i).xVelocity = Math.abs(ball.get(i).xVelocity);
		}
		if(ball.get(i).y >= 600 - 25)
		{
			ball.get(i).yVelocity = -ball.get(i).yVelocity;
			if(ball.size() <= 1)
			{
				running = false;
			}
			else
			{
				remove = true;
			}
		}
		
		for(int j = 0; j<size; j++)
		{
			if(blo.get(j).blockColl(ball.get(i)))	
			{
				blo.remove(j);
				randomMult(blo.get(j).x, blo.get(j).y);
				size--;
				score++;
			}
			
		}
		if(remove)
		{
			remove = false;
			ball.remove(i);
		}
		}
		for(int i = 0; i < mult.size(); i++) {
			if(mult.get(i).intersects(paddle))
			{
				ballDupe(i);
				mult.remove(i);
			}
			else if(mult.get(i).y > 600)
			{
				mult.remove(i);
			}
		}
			
	}
	
	public void randomMult(int x, int y)
	{
		int random = (int)(Math.random() * 10 + 1);
		
		if(random == 1 || random == 8)
		{
			mult.add(new multiplier(x, y, 10, 10));
		}
	}
	
	public void ballDupe(int j)
	{
		int newSize = ball.size() * 2;
		for(int i = ball.size(); i < newSize; i++)
		{
			ball.add(new Ball(ball.get(j).x,ball.get(j).y,16,16));
		}
	}
	
	public void run() {
		//game loop
		long lastTime = System.nanoTime();
		double amountOfTicks =60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		while(true) {
			long now = System.nanoTime();
			delta += (now -lastTime)/ns;
			lastTime = now;
			if(delta >=1) {
				if(running)
				{
					move();
					checkCollision();
					repaint();
				}
				delta--;
				
			}
		}
	}
	
	
	
	
	
	
	
	public class AL extends KeyAdapter
	{
		public void keyPressed(KeyEvent e) {
			paddle.keyPressed(e);
			
		}
		public void keyReleased(KeyEvent e) {
			paddle.keyReleased(e);
			
		}
	}
		
	}
	
	
	
	
	







