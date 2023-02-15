package com.mycompany.a3;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	
	public AboutCommand()
	{
		super("About");
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String aboutInfo = "Kareem Moustafa, CSC 133, Computer Science Major";
		
		Dialog.show("About", aboutInfo, "Ok", null);
	}
}
