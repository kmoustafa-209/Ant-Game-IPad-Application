package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class ExitCommand extends Command{
	
	public ExitCommand()
	{
		super("Exit");
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (Dialog.show("Quit", "Are you sure you want to exit?", "Yes", "No"))
		{
			System.exit(0);
		}
	}
}
