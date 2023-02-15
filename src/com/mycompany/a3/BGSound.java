package com.mycompany.a3;

import java.io.InputStream; 
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{
	
	private Media m;
	
	public BGSound(String fIn)
	{
		while(m == null) 
		{
			try 
			{
			InputStream is = Display.getInstance().getResourceAsStream(getClass(),
			"/" + fIn);
			m = MediaManager.createMedia(is, "audio/wav");
			} catch (Exception e) 
			{
			e.printStackTrace(); 
			}
		}
		
	}
	public void pause()
	{
		m.pause();
	}
	public void play()
	{
		m.play();
	}
	
	@Override
	public void run()
	{
		m.setTime(0);
		m.play();
	}
	
}