package net.samongi.Labynth.Logic.World;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class WorldArea
{
  public static final int DIM_W = 8;
  public static final int DIM_H = 8;
  
  // Mapss x, y cordinates to a world chunk
  private Map<Integer, Map<Integer, WorldChunk>> chunks = new HashMap<>();
  private int max_chunk_x = 0;
  private int min_chunk_x = 0;
  private int max_chunk_y = 0;
  private int min_chunk_y = 0;
  
  public WorldTile getTile(int x, int y){return this.getTileChunk(x, y).getTile(x % DIM_W, y % DIM_H);}
  public void setTile(int x, int y, WorldTile tile){this.getTileChunk(x, y).setTile(x % DIM_W, y % DIM_H, tile);}
  
  public int countChunks()
  {
    int sum = 0;
    for(int i : chunks.keySet()) sum += chunks.get(i).size();
    return sum;
  }
  /**Checks to see if the chunk at the specified chunk slot exists
   * 
   * @param x X dimension of the chunk probe
   * @param y Y dimension of the chunk probe
   * @return True if the chunk exists at the location
   */
  public boolean chunkExists(int x, int y)
  {
    if(!chunks.containsKey(x)) return false;
    if(!chunks.get(x).containsKey(y)) return false;
    return true;
  }
  
  public WorldChunk getChunk(int chunk_x, int chunk_y)
  {
    if(!chunks.containsKey(chunk_x)) chunks.put(chunk_x, new HashMap<Integer, WorldChunk>());
    Map<Integer, WorldChunk> chunks_y = chunks.get(chunk_x);
    if(!chunks_y.containsKey(chunk_y)) chunks_y.put(chunk_y, new WorldChunk());
    WorldChunk chunk = chunks_y.get(chunk_y);
    
    this.updateMinMax(chunk_x, chunk_y);
    
    return chunk;
  }
  public void setChunk(int chunk_x, int chunk_y, WorldChunk chunk)
  {
    if(!chunks.containsKey(chunk_x)) chunks.put(chunk_x, new HashMap<Integer, WorldChunk>());
    Map<Integer, WorldChunk> chunks_y = chunks.get(chunk_x);
    chunks_y.put(chunk_y, chunk);
    
    this.updateMinMax(chunk_x, chunk_y);
  }
  private void updateMinMax(int chunk_x, int chunk_y)
  {
    if(chunk_x > max_chunk_x) max_chunk_x = chunk_x;
    if(chunk_x < min_chunk_x) max_chunk_x = chunk_x;
    if(chunk_y > max_chunk_y) max_chunk_y = chunk_y;
    if(chunk_y < min_chunk_y) min_chunk_y = chunk_y;
  }
  
  public WorldChunk getTileChunk(int tile_x, int tile_y){return this.getChunk(tile_x / DIM_W, tile_y / DIM_H);}
  public void setTileChunk(int tile_x, int tile_y, WorldChunk chunk){this.setChunk(tile_x / DIM_W, tile_y / DIM_H, chunk);}
  
  /**Renders the area to the passed in graphic
   * 
   * @param graphic The graphic that will be drawn to
   * @param screen_x The width of the screen
   * @param screen_y The height of the screen
   * @param center_x The x location in the area to be rendered as "center"
   * @param center_y The y location in the area to be rendered as "center"
   * @param scale The scale to render at (1.0 will do a normal size render)
   */
  public void render(Graphics2D graphic, JPanel panel, double center_x, double center_y, double scale)
  {
    System.out.println("Rendering a World Area");
    
    // Getting the window height and width
    JFrame window = (JFrame) SwingUtilities.getWindowAncestor(panel);
    Rectangle bounds = window.getBounds();
    int screen_h = bounds.height;
    int screen_w = bounds.width;
    
    // finding the mid point of the screen
    final int MID_SCREEN_W = screen_w / 2;
    final int MID_SCREEN_H = screen_h / 2;
        
    // scaling the pixel width of tiles
    final int TILE_PIXEL_W = (int) (scale * WorldTile.PIXEL_W);
    final int TILE_PIXEL_H = (int) (scale * WorldTile.PUXEL_H);
    
    // finding the center pixel for the world grids
    final int CENTER_PIXEL_X = (int) Math.round(center_x * TILE_PIXEL_W);
    final int CENTER_PIXEL_Y = (int) Math.round(center_y * TILE_PIXEL_H);
    
    final int PIXEL_SHIFT_X = MID_SCREEN_W - CENTER_PIXEL_X;
    final int PIXEL_SHIFT_Y = MID_SCREEN_H - CENTER_PIXEL_Y;
    
    // Calculating the number of tiles the screen resolution holds
    final int SCREEN_W_TILE = 1 + (int)Math.ceil(screen_w / (double)TILE_PIXEL_W);
    final int SCREEN_H_TILE = 1 + (int)Math.ceil(screen_h / (double)TILE_PIXEL_H);
    
    // Calculating the tile that would be in the center of the screen
    final int CENTER_TILE_X = (int)Math.floor(center_x);
    final int CENTER_TILE_Y = (int)Math.floor(center_y);
    
    // Getting the tile to start rendering at
    final int START_TILE_X = CENTER_TILE_X - SCREEN_W_TILE / 2;
    final int START_TILE_Y = CENTER_TILE_Y - SCREEN_H_TILE / 2;
    
    // Looping through all the tiles needed and pasting them.
    //  x, y represent current pixel locations.
    for(int x = START_TILE_X ; x <= START_TILE_X + SCREEN_W_TILE ; x++) 
    {
      for(int y = START_TILE_Y ; y <= CENTER_TILE_Y + SCREEN_H_TILE  ; y++) 
      {
        int render_x = TILE_PIXEL_W * x + PIXEL_SHIFT_X;
        int render_y = TILE_PIXEL_H * y + PIXEL_SHIFT_Y;
        
        System.out.println("Printing Tile [" + x + "," + y + "] to pixel coord: [" + render_x + "," + render_y + "]");
        
        WorldTile tile = this.getTile(x, y);
        if(tile == null) continue;
        else System.out.println("  Tile did not return null!");
        
        graphic.drawImage(tile.getImage(), render_x, render_y, TILE_PIXEL_W, TILE_PIXEL_H, panel);
      }
    }
  }
  public void printConsole()
  {
    // r indicates the room we will be printing each time.
    for(int c_y = min_chunk_y ; c_y <= max_chunk_y ; c_y++) 
    {
      for(int r = 0 ; r < DIM_H ; r++) 
      {
        String line = "";
        for(int c_x = min_chunk_x ; c_x <= max_chunk_x ; c_x++)  
        {
          // Getting the chunk
          WorldChunk chunk = chunks.get(c_x).get(c_y);
          //System.out.println("[" + c_x + "," + c_y + "]");
          if(chunk == null)
          {
            line += "`";
            continue;
          }
          for(int c = 0 ; c < DIM_W ; c++)
          {
            WorldTile tile = chunk.getTile(c, r);
            if(tile == null)
            {
              line += "`";
              continue;
            }
            line += tile.getConsoleRepresentation();
          }
          line += " ";
        }
        System.out.println(line);
      }
      System.out.println();
    }
  }
}
