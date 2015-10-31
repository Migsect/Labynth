package net.samongi.Labynth.Listener.Events;

public interface Catchable
{
  /**Sets the capture flag on the event to indicate that the event has been visited at least once and was handled.
   * 
   * @param capture True if you wish to set the capture flag on the event
   */
  public void setCaptured(boolean capture);
  
  /**Returns the capture state of the event
   * 
   * @return True if the event was captured
   */
  public boolean wasCaught();
}
