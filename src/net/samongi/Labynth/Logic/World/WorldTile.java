package net.samongi.Labynth.Logic.World;

import java.awt.Image;

import javax.swing.ImageIcon;

import net.samongi.Labynth.Logic.Collision.Hitbox;
import net.samongi.Labynth.Logic.Shapes.Vector2D;

public class WorldTile
{
  public static final int PUXEL_H = 16;
  public static final int PIXEL_W = 16;
  
  private Image image;
  private final char console_rep;
  
  private Hitbox hitbox;
  
  private final double graphic_start_x;
  private final double graphic_start_y;
  
  public WorldTile(String image_path)
  {
    ImageIcon img_icon = new ImageIcon(image_path);
    this.image = img_icon.getImage();
    this.console_rep = '0';
    
    graphic_start_x = 0;
    graphic_start_y = 0;
  }
  public WorldTile(String image_path, char console_rep)
  {
    ImageIcon img_icon = new ImageIcon(image_path);
    this.image = img_icon.getImage();
    this.console_rep = console_rep;
    
    graphic_start_x = 0;
    graphic_start_y = 0;
  }
  public WorldTile(Image image)
  {
    this.image = image;
    this.console_rep = '0';
    
    graphic_start_x = 0;
    graphic_start_y = 0;
  }
  public WorldTile(Image image, char console_rep)
  {
    this.image = image;
    this.console_rep = console_rep;
    
    graphic_start_x = 0;
    graphic_start_y = 0;
  }
  
  public Image getImage(){return this.image;}
  public Hitbox getHitbox(){return this.hitbox;}
  public Vector2D getGraphicsStart(){return Vector2D.create(graphic_start_x, graphic_start_y);}
  public char getConsoleRepresentation(){return this.console_rep;}
}