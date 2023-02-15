package com.mycompany.a3;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.plaf.Border;

public class ButtonDesign extends Button
{
	public ButtonDesign(Command cmd)
	{
		//Button Design for myy Buttons
		this.getAllStyles().setBgTransparency(255);
		this.getAllStyles().setBgColor(ColorUtil.BLUE);
		this.getAllStyles().setFgColor(ColorUtil.WHITE);
		
		this.getAllStyles().setBorder(Border.createLineBorder(3, ColorUtil.BLACK));
		this.getAllStyles().setPadding(TOP, 5);
		this.getAllStyles().setPadding(BOTTOM, 5);
		
		this.getAllStyles().setPadding(LEFT, 7);
		this.getAllStyles().setPadding(RIGHT, 7);
		
		this.setCommand(cmd);
	}
}

