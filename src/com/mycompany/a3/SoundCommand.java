package com.mycompany.a3;

import com.codename1.ui.CheckBox;
import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SoundCommand extends Command{
	
	private CheckBox sound;
	private GameWorld gw;
	
	public SoundCommand(GameWorld gw, CheckBox sound)
	{
		super("Sound");
		this.gw = gw;
		this.sound = sound;
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		if (sound.isSelected())
		{
			System.out.println("Sound turned on");
			
		}
		else
		{
			System.out.println("Sound turned off");
		}
		gw.setSound();
	}
	

}
