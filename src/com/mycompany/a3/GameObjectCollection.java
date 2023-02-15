package com.mycompany.a3;

import java.util.Vector;

public class GameObjectCollection implements ICollection
{
	private Vector<GameObject> objs;
	
	public GameObjectCollection()
	{
		objs = new Vector<GameObject>();
	}
	
	@Override
	public void add(GameObject obj) 
	{
		objs.addElement(obj);
		
	}
	
	public IIterator getIterator()
	{
		return new GameObjectIterator();
	}
	
	@Override
	public void clear() 
	{
		
		objs.clear();
	}
	
	public int size() 
	{
		return objs.size();
	}

	public GameObject get(int i) 
	{
		
		return objs.get(i);
	}
	

	private class GameObjectIterator implements IIterator 
	{
		private int currElementIndex;
		
		public GameObjectIterator() 
		{
			currElementIndex = -1; 
		}
		
		public boolean hasNext() 
		{
		if(objs.size() <= 0)
			return false;
		if(currElementIndex == objs.size() - 1)
			return false;
		
		return true;
		}
		
		public GameObject getNext() {
			
			currElementIndex++;
			return(objs.elementAt(currElementIndex));
		}
		
	}
}


