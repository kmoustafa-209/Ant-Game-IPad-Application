package com.mycompany.a3;

import java.util.Random;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;

public class Flag extends Fixed implements ISelectable{
	
	private int sequenceNumber; 
	private int color;
	private GameWorld gw;
	private boolean selected = false; 
	
	//Constructor that initializes Flag's values
	Flag(int size, int num, double x, double y, GameWorld gw){
		super(size);
		this.gw = gw;
		
		setColor(0, 0, 500);
		setLocation(x, y);
		setSequenceNumber(num);
		
	}
	public int getColor(){
		return color;
	}
	public int getSequenceNumber() {
		return sequenceNumber;
	}
	public void setSequenceNumber(int sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}	
	
	//Overrides setColor() from GameObject Class
	@Override
	public void setColor(int r, int g, int b) 
	{

	}
	//String that overrides gameObject's toString() and concatenates new string
	public String toString()
	{
		String base = super.toString();
		String myDesc = " size= " + getSize() + " seqNum= " + getSequenceNumber();
		String string = "Flag: "+ base +myDesc;
			
		return string;
		
	}
	
	@Override
	public void draw(Graphics g, Point pCmpRelPrnt)
	{
		g.setColor(this.getColor());
		g.setColor(ColorUtil.BLUE);
		
		
		String str = getSequenceNumber() + "";
		
		int xLoc = (int) ((int)this.getLocation().getX() + pCmpRelPrnt.getX());
		int yLoc = (int) ((int)this.getLocation().getY() + pCmpRelPrnt.getY());
		
		int[] xPoints = { xLoc, (xLoc - getSize()), (xLoc + getSize()), xLoc };
		
		int[] yPoints = { (yLoc + getSize()), (yLoc - getSize()), (yLoc - getSize()), (yLoc + getSize()) };
		
		int nPoints = 4;
		
		if(isSelected())
		{
			g.drawPolygon(xPoints, yPoints, nPoints);
		} 
		else
		{	
			g.fillPolygon(xPoints, yPoints, nPoints);
			
		}
		
		g.setColor(ColorUtil.BLACK);
		
		g.drawString(str, xLoc, yLoc);

	}

	@Override
	public void handleCollision(ICollider otherObject) 
	{
		super.getCollideVector().add((GameObject) otherObject);
	}
	
	@Override
	public void setSelected(boolean y) {
		selected = y;
		
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
