package net.samongi.Labynth.Logic.GameManager;

import net.samongi.Labynth.Application;
import net.samongi.Labynth.Logic.Entities.Entity;
import net.samongi.Labynth.Logic.World.Universe;
import net.samongi.Labynth.Logic.World.UniverseManager;

public class GameManager
{
  private final Application app;
  
  private final UniverseManager uni_manager;
  
  private Entity world_focus;
  
  public GameManager(Application app)
  {
    this.app = app;
    this.uni_manager = new UniverseManager();
  }
  
  public void update(long update_ms)
  {
    
  }
  public UniverseManager getUniverseManager(){return this.uni_manager;}
  public Universe getUniverse(){return this.uni_manager.getLoaded();}
  public Entity getWorldFocus(){return this.world_focus;}
  public void setWorldFocus(Entity focus){this.world_focus = focus;}
}
