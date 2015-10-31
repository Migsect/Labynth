package net.samongi.Labynth.Input;

import net.samongi.Labynth.Application;

public class InputManager
{
  private final Application app;
  
  public final KeyHandler key_handler;
  public final MouseHandler mouse_handler;
  
  public InputManager(Application app)
  {
    this.app = app; 
    
    Application.log("  Creating Key Handler");
    this.key_handler = new KeyHandler(this, app.getEventHandler());
    app.addKeyListener(key_handler);
    
    Application.log("  Creating Key Handler");
    this.mouse_handler = new MouseHandler(this, app.getEventHandler());
    app.addMouseListener(mouse_handler);
  }
  
  public void process(int ticks)
  {
    this.key_handler.poll(ticks);
    this.mouse_handler.poll(ticks);
  }
  
  public void updateProcess()
  {
    this.key_handler.updatePoll();
  }
}
