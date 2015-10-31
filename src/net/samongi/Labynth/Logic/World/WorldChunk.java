package net.samongi.Labynth.Logic.World;

public class WorldChunk
{
  private final int width;
  private final int height;
  
  private final WorldTile[][] tiles;
  
  
  public WorldChunk()
  {
    this.width = WorldArea.DIM_W;
    this.height = WorldArea.DIM_H;
    this.tiles = new WorldTile[this.width][this.height];
  }
  public WorldChunk(int width, int height)
  {
    this.width = width;
    this.height = height;
    this.tiles = new WorldTile[this.width][this.height];
  }
  public int getWidth(){return this.width;}
  public int getHeight(){return this.height;}
  
  public WorldTile getTile(int index_w, int index_h)
  {
    if(index_w >= this.width || index_h >= this.height) return null;
    if(index_w < 0 || index_h < 0) return null;
    return this.tiles[index_w][index_h];
  }
  public boolean setTile(int index_w, int index_h, WorldTile tile)
  {
    if(index_w >= this.width || index_h >= this.height) return false;
    if(index_w < 0 || index_h < 0) return false;
    this.tiles[index_w][index_h] = tile;
    return true;
  }
}
