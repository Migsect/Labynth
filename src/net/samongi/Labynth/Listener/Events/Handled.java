package net.samongi.Labynth.Listener.Events;

public interface Handled
{
  public interface EventHandler
  {
    public void handle(Event e);
  }
  
  public EventHandler getHandler();
}
