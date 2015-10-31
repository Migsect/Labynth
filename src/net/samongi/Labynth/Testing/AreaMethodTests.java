package net.samongi.Labynth.Testing;
import static org.junit.Assert.*;

import java.util.Random;

import junit.framework.Assert;
import net.samongi.Labynth.Logic.World.WorldArea;
import net.samongi.Labynth.Logic.World.WorldChunk;
import net.samongi.Labynth.Logic.World.WorldTile;
import net.samongi.Labynth.Logic.World.Generators.AreaGenerator;

import org.junit.Test;


public class AreaMethodTests
{
  
  @Test
  public void testGetChunkNonExisting()
  {
    WorldArea test_area = new WorldArea();
    WorldChunk chunk = test_area.getChunk(0, 0);
    if(chunk == null) fail("Returned null Chunk");
    if(test_area.countChunks() != 1) fail("Chunk Number is not 1!");
  }
  
  @Test
  public void testChunkExists()
  {
    WorldArea test_area = new WorldArea();
    WorldChunk chunk = test_area.getChunk(0, 0);
    if(chunk == null) fail("Returned null Chunk");
    if(test_area.chunkExists(0, 0) == false) fail("Chunk exists did not return true!");
  }
  
  @Test
  public void testRandomChunkInsertion()
  {
    final int variation = 10;
    final int amount = 10;
    WorldArea test_area = new WorldArea();
    Random rand = new Random();
    for(int i = 0 ; i < amount ; i++)
    {
      int x = rand.nextInt(2 * variation + 1) - variation;
      int y = rand.nextInt(2 * variation + 1) - variation;
      
      if(test_area.chunkExists(x, y)) continue;
      test_area.setChunk(x, y, new WorldChunk());
    }
    if(test_area.countChunks() != amount) fail("Area Chunk amount is " + test_area.countChunks() + " and not " + amount + ".");
  }
  
  @Test
  public void testChunkDimensions()
  {
    WorldArea test_area = new WorldArea();
    WorldChunk chunk = test_area.getChunk(0, 0);
    if(chunk == null) fail("Returned null Chunk");
    if(chunk.getHeight() != WorldArea.DIM_H) fail("Height did not match standard");
    if(chunk.getWidth() != WorldArea.DIM_W) fail("Width did not match standard");
  }
  
  @Test
  public void testChunkTileSettingValidate()
  {
    WorldChunk chunk = new WorldChunk();
    WorldTile new_tile = new WorldTile("--Not An Image--");
    boolean status = chunk.setTile(0, 0, new_tile);
    if(status == false) fail("Setting tile failed.");
    WorldTile tile = chunk.getTile(0, 0);
    if(tile != new_tile) fail("Tile received not the same as set tile.");
  }
  
  @Test
  public void testChunkTileSettingStatusPassing()
  {
    WorldChunk chunk = new WorldChunk();
    WorldTile new_tile = new WorldTile("--Not An Image--");
    for(int y = 0 ; y < WorldArea.DIM_H ; y++) for(int x = 0 ; x < WorldArea.DIM_W ; x++)
    {
      boolean status = chunk.setTile(y, x, new_tile);
      if(status == false) fail("Setting tile as [" + x + "," + y + "] should have returned true");
    }
  }
  
  @Test
  public void testChunkTileSettingStatusFailing()
  {
    WorldChunk chunk = new WorldChunk();
    WorldTile new_tile = new WorldTile("--Not An Image--");
    for(int y = -1 ; y < WorldArea.DIM_H + 1 ; y++) for(int x = -1 ; x < WorldArea.DIM_W + 1 ; x++)
    {
      if(x > 0 || x < WorldArea.DIM_W) continue;
      if(y > 0 || y < WorldArea.DIM_H) continue;
      boolean status = chunk.setTile(y, x, new_tile);
      if(status == true) fail("Setting tile as [" + x + "," + y + "] should have returned false");
    }
  }
  
  @Test
  public void testCreateChunk()
  {
    WorldArea test_area = new WorldArea();
    
    WorldChunk chunk = test_area.getChunk(0, 0);
    if(chunk == null) fail("Returned null Chunk");
    
    WorldChunk new_chunk = new WorldChunk();
    test_area.setChunk(0, 0, new_chunk);
    
    chunk = test_area.getChunk(0, 0);
    if(chunk == null) fail("Returned null Chunk");
  }

}
