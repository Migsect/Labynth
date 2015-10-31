package net.samongi.Labynth.Input;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import net.samongi.Labynth.Listener.Events.EventManager;
import net.samongi.Labynth.Listener.Events.Input.KeyHeldEvent;
import net.samongi.Labynth.Listener.Events.Input.KeyPressedEvent;
import net.samongi.Labynth.Listener.Events.Input.KeyReleasedEvent;

public class KeyHandler extends KeyAdapter
{
  private static final int KEY_COUNT = 256;
  
  private final InputManager manager;
  private final EventManager event_manager;
  
  // Used to indicate if the key is current being pressed down.
  private boolean[] is_pressed = null;
 
  // Used to track the number of times the key was pressed.
  private int[] times_pressed = null;
  private int[] times_released = null;
  
  // A variable to determine the tick length that a key is held for;
  private int[] hold_ticks = null;
  
  /**Initialized both the arrays
   * Sets all the current key states to released
   */
  public KeyHandler(InputManager manager, EventManager event_manager)
  {
    this.manager = manager;
    this.event_manager = event_manager;
    
    // is_pressed init
    this.is_pressed = new boolean[KEY_COUNT];
    for(int i = 0 ; i < KEY_COUNT ; i++) is_pressed[i] = false;
    
    // times_pressed and times_released init
    this.times_pressed = new int[KEY_COUNT];
    for(int i = 0 ; i < KEY_COUNT ; i++) times_pressed[i] = 0;
    this.times_released = new int[KEY_COUNT];
    for(int i = 0 ; i < KEY_COUNT ; i++) times_released[i] = 0;
    
    // hold_ticks init
    this.hold_ticks = new int[KEY_COUNT];
    for(int i = 0 ; i < KEY_COUNT ; i++) hold_ticks[i] = 0;
  }
  
  /**Will poll the current keys and set the key states.
   * 
   */
  public synchronized void poll(int ticks)
  {
    for(int i = 0 ; i < KEY_COUNT ; i++)
    {
      // Updating the number of ticks the key was held for
      if(this.is_pressed[i]) this.hold_ticks[i] += ticks;
      else this.hold_ticks[i] = 0;
      
      // Generating press events
      for(int c = 0 ; c < this.times_pressed[i] ; c++)
      {
        KeyPressedEvent event = new KeyPressedEvent(i, this.is_pressed[i]);
        this.event_manager.queueEvent(event);
      }
      
      // Generating release events
      for(int c = 0 ; c < this.times_released[i] ; c++)
      {
        KeyReleasedEvent event = new KeyReleasedEvent(i, this.is_pressed[i]);
        this.event_manager.queueEvent(event);
      }
    }
  }
  
  public synchronized void updatePoll()
  {
    for(int i = 0 ; i < KEY_COUNT ; i++)
    {
      if(this.hold_ticks[i] > 0)
      {
        KeyHeldEvent event = new KeyHeldEvent(i, this.is_pressed[i], this.hold_ticks[i]);
        this.event_manager.queueEvent(event);
      }
    }
  }
  
  @Override
  public synchronized void keyPressed(KeyEvent e)
  {
    int key_code = e.getKeyCode();
    this.is_pressed[key_code] = true;
    this.times_pressed[key_code]++;
  }
  @Override 
  public synchronized void keyReleased(KeyEvent e)
  {
    int key_code = e.getKeyCode();
    this.is_pressed[key_code] = false;
    this.times_released[key_code]++;
    this.hold_ticks[key_code] = 0;
  }
  
}
