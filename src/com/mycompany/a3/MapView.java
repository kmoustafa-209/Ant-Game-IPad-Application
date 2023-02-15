package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.TextArea;
import com.codename1.charts.models.Point;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer{

	private int RED = ColorUtil.rgb(255,0,0);
	private GameWorld gw;
	
	
	public MapView()
	{
		//Creates an Empty Container with Style for MapView for future Usage
		getAllStyles().setBgTransparency(255);
		getAllStyles().setBgColor(ColorUtil.WHITE);
		
		getAllStyles().setBorder(Border.createLineBorder(2,RED));
		
	}

	@Override
	public void update(Observable observable, Object data) 
	{
		gw = (GameWorld) observable;
		
		//Calls the repaint() when update() is invoked
		repaint();
	}
	
	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		
		GameObject next;
		Point pCmpRelPrnt = new Point(getX(), getY());
		IIterator iter = gw.getCollection().getIterator();
		
		while (iter.hasNext())
		{
			next = (GameObject) iter.getNext();
			if (next instanceof IDrawable)
			{

				((IDrawable) next).draw(g, pCmpRelPrnt);
			}
		} 
	}
	
	 @Override
	    public void pointerPressed(int x, int y)
	    {
		 super.pointerPressed(x, y);
	    
	        
		 x = x - getParent().getAbsoluteX();
		 y = y - getParent().getAbsoluteY();
	           
		 Point pPtrRelPrnt = new Point(x, y);
		 Point pCmpRelPrnt = new Point(getX(), getY());
	        
		 IIterator iter = gw.getCollection().getIterator();
	        
		 System.out.println("(" + x + "," + y + ")");
	      
		 // If-else statement to tell pointerPressed what to do dependent on isSelected's return value
	     if(gw.getSelection())
		 {
	    	 while (iter.hasNext())
             {
	    		 GameObject obj = iter.getNext();
             
	    		 if (obj instanceof ISelectable)
	    		 {
	    			 ISelectable selectObj = (ISelectable)obj;
                 
	    			 if(selectObj.isSelected())
	    			 {
	    				 if(gw.getSelection() && selectObj instanceof Flag)
	    				 {
	    					 ((Flag) selectObj).setLocation(x - getX(), y - getY());
	    					 gw.setSelection();
	    					 break;
	    				 }
                     
	    				 else if(gw.getSelection() && selectObj instanceof FoodStation)
	    				 {
	    					 ((FoodStation) selectObj).setLocation(x - getX(), y - getY());
	    					 gw.setSelection();
	    					 break;
	    				 }
	    			 }
	    		 }
             }
		 }
		else
		{
		 while (iter.hasNext())
         {
             GameObject obj = iter.getNext();
             
             
             if (obj instanceof ISelectable)
             {
                 ISelectable selectObj = (ISelectable)obj;
                 
                 if (selectObj.contains(pPtrRelPrnt, pCmpRelPrnt))
                 {
                         
                     selectObj.setSelected(true);
                 }
                 else
                 {
                     selectObj.setSelected(false);
                 }
             }
         }
		}
	        repaint();
	    }
}