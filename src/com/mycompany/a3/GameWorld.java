package com.mycompany.a3;


import com.mycompany.a3.Ant;
import com.mycompany.a3.Flag;
import com.mycompany.a3.FoodStation;
import com.mycompany.a3.Spider;

import java.util.Observable;
import java.util.Random;
import java.util.Vector;

public class GameWorld extends Observable {
	
   private GameObjectCollection gc; 
  
   private int time;
   private int livesRemaining;
   private boolean endGame; 
   private boolean sound = false;
   private boolean isSelected = false;
   private Random rand = new Random();
   
   private int width;
   private int height;
   private Sound foodCollision, spiderCollision, flagCollision;
   private BGSound bgmusic;
   private int flagcount = 4;


   
   
  
   //Initialize the Initial Game State
	 GameWorld() 
	{
		time = 0;
		livesRemaining = 3;
	}
	
	//Initialize the Initial Game Objects
	public void init()
	{
		gc = new GameObjectCollection();
		
		gc.add(Ant.getAnt(this));
		gc.add(new Flag(50, 1, 50, 100, this));
		gc.add(new Flag(50, 2, 400, 400, this));
		gc.add(new Flag(50, 3, 700, 900, this));
		gc.add(new Flag(50, 4, 1100, 1000, this));
	
		gc.add(new Spider(20 + rand.nextInt(50), this));
		gc.add(new Spider(20 + rand.nextInt(50), this));
		gc.add(new FoodStation(20 + rand.nextInt(100), this));
		gc.add(new FoodStation(20 + rand.nextInt(100), this));
		
		setChanged();
		notifyObservers();
	
	}
	//Initialize the Initial Game Sounds
	public void createSounds() 
	{
		foodCollision = new Sound("food.wav") ;
		spiderCollision = new Sound("spider.wav");
		flagCollision = new Sound("flag.wav");
		bgmusic = new BGSound("bgmusic.wav");

	}
	
	
	//Methods for retrieving lives, time, and sound
	
	public int getLives()
	{
		return livesRemaining;
	}
	
	public int getTime()
	{
		return time;
	}
	public boolean getSound()
	{
		return sound;
	}
	
	public void setSound()
	{
		sound = !sound;
		
		if(sound == true)
		{
			bgmusic.play();
		}
		
		else
		{
			bgmusic.pause();
		}
		
		setChanged();
		notifyObservers();

	}
	//Method to retrieve GameObjectCollection
	public GameObjectCollection getCollection()
	{
		return  this.gc;
	}
	
	//Methods to help query width and height 
	
	public int getHeight()
	{
		return height;
	}
	
