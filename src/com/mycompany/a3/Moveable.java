package com.mycompany.a3;

import com.codename1.charts.models.Point;
import java.lang.Math; 

public abstract class Moveable extends GameObject {
	
	private int heading;
	private int speed;
	private GameWorld gw;
	
	public Moveable(int size)
	{
		super(size);
	} 
	
	public int getHeading()
	{
		return heading;
	}
	
	public int getSpeed()
	{
		return speed; 
	}
	
	
	public void setSpeed(int x)
	{
		speed = speed + x;
	}
	
	public void setHeading(int x)
	{
		heading = x;
	}
	
	// General move function that Moveable objects can call
	public void move(GameWorld gw, float t){
		
		Point oldLocation = getLocation();
		Point newLocation = new Point(0,0);
		this.gw = gw; 
		
		int theta = 90 - heading;
		int mapWidth = gw.getWidth();
		int mapHeight = gw.getHeight();
		
		double deltaX = 0; 
		double deltaY = 0;
		

		deltaX = Math.cos(Math.toRadians(theta))* speed * (t/1000); 
		deltaY = Math.sin(Math.toRadians(theta)) * speed * (t/1000);
		
		newLocation.setX((float) (deltaX + oldLocation.getX())); 
		newLocation.setY((float) (deltaY + oldLocation.getY()));		
		
		   if(newLocation.getX() + this.getSize() >= mapWidth)
		   {
               newLocation.setX(newLocation.getX() - this.getSize());
               this.setHeading(180 + heading);
               
           }
		   else if(newLocation.getX() < 0) 
		   {
               newLocation.setX((float) (0.0 +this.getSize()));
               this.setHeading(180 + heading);
           }
           if(newLocation.getY() + this.getSize() >= mapHeight)
           {
               newLocation.setY(newLocation.getY() - this.getSize());
               this.setHeading(180 - heading);
           }
           else if(newLocation.getY() < 0) 
           {
               newLocation.setY((float) (0.0 +this.getSize()));
               this.setHeading(180 - heading);
           }
           
           this.setLocation(newLocation.getX(),newLocation.getY());
	}
}
