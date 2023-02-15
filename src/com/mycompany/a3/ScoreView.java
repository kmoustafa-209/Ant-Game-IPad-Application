package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.FlowLayout;

public class ScoreView extends Container implements Observer{

	private Label livesLeft;
	private Label elapsedTime;
	private Label lastFlagReached;
	private Label foodLevel;
	private Label healthLevel;
	private Label soundSetting;
	
	public ScoreView()
	{
		this.setLayout(new FlowLayout(CENTER));
		
		setTime();
		setLives();
		setLastFlag();
		setFood();
		setHealth();
		setSound();
	}
	//Sets the Time for the ScoreView
	public void setTime()
	{
		Label time = new Label("Time:");
		
		time.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		elapsedTime = new Label(" 0 ");
		elapsedTime.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(time);
		this.add(elapsedTime);
		
	}
	//Sets the Lives for the ScoreView
	public void setLives()
	{
		Label life = new Label("Lives:");

		life.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		livesLeft = new Label("0 ");
		livesLeft.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(life);
		this.add(livesLeft);

	}
	//Sets the LastFlagReached for the ScoreView
	public void setLastFlag()
	{
		Label last = new Label("Last Flag Reached:");

		last.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		lastFlagReached = new Label("0 ");
		lastFlagReached.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(last);
		this.add(lastFlagReached);
	}
	//Sets the FoodLevel for the ScoreView
	private void setFood() 
	{
		Label food = new Label("Food Level:");

		food.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		foodLevel = new Label(" 0 ");
		foodLevel.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(food);
		this.add(foodLevel);
		
	}
	//Sets the Health for the ScoreView
	private void setHealth() 
	{
		Label health = new Label("Health Level:");

		health.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		healthLevel = new Label(" 0 ");
		healthLevel.getAllStyles().setPadding(RIGHT, 5);
		
		this.add(health);
		this.add(healthLevel);
		
	}
	//Sets the Sound for the ScoreView
	private void setSound() 
	{
		Label sound = new Label("Sound:");
		
		sound.getAllStyles().setFgColor(ColorUtil.BLUE);
		
		soundSetting = new Label("OFF");
		soundSetting.getAllStyles().setPadding(RIGHT,5);
		
		this.add(sound);
		this.add(soundSetting);
		
	}
	@Override
	public void update(Observable observable, Object data) 
	{
		GameWorld gw = (GameWorld) observable;
		
		boolean sound = gw.getSound();
		livesLeft.setText(""+Integer.toString(gw.getLives()));
		elapsedTime.setText(""+Integer.toString(gw.getTime() / 1000));
		lastFlagReached.setText(""+Integer.toString(gw.Ant().getLastFlagReached()));
		foodLevel.setText(""+Integer.toString(gw.Ant().getFoodLevel()));
		healthLevel.setText(""+Integer.toString(gw.Ant().getHealthLevel()));
		
		if(sound == true)
		{
			soundSetting.setText("ON");
		}
		else
		{
			soundSetting.setText("OFF");
		}
		
		revalidate();
	}

}
