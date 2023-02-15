package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;

import java.util.Vector;

import com.codename1.charts.models.Point;

public class Ant extends Moveable implements ISteerable{

	private int maximumSpeed;
	private int foodLevel; 
	private int foodConsumptionRate;
	private int healthLevel;
	private int lastFlagReached;
	private static Ant Ant;
	private int width = 50;
	private int height = 50;
	private GameWorld gw;

	//Constructor that initializes Ant's values 
	private Ant(int size, GameWorld gw)
	{
		super(size);
			
		healthLevel = 10;
		maximumSpeed = 300;
		foodLevel = 1000;
		foodConsumptionRate = -1;
		lastFlagReached = 1; 
		this.gw = gw;
		
		setSpeed(150);
		setHeading(0);
		setColor(500, 0, 0);
		setLocation(50, 100);
	}
	//Implements the Singleton Pattern
	public static Ant getAnt(GameWorld gw)
	{
		if(Ant == null)
			
			Ant = new Ant(50, gw);
		
		return Ant;
	}
	//Re-initiates the values stored in Ant
	public void reset(GameWorld gw)
	{
		healthLevel = 10;
		maximumSpeed = 300;
		foodLevel = 1000;
		foodConsumptionRate = -1;
		lastFlagReached = 1; 
		this.gw = gw; 
		
		setSpeed(150);
		setHeading(0);
		setColor(500, 0, 0);
		setLocation(50, 100);
	}

	public int getMaximumSpeed() 
	{
		return maximumSpeed;
	}
	
	

	
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}


	public int getFoodLevel() {
		return foodLevel;
	}


	public void setFoodLevel(int x) {
		foodLevel = foodLevel + x;
	}


	public int getFoodConsumptionRate() {
		return foodConsumptionRate;
	}


	public void setFoodConsumptionRate(int foodConsumptionRate) {
		this.foodConsumptionRate = foodConsumptionRate;
	}


	public int getHealthLevel() {
		return healthLevel;
	}


	public void setHealthLevel(int x) {
		healthLevel = healthLevel + x;
	}


	public int getLastFlagReached() {
		return lastFlagReached;
	}


	public void setLastFlagReached(int lastFlagReached) 
	{
		this.lastFlagReached = lastFlagReached;
	}

	public void accelerate()
	{
		if(getSpeed() < getMaximumSpeed()) // Increases speed by 1
		setSpeed(20);		
	}

	public void brake()
	{
		if(getSpeed() > 0) // Decreases speed by 1
		setSpeed(-20);
		
	}
	
	@Override
	public void turnL()
	{
		
		setHeading(getHeading() - 30); // Sets Heading to current heading - 30
				
	}

	@Override
	public void turnR()
	{
		setHeading(getHeading() + 30);  // Sets Heading to current heading + 30
	}
	
	//String that overrides gameObject's toString() and concatenates new string
	public String toString()
	{
		String base = super.toString();
		String myDesc = " heading = " + getHeading() + " speed = " + getSpeed() + " size = " + getSize() + " maxSpeed= " + maximumSpeed + " foodConsumptionRate= " + foodConsumptionRate;
		String string = "Ant: "+ base + myDesc;
			
		return string;
		
	}
	
	@Override 
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		g.setColor(this.getColor());
		
		
		
		int xLoc = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX() - (width / 2));
		
		int yLoc = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY() - (height / 2));
		
		g.drawArc(xLoc, yLoc,  width,  height, 0, 360);
		g.fillArc(xLoc, yLoc,  width,  height, 0, 360);
		

	}
	// Handles Collision with all objects / Adds obj to collision vector to check in collidesWith
	@Override
	public void handleCollision(ICollider otherObject) 
	{
		if ((otherObject instanceof Spider && !super.getCollideVector().contains(otherObject)))
		{
			super.getCollideVector().add((GameObject) otherObject);
			gw.spiderCollision();
		}
		
		else if((otherObject instanceof FoodStation && !super.getCollideVector().contains(otherObject)))
		{
			super.getCollideVector().add((GameObject) otherObject);
			gw.stationCollision((GameObject) otherObject);
		}
		
		else if((otherObject instanceof Flag && !super.getCollideVector().contains(otherObject)))
		{
			
			gw.flagCollision(((Flag) otherObject).getSequenceNumber());
		}
	}
}
