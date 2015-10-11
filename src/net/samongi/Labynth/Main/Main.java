package net.samongi.Labynth.Main;

import java.awt.EventQueue;

import net.samongi.Labynth.Screens.Application;

/**Main class for the game
 * 
 * @author Alex
 *
 */
public class Main
{
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
}
