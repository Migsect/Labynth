package net.samongi.Labynth.Map;

/**A Location represents a location on a Map.
 *   Locations are based on the maps units and not on the pixel count.
 * 
 * @author Alex
 *
 */
public class Location
{
  private Map map;
  private double x_loc;
  private double y_loc;
  
  public Location(Map map, double x, double y)
  {
  	this.map = map;
  	this.x_loc = x;
  	this.y_loc = y;
  }
  
  public Map getMap(){return this.map;}
  public double getX(){return this.x_loc;}
  public double getY(){return this.y_loc;}
  
  public int getPixelX(){return (int) Math.round(this.x_loc * map.getPixelRatio());}
  public int getPixelY(){return (int) Math.round(this.y_loc * map.getPixelRatio());}
  
  public String toString(){return "[ " + this.x_loc + ", " + this.y_loc + " ]";}
}
