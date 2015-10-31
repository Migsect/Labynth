package net.samongi.Labynth.Listener.Events.Input;

import net.samongi.Labynth.Listener.Events.Catchable;
import net.samongi.Labynth.Listener.Events.Event;

public abstract class KeyEvent extends Event implements Catchable
{
  private int key_code;
  
  private boolean is_pressed;
  
  private boolean caught;
  
  public KeyEvent(int key_code, boolean is_pressed)
  {
    this.key_code = key_code;
    this.is_pressed = is_pressed;
  }
  
  public int getKeyCode(){return this.key_code;}
  public char getKeyChar(){return (char)this.key_code;}
  
  public boolean isCurrentlyPressed(){return this.is_pressed;}

  @Override
  public void setCaptured(boolean capture){this.caught = capture;}

  @Override
  public boolean wasCaught(){return this.caught;}
}
