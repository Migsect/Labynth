package net.samongi.Labynth.Listener.Events;

public interface Cancelable
{
  public boolean isCanceled();
  public void setCanceled(boolean cancel);
  public default void cancel(){this.setCanceled(true);}
  public default void uncancel(){this.setCanceled(false);}
}
