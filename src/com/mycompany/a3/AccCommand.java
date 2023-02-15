package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class AccCommand extends Command
{
	private GameWorld gw;

	public AccCommand(GameWorld gw)
	{
		super("Accelerate");
		this.gw = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent ev)
	{
		gw.accelerate();
	}
}
