package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public class Spider extends Moveable {
	
	private Random rand = new Random();
	private GameWorld gw; 
	
	//Constructor that initializes Spider's values
	public Spider(int size, GameWorld gw)
	{ 
		super(size);
		this.gw = gw;
		
		setSpeed(50 + rand.nextInt(200));
		setHeading(rand.nextInt(359));
		setLocation(rand.nextInt(gw.getWidth()), rand.nextInt(gw.getHeight()));
		
		setColor(30,200, 10);
		
	}
	
	
	public void update()
	{
		int x =  + rand.nextInt(5 + 5) -5; // Randomly adjusts heading with an integer between -5 and 5
		setHeading(getHeading() + x);
	}
	//Overrides setColor() from GameObject Class
	@Override
	public void setColor(int r, int g, int b) {

	}
	//String that overrides gameObject's toString() and concatenates new string
	public String toString()
	{
		String base = super.toString();
		String myDesc = " heading = " + getHeading() + " speed = " + getSpeed() + " size = " + getSize();
		String string = "Spider: "+ base +myDesc;
			
		return string;
		
	}


	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) 
	{
		g.setColor(this.getColor());
		g.setColor(ColorUtil.BLACK);
		
		int xLoc = (int) ((int)this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) ((int)this.getLocation().getY() + pCmpRelPrnt.getY());
		
		int[] xPoints = { xLoc, (xLoc - getSize()), (xLoc + getSize()), xLoc };
		
		int[] yPoints = { (yLoc + getSize()), (yLoc - getSize()), (yLoc - getSize()), (yLoc + getSize()) };
		
		int nPoints = 4;
		
		
		g.drawPolygon(xPoints, yPoints, nPoints);
		
	}


	@Override
	public void handleCollision(ICollider otherObject) 
	{
		super.getCollideVector().add((GameObject) otherObject);
	
	}
}
