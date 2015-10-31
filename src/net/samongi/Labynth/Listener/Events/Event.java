package net.samongi.Labynth.Listener.Events;

public abstract class Event
{
  
  public String getEventName(){return this.getClass().getSimpleName();}
  
}
