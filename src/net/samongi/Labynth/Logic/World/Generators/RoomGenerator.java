package net.samongi.Labynth.Logic.World.Generators;

import java.util.Random;

import net.samongi.Labynth.Logic.World.WorldArea;
import net.samongi.Labynth.Logic.World.WorldTile;

public class RoomGenerator extends AreaGenerator
{
  
  private final int x_dim;
  private final int y_dim;
  
  private final int x_var;
  private final int y_var;
  
  public RoomGenerator(long seed, int x_dim, int y_dim, int x_var, int y_var)
  {
    super(seed);
    
    this.x_dim = x_dim;
    this.y_dim = y_dim;
    
    this.x_var = x_var;
    this.y_var = y_var;
  }

  @Override
  public WorldArea generate()
  {
    int x_size = this.getSize(x_dim, x_var);
    int y_size = this.getSize(y_dim, y_var);
    
    System.out.println("Generating Room of size: [" + x_size + " , " + y_size  + "]");
    
    WorldArea area = new WorldArea();
    for(int x = 0 ; x < x_size ; x++) for(int y = 0 ; y < y_size ; y++)
    {
      WorldTile tile = null;
      if(x == 0 || x == x_size - 1 || y == 0 || y == y_size - 1) tile = new WorldTile("assets/tiles/test_wall_tile.png");
      else tile = new WorldTile("assets/tiles/test_tile.png", '_');
      
      //System.out.println("Setting tile to: " + "[" + x + "," + y + "]");
      area.setTile(x, y, tile);
      //System.out.println("  Tile Character: " + tile.getConsoleRepresentation());
    }

    area.printConsole();
    
    return area;
  }
  
  private int getSize(int dim, int var)
  {
    Random rand = this.getRandom();
    int c_var = rand.nextInt(2 * var + 1);
    return dim - var + c_var;
  }

}
