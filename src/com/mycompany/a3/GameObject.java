package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;
import java.util.Vector;

public abstract class GameObject implements IDrawable, ICollider
{
	
	private Random rand = new Random();
	private int size;
	private Point location;
	private int color;
	private Vector<GameObject> gameCollide = new Vector<GameObject>();
	
	public GameObject(int size)
	{
		float x = (float) (Math.round((1001.0* rand.nextDouble() * 10.0)) / 10.0);
		float y = (float) (Math.round((1001.0* rand.nextDouble() * 10.0)) / 10.0);
		
		//Accounts for Edging or going off screen
		if( x > 1517)
			x = 1517;
		
		if(y > 1216)
			y = 1216;
		 
		location = new Point(x,y);
		this.size = size;
	}
	
	
	public int getSize()
	 {
		return size;
	} 
	 public Point getLocation()
	 {
		 return location;  
	 }
	 
	 public void setLocation(double x, double y)
	 {
		 location.setX((float) (Math.round(x)*10.0/10.0));
		 location.setY((float) (Math.round(y)*10.0/10.0));
	 }
	 
	 public void setLocation(Point loc)
	 {
		 location.setX((float) ((Math.round(loc.getX())*10.0)/10.0));
		location.setY((float) ((Math.round(loc.getY())*10.0)/10.0));
	 }
	 
	 public int getColor()
	 {
		 return color;
	 }
	 
	 public void setColor(int R, int G, int B)
	 {
		this.color = ColorUtil.rgb(R, G, B);
		
	 }
	
	public String toString()
	{
		String string = 
			" Location = " + location.getX() + "," + location.getY() 
			+ " Color = [" + ColorUtil.red(color) + "," +
			ColorUtil.green(color) +"," +
			ColorUtil.blue(color) + "]";
		return string;	
	}
	// General collidesWith method for all GameObjects
	
	@Override
	public boolean collidesWith(ICollider otherObject)
	{
		boolean result = false;
	
		double thisCenterX = this.getLocation().getX();
		double thisCenterY = this.getLocation().getY();
		
		double otherCenterX = ((GameObject)otherObject).getLocation().getX();
		double otherCenterY = ((GameObject)otherObject).getLocation().getY();
		
		double dx = thisCenterX - otherCenterX;
		double dy = thisCenterY - otherCenterY;
		
		double distBetweenCentersSqr = (dx * dx + dy * dy);
		
		int thisRadius= this.getSize() / 2;
		int otherRadius= ((GameObject)otherObject).getSize() / 2;
		
		int radiiSqr= (thisRadius * thisRadius + 2 * thisRadius * otherRadius + otherRadius * otherRadius);
		
		if (distBetweenCentersSqr <= radiiSqr) 
		{ 
			result = true;	
		}
		else
		{
			gameCollide.remove(otherObject); // Removes obj from collisionVector
		}
		return result;
	}
	
	public Vector<GameObject> getCollideVector()
	{
		return gameCollide;
	}
}
