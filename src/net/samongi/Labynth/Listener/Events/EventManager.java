package net.samongi.Labynth.Listener.Events;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import net.samongi.Labynth.Application;

public class EventManager
{
  private final Application app;
  
  private Queue<Event> event_queue = null;
  
  private Map<Class<? extends Event>, Map<EventPriority, Set<EventListener>>> handlers = null;
  
  public EventManager(Application app)
  {
    this.app = app;
    
    this.event_queue = new LinkedList<Event>();
    this.handlers = new HashMap<>();
  }
  
  public enum EventPriority
  {
    HIGHEST,
    HIGH,
    NORMAL,
    LOW,
    LOWEST;
  }
  
  /**Will register the listener with the event
   * 
   * @param listener The listener object to be registering
   * @param event The event to register the executor to
   * @param priority The priority of the event's execution (higher values get executed later. Priority is double between 0 and 1)
   */
  public void registerListener(EventListener listener, Class<? extends Event> event, EventPriority priority)
  {
    if(!handlers.containsKey(event)) handlers.put(event, new HashMap<EventPriority, Set<EventListener>>());
    Map<EventPriority, Set<EventListener>> priority_mapping = handlers.get(event);
    
    if(!priority_mapping.containsKey(priority)) priority_mapping.put(priority, new HashSet<EventListener>());
    Set<EventListener> handler_set = priority_mapping.get(priority);
    
    handler_set.add(listener);
  }
  
  public void queueEvent(Event event)
  {
    event_queue.add(event);
  }
  
  public void emptyEventQueue()
  {
    while(event_queue.size() > 0)
    {
      Event event = event_queue.peek();
      event_queue.remove();
    }
  }
  
  public void callEvent(Event event)
  {
    Class<? extends Event> event_class = event.getClass();
    Map<EventPriority, Set<EventListener>> priority_mapping = handlers.get(event_class);
    for(EventPriority p : EventPriority.values())
    {
      Set<EventListener> event_listeners = priority_mapping.get(p);
      if(event_listeners == null) continue;
      for(EventListener e : event_listeners) e.listen(event);
    }
    
  }
  
 
}
