package net.samongi.Labynth.Input;

import java.awt.event.MouseAdapter;

import net.samongi.Labynth.Listener.Events.EventManager;

public class MouseHandler extends MouseAdapter
{
  private final InputManager manager;
  private final EventManager event_manager;
  
  public MouseHandler(InputManager manager, EventManager event_manager)
  {
    this.manager = manager;
    this.event_manager = event_manager;
  }
  
  public synchronized void poll(int ticks)
  {
    
  }

}
