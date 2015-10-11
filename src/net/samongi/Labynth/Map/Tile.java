package net.samongi.Labynth.Map;

import java.awt.Graphics2D;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Tile
{
	// This is the image that represents the tile
	private Image image;
	// This is the tile's location
  private Location location;
  // This is the tile width in pixels
  private int tile_width = 16;
  // This is the tile height in pixels
  private int tile_height = 16;
  
  public Tile(String image_path, Location loc)
  {
  	ImageIcon img_icon = new ImageIcon(image_path);
  	this.image = img_icon.getImage();
  	
  	this.tile_height = img_icon.getIconHeight();
  	this.tile_width = img_icon.getIconWidth();
  	
  	this.location = loc;
  }
  
  public Location getLocation(){return this.location;}
  public int getWidth(){return this.tile_width;}
  public int getHeight(){return this.tile_height;}
  
  public void draw(Graphics2D g, JPanel panel, int center_x, int center_y)
  {
  	g.drawImage(image, location.getPixelX() - center_x, location.getPixelY() - center_y, panel);
  }
  
}
