package com.mycompany.a3;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.CheckBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.Toolbar;
import java.lang.String;
import com.codename1.ui.util.UITimer;



public class Game extends Form implements Runnable
{
	private GameWorld gw;
	private MapView mv;
	private ScoreView sv;
	private static String gameTitle = "FlagbyFlag Game"; 
	private UITimer timer;
	private int schedule = 20;
	private boolean pauseState = false;
	private ButtonDesign accButton;
	private ButtonDesign brakeButton;
	private ButtonDesign leftButton;
	private ButtonDesign rightButton;
	private ButtonDesign pauseButton;
	private ButtonDesign positionButton;
	private AccCommand accelerate;
	private BrakeCommand brake;
	private LeftCommand left;
	private RightCommand right;
	private PauseCommand pause; 
	private PositionCommand position;
	private AboutCommand about;
	private ExitCommand exit;
	private CheckBox soundOn;
	private SoundCommand sound;
	private HelpCommand help;
	
	public Game() 
	{ 
		this.setLayout(new BorderLayout());
		this.setScrollable(false);

		gw = new GameWorld(); 								// create Observable GameWorld 
		mv = new MapView(); 								// create an Observer for the map
		sv = new ScoreView(); 								// create an Observer for the game/ant state data 		
		
		gw.addObserver(mv); 								// register the map observer 
		gw.addObserver(sv); 								// register the score observer
		
		
		setup();											// code here to create Command objects for each command, 
															// add commands to side menu and title bar area, bind commands to keys, create 
															// control containers for the buttons, add buttons to the control containers, 
															// add commands to the buttons, and add control containers, MapView, and 
															// ScoreView to the form 
			
		this.addComponent(BorderLayout.CENTER, mv);
		this.addComponent(BorderLayout.NORTH, sv); 
		
		show(); 
		
		gw.setHeight(mv.getHeight());
		gw.setWidth(mv.getWidth());
		
		System.out.println("Height = " + gw.getHeight());	// code here to query MapViews width and height and set them as worlds 
		 
		System.out.println("Width = " + gw.getWidth());		// width and height 
		
		gw.init(); 											// initialize world 
		gw.createSounds();									// Instantiates sound objects
		
		revalidate();
		
		timer = new UITimer(this);
		timer.schedule(schedule, true, this);
		
		
		
	
	}

	public void setup() {
		
		//Establishing our ToolBar and Containers for Buttons
		Toolbar menu = new Toolbar();
		this.setToolbar(menu);
		menu.setTitle(gameTitle);
		
		Container westContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		westContainer.setScrollableY(false);
		
		westContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
		
		Container eastContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
		eastContainer.setScrollableY(false);
		
		eastContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
	
		Container botContainer = new Container(new FlowLayout(Component.CENTER));
		botContainer.setScrollableX(false);
		
		botContainer.getAllStyles().setBorder(Border.createLineBorder(2, ColorUtil.BLACK));
	
		//Establishing our Buttons that go into ToolBar/Containers
		accelerate = new AccCommand(gw);
		accButton = new ButtonDesign(accelerate);
		addKeyListener('a', accelerate);
		westContainer.add(accButton);
		
		menu.addCommandToSideMenu(accelerate);
		
		brake = new BrakeCommand(gw);
		brakeButton = new ButtonDesign(brake);
		addKeyListener('b', brake);
		eastContainer.add(brakeButton);
		
		left = new LeftCommand(gw);
		leftButton = new ButtonDesign(left);
		addKeyListener('l', left);
		westContainer.add(leftButton);
		
		right = new RightCommand(gw);
		rightButton = new ButtonDesign(right);
		addKeyListener('r', right);
		eastContainer.add(rightButton);
		
		pause = new PauseCommand(this);
		pauseButton = new ButtonDesign(pause);
		addKeyListener('p', pause);
		botContainer.add(pauseButton);
		
		position = new PositionCommand(gw);
		positionButton = new ButtonDesign(position);
		botContainer.add(positionButton);
		positionButton.setEnabled(!positionButton.isEnabled());
		
		exit = new ExitCommand();
		menu.addCommandToSideMenu(exit);
		
		about = new AboutCommand();
		menu.addCommandToSideMenu(about);
		
		soundOn = new CheckBox("Sound");
		soundOn.getAllStyles().setBgTransparency(255);
		soundOn.getAllStyles().setBgColor(ColorUtil.LTGRAY);
		
		sound = new SoundCommand(gw,soundOn);
		soundOn.setCommand(sound);
		menu.addComponentToSideMenu(soundOn);
		
		help = new HelpCommand();
		menu.addCommandToRightBar(help);
		
		//Adding our Components to the BorderLayout
		this.addComponent(BorderLayout.WEST, westContainer);
		this.addComponent(BorderLayout.EAST, eastContainer);
		this.addComponent(BorderLayout.SOUTH, botContainer);
	}

	@Override
	public void run() 
	{
		gw.tick(schedule);
		
	}

	public void pauseGame() 
	{
		this.pauseState  = !pauseState;
		
		accButton.setEnabled(!accButton.isEnabled());
		brakeButton.setEnabled(!brakeButton.isEnabled());
		leftButton.setEnabled(!leftButton.isEnabled());
		rightButton.setEnabled(!rightButton.isEnabled());
		accelerate.setEnabled(!accelerate.isEnabled());
		
		if(pauseState)
		{
			removeKeyListener('a', accelerate);
			removeKeyListener('b', brake);
			removeKeyListener('l', left);
			removeKeyListener('r', right);
			positionButton.setEnabled(!positionButton.isEnabled());
			
			timer.cancel();
			pauseButton.setText("Play");
			
		}
		else
		{
			timer.schedule(schedule, true, this);
			pauseButton.setText("Pause");
			positionButton.setEnabled(!positionButton.isEnabled());
			addKeyListener('a', accelerate);
			addKeyListener('b', brake);
			addKeyListener('l', left);
			addKeyListener('r', right);

		}
	}
}
