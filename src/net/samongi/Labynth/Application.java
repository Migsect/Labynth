package net.samongi.Labynth;

import java.awt.EventQueue;
import java.util.logging.Logger;

import javax.swing.JFrame;

import net.samongi.Labynth.Input.InputManager;
import net.samongi.Labynth.Listener.Events.EventManager;
import net.samongi.Labynth.Logic.GameRunner;
import net.samongi.Labynth.Logic.GameManager.GameManager;
import net.samongi.Labynth.Render.SceneManager;

public class Application extends JFrame
{
	private static final long serialVersionUID = -8571219961645888155L;
	
	public static void log(String message)
	{
	  System.out.println(message);
	}
	
	public final int INIT_WIDTH = 800;
	public final int INIT_HEIGHT = 600;
	
	private final InputManager input_manager;
	private final GameManager game_manager;
	private final SceneManager scene_manager;
	private final EventManager event_handler;
	
	private Thread loop_thread;
	
	// Starting up the application
	public static void main(String[] args)
  {
    EventQueue.invokeLater(new Runnable(){
      @Override
      public void run()
      {
        Application app = new Application();
        app.setVisible(true);
      }
    });
  }
	
	/**Constructs an application object used to represent the whole of the application
	 *   the game is running as.
	 */
	public Application() 
  {
	  // Creating the game stage for displaying everything
    this.setSize(INIT_WIDTH, INIT_HEIGHT);
    
    Application.log("Creating Input Manager");
    this.input_manager = new InputManager(this);
    Application.log("Creating Game Manager");
    this.game_manager = new GameManager(this);
    Application.log("Creating SceneManager");
    this.scene_manager = new SceneManager(this);
    this.add(scene_manager);
    Application.log("Creating Event Handler");
    this.event_handler = new EventManager(this);
    
    this.setTitle("Labynth");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
    
    // Starting the game loop thread
    Application.log("Starting Game Loop");
    GameRunner runner = new GameRunner(this);
    this.loop_thread = new Thread(runner);
    this.loop_thread.start();
  }
	
	public InputManager getInputManager(){return this.input_manager;}
	public GameManager getGameManager(){return this.game_manager;}
	public SceneManager getSceneManager(){return this.scene_manager;}
	public EventManager getEventHandler(){return this.event_handler;}
}
