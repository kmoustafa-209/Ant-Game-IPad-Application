package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public class FoodStation extends Fixed implements ISelectable
{
	private int capacity; 
	private Random rand = new Random();
	private GameWorld gw;
	private boolean selected = false;
 
	//Constructor that initializes FoodStation's values
	public FoodStation(int size, GameWorld gw)
	{
		super(size);
		this.gw = gw;
		
		setCapacity(size);
		setColor(0, 500, 0);
		setLocation(rand.nextInt(gw.getWidth()) - size, rand.nextInt(gw.getHeight()) - size);
		
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) 
	{
		this.capacity = capacity;
		
	}
		
	//String that overrides gameObject's toString() and concatenates new string
	public String toString()
	{
		String base = super.toString();
		String myDesc = " size = " + getSize() + " capacity = " + getCapacity();
		String string = "FoodStation: "+ base +myDesc;
				
		return string;
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		
		g.setColor(this.getColor());
		g.setColor(ColorUtil.GREEN);
		
		String str = getCapacity() + "";
		
		int xLoc = (int) ((int)this.getLocation().getX() + pCmpRelPrnt.getX() + (getCapacity()/2));
		int yLoc = (int) ((int)this.getLocation().getY() + pCmpRelPrnt.getY() + (getCapacity()/2));
		
		if(isSelected())
		{
			g.drawRect(xLoc, yLoc, getCapacity() , getCapacity());
		} 
		else
		{	
			g.fillRect(xLoc, yLoc, getCapacity() , getCapacity());	
		}
		 
		g.setColor(ColorUtil.BLACK);
		
		g.drawString(str, xLoc, yLoc);
			
	}

	@Override
	public void handleCollision(ICollider otherObject) {
		
		super.getCollideVector().add((GameObject) otherObject);
	}

	@Override
	public void setSelected(boolean y) {
		selected  = y;
		
	}

	@Override
	public boolean isSelected() {
		return selected;
	}

	@Override
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrint) 
	{
		int px = (int) pPtrRelPrnt.getX();
		int py = (int) pPtrRelPrnt.getY();
		
		int xLoc = (int) ((int)this.getLocation().getX() + pCmpRelPrint.getX());
		int yLoc = (int) ((int)this.getLocation().getY() + pCmpRelPrint.getY());
		
		if ( ((px >= xLoc - getSize() /  2) && (px <= xLoc + getSize() / 2)) && 
				((py >= yLoc - getSize() / 2) && (py <= yLoc + getSize() / 2)))
		{
			return true;
		}
		return false;
	}
}
