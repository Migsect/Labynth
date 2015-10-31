package net.samongi.Labynth.Logic;

import net.samongi.Labynth.Application;

public class GameRunner implements Runnable
{
	public static final int TPS = 100;
  public static final int MS_PER_SEC = 1000;
  public static final int MS_PER_UPDATE = MS_PER_SEC / TPS;
  public static final int MAX_LOOPS = 10;
	
	@SuppressWarnings("unused")
  private double fps = 0;

  private Application app;
	
	public GameRunner(Application app)
	{
	  this.app = app;
	}
	
	/**Perform an input processing
	 *   Event creation for inputs will be created here.
	 */
	private void process(int ticks)
	{
	  this.app.getInputManager().process(ticks);
	}
	
	/**A tic of game logic
	 * 
	 */
	private void update(long update_ms)
	{
	  this.app.getInputManager().updateProcess();
		this.app.getGameManager().update(update_ms);
	}
	
	/**Renders the Game View
	 * 
	 */
	private void render(double frames_ahead)
	{
	  this.app.getSceneManager().render(frames_ahead);
	}
	
  @Override
	public void run()
	{
    long previous_time = System.currentTimeMillis();
    long lag = 0;
    int loops = 0;
    
    boolean do_loop = true;
    while(do_loop)
    {
      long current_time = System.currentTimeMillis();
      long elapsed_time = current_time - previous_time;
      previous_time = current_time;
      // Adding the elapsed time as lag we need to catchup for
      lag += elapsed_time;
      
      // Processing the Inputs
      this.process(loops);
      
      loops = 0;
      while(lag >= MS_PER_UPDATE && loops < MAX_LOOPS)
      {
        // Updating
        this.update(MS_PER_UPDATE);
        lag -= MS_PER_UPDATE;
        loops++;
      }
      
      // Updating the fps
      if(elapsed_time > 0) fps = 1000 / elapsed_time;
      
      // Rendering
      this.render(lag);
      // Application.log("Game Loop - " + loops + " loops - " + lag + " lag");
    }
	}

}
