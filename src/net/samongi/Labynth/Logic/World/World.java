package net.samongi.Labynth.Logic.World;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class World
{
  private Map<String, File> area_files = new HashMap<>();
  private WorldArea loaded_area;
  
  public WorldArea getLoaded(){return this.loaded_area;}
  
}
