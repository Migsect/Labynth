package net.samongi.Labynth.Application;

public class GameRunner implements Runnable
{
	private int delay = 25; // 25 seconds of delay between frames.
	
	
	/**A tic of game logic
	 * 
	 */
	private void logic()
	{
		
	}
	
	/**Renders the Game View
	 * 
	 */
	private void render()
	{
		
	}
	
  @Override
	public void run()
	{
		// The time that the thread started at.
		//  This is initialized to the current system time.
		long before_time = System.currentTimeMillis();
		// The amount of time all the operations the thread took to achieve.
		long time_diff; // This will be in terms of miliseconds
		// The time the thread needs to sleep.
		long sleep;
		
		boolean do_loop = true;
		while(do_loop)
		{
			this.logic();
			this.render();
			
			// Calculating the time it took to render and tic
			time_diff = System.currentTimeMillis() - before_time;
			
			// Calculating the time we want the thread to sleep for.
			sleep = delay - time_diff;
			
			// attempting to sleep
			try
			{
				Thread.sleep(sleep);
			}
			catch (InterruptedException e)
			{
				System.out.println("Interrupted: " + e.getMessage());
			}
			
			// Updating the before time.
			before_time = System.currentTimeMillis();
		}
		
	}

}
