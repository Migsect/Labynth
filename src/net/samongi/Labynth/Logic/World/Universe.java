package net.samongi.Labynth.Logic.World;

import java.util.HashMap;
import java.util.Map;

public class Universe
{
  Map<String, World> worlds = new HashMap<String, World>();
  World loaded_world;
  
  public World getLoaded(){return this.loaded_world;}
  public World getWorld(String name){return this.worlds.get(name);}
  public boolean worldExists(String name){return this.worlds.containsKey(name);}
}
