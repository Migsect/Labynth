package net.samongi.Labynth.Listener.Events.Input;

public class KeyHeldEvent extends KeyEvent
{
  private int held_ticks;
  
  public KeyHeldEvent(int key_code, boolean is_pressed, int held_ticks)
  {
    super(key_code, is_pressed);
    this.held_ticks = held_ticks;
  }

  public int getTicks(){return this.held_ticks;}
  
}
