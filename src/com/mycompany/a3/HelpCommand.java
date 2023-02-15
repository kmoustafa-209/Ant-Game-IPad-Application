package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpCommand extends Command{
	
	public HelpCommand()
	{
		super("Help");
	}
	
	public void actionPerformed(ActionEvent e)
	{
String helpInfo = "A: Accelerates the Ant "
				+ " B: Brakes the Ant "
				+ " L: Turns the Ant left "
				+ " R: Turns the Ant right "
				+ " 1-9: Collide with a Flag "
				+ " F: Collide with Food Station "
				+ " G: Collide with Spider "
				+ " T: Adds to the Time "
				+ " X: Exit the Game";
		
		Dialog.show("Help", helpInfo, "Ok", null);
	}
}
