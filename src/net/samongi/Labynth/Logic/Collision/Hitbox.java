package net.samongi.Labynth.Logic.Collision;

import net.samongi.Labynth.Logic.Shapes.Shape;

public interface Hitbox
{
  public static Hitbox getCircular(double radius)
  {
    return null;
  }
  public static Hitbox getSquare(double side_length, double radians)
  {
    return null;
  }
  public static Hitbox getRectangle(double width_height, double radians)
  {
    return null;
  }
  
  class CircularHitbox implements Hitbox
  {
    private Shape.Circle shape;
    
  }
}
