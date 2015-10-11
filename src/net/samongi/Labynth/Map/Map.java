package net.samongi.Labynth.Map;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Map
{
	// Height x Width in terms of tiles
	private final int width;
	private final int height;
	
	// Array of tiles
	private List<Tile> tiles = new ArrayList<Tile>();
	
	// The pixel ratio is the amount of pixels per unit that the max is working at 
	//  The default is 16 pixels per unit.
	private double pixel_ratio = 16;
	
  public Map(int width, int height)
  {
  	this.width = width;
  	this.height = height;
  	
  	this.generateTiles();
  }
  
  private void generateTiles()
  {
  	// Generate the wall tiles
  	//System.out.println("***Generating Tiles:");
  	for(int x = 0; x < width; x++) { for(int y = 0; y < height; y++)
  	{
  		Location location = new Location(this, x, y);
  		//System.out.println("***  Location: " + location.toString());
  		if(x == 0 || x == width -1 || y == 0 || y == width - 1)
  		{
  			Tile new_tile = new Tile("assets/tiles/test_wall_tile.png", location);
  			tiles.add(new_tile);
  		}
  		else
  		{
  			Tile new_tile = new Tile("assets/tiles/test_tile.png", location);
  			tiles.add(new_tile);
  		}
  	}}
  	//System.out.println("***Generated " + tiles.size() + " tiles.");
  	//for(Tile t : this.tiles) System.out.println("***  Tile Location: " + t.getLocation().toString());
  }
  
  public double getPixelRatio(){return this.pixel_ratio;}
  
  public void draw(Graphics2D g, JPanel panel, int center_x, int center_y)
  {
  	//System.out.println("***Drawing " + tiles.size() + " tiles.");
  	for(Tile t : this.tiles) t.draw(g, panel, center_x, center_y);
  }
}