	public void setHeight(int height)
	{
		this.height = height;
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public void setWidth(int width)
	{
		this.width = width;
	}
	//Methods for Checking boolean value of endGame
	public boolean isEndGame()
	{
		return endGame;
	}
	
	public void setEndGame(boolean endGame)
	{
		this.endGame = endGame; 
	}
	//Constructors created to retrieve instances of different GameObjects (utilizes Iterator Pattern)
	public Ant Ant()
	{
		IIterator itr = gc.getIterator();
		
		while(itr.hasNext())
		{
			GameObject go =itr.getNext();
			
			if(go instanceof Ant)
			{

				return (Ant) go;
			}
		}
		
		setChanged();
		notifyObservers();
		
		return null;
	}
	
	public Flag Flag()
	{
		IIterator itr = gc.getIterator();
		
		while(itr.hasNext())
		{
			GameObject go =itr.getNext();
			
			if(go instanceof Flag)
			{
				
				return (Flag) go;
			}
		}
		
		setChanged();
		notifyObservers();
		
		return null;
	}
	
	
	public Spider Spider()
	{
		IIterator itr = gc.getIterator();
		
		while(itr.hasNext())
		{
			GameObject go =itr.getNext();
			
			if(go instanceof Spider)
			{
				
				return (Spider) go;
			}
		}
		setChanged();
		notifyObservers();
		
		return null;
	}
	
	public FoodStation FoodStation()
	{
		IIterator itr = gc.getIterator();
		
		while(itr.hasNext())
		{
			GameObject go =itr.getNext();
			
			if(go instanceof FoodStation)
			{
				
				return (FoodStation) go;
			}
		}
		setChanged();
		notifyObservers();
		
		return null;
	}
	
	//Method to check if Game Over
	public void gameOver()
	{
		if(livesRemaining == 0)
		{
			System.out.println("Game Over, you failed!");
			time = 0;
			livesRemaining = 3;
			gc.clear();
			init();
		}
		
		setChanged();
		notifyObservers();
	}
	//Method for accelerating Ant
	public void accelerate() 
	{
		if(Ant() != null)
		{
			
			Ant().accelerate();
			System.out.println("Accelerate is applied");
			
		}
		
		setChanged();
		notifyObservers();
	}
	//Method for braking Ant
	public void brake()
	{
		if(Ant() != null)
		{
			Ant().brake();
			System.out.println("Brake is applied");
		}
		
		setChanged();
		notifyObservers();
	}
	//Method for turning Ant left
	public void turnL()
	{
		if(Ant() != null)
		{
			Ant().turnL();
			System.out.println("Left turn is applied");
		}
		
		setChanged();
		notifyObservers();
	}
	//Method for turning Ant right
	public void turnR()
	{
		if(Ant() != null)
		{
			Ant().turnR();
			System.out.println("Right turn is applied"); 
		}	
		
		setChanged();
		notifyObservers();
	}
	//Method for collision between Flag and Ant
	   public void flagCollision(int num)
	{
	
    
	if(num == Ant().getLastFlagReached() + 1)
    {
		if(sound == true)
		{
		flagCollision.play();
    	} 	
		
      	Ant().setLastFlagReached(num);
		System.out.println("Flag has been reached");
    }
    else if(Ant().getLastFlagReached() == flagcount)  // Check to see if lastFlagReached has reached last Flag
    {
    	if(sound == true)
        {
    		flagCollision.play();
        } 	
     
    	System.out.println("Game over, you win! Total time: " + (time / 1000));
        	
     // Exits Game
     System.exit(0);
    }
           
 
        
        setChanged();
		notifyObservers();   
     
    }
	//Method for collision between FoodStation and Ant 
	public void stationCollision(GameObject thisObj) // NEED TO FIX 
    {
		FoodStation station = (FoodStation) thisObj;
		
		if(station.getCapacity() != 0)
		{
			if(sound == true)
            {
				foodCollision.play();
            }
         
            	  
            Ant().setFoodLevel(station.getCapacity());
            station.setCapacity(0);
            station.setColor(0, 100, 0);
            System.out.println("Food Station has been consumed");
            gc.add(new FoodStation(20 + rand.nextInt(100), this)); 
		}
			
        setChanged();
		notifyObservers();
		
    }
	//Method for collision between Spider and Ant
	public void spiderCollision()
	{
	 	if(sound == true)
  		{
  		  spiderCollision.play();
  		}
	 	
		Ant().setHealthLevel(-1);
		Ant().setColor(400, 0, 0);
		if(Ant().getSpeed() < 1)
		{
			Ant().setSpeed(0);
		}
		else
		{
			Ant().setSpeed(-1);
		}
		//If instanceof Ant's health is 0
		if(Ant().getHealthLevel() == 0 && livesRemaining > 0) 
		{
			livesRemaining--;
			Ant().setMaximumSpeed(0);
			System.out.println("Lost Life.");
			gc.clear();	
			init();
			Ant().reset(this);
		
		}	
		
		else
		{
		gameOver();
		
		}
		setChanged();
		notifyObservers();
	}
	//Method for our Game Timer
	public void tick(float time)
	{
		if(Ant().getFoodLevel() == 0 && livesRemaining > 0)
		{
			livesRemaining--;
			Ant().setMaximumSpeed(0);
			System.out.println("Lost Life.");
			gc.clear();
			init();
			Ant().reset(this);
			
		}
		else
		{
			Ant().setFoodLevel(Ant().getFoodConsumptionRate());
		}
		Spider().update(); //Updates spider's heading
		
		IIterator itr = gc.getIterator();
		 
		while(itr.hasNext())
		{
			GameObject go =itr.getNext();
			 
			if(go instanceof Moveable)
			
				((Moveable) go).move(this, time);
		}
		gameOver();
		
		
		this.time += time;
		
		handleCollisions();
		setChanged();
		notifyObservers();
	}
	//Method for Game State Display
	public void display()
	{
	
		System.out.println("Lives Left: " + livesRemaining);
		System.out.println("Time: " + time);
		System.out.println("Highest Flag: " + Ant().getLastFlagReached());
		System.out.println("Current Food Level: " + Ant().getFoodLevel());
		System.out.println("Current Health Level: " + Ant().getHealthLevel());
		
		setChanged();
		notifyObservers();
	}
	//Method to handle Collisions
	public void handleCollisions()
	{
		IIterator iter = gc.getIterator();
		
		while(iter.hasNext())
		{
			GameObject thisObj = iter.getNext();
			if(thisObj instanceof ICollider)
			{
				ICollider thisColliderObj = (ICollider) thisObj;
				IIterator otherIter = gc.getIterator();
				while(otherIter.hasNext())
				{
					GameObject otherObj = otherIter.getNext();
					if(otherObj instanceof ICollider && !(thisObj.equals(otherObj)))
					{
						if(thisColliderObj.collidesWith(otherObj))
						{
							thisColliderObj.handleCollision(otherObj);
						}
					}
				}
			}
		}	
		
		setChanged();
		notifyObservers();
	}
	// Getter and Setter for boolean isSelected; utilized for Position Command
	
	public boolean getSelection()
	{
		return isSelected;
	}
	
	public void setSelection()
	{
		isSelected = !isSelected;
	}
}
