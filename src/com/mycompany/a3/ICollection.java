package com.mycompany.a3;

public interface ICollection 
{
	public void add(GameObject obj);
	
	public IIterator getIterator();
	
	public void clear();
	
	public int size();
	
	public GameObject get(int i); 
}
