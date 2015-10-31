package net.samongi.Labynth.Logic.Shapes;

import java.util.ArrayList;
import java.util.List;

public class Polygon implements Shape
{
  private Vector2D reference_point;
  private List<Vector2D> points = new ArrayList<Vector2D>();
  
  public Polygon(Vector2D first_point){this.reference_point = first_point;}
  
  public void addPoint(Vector2D point){this.points.add(point.makeRelative(reference_point));}

  @Override
  public Shape scale(double ratio)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Shape normalize()
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Shape setRotation(double radians)
  {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public double getRotation()
  {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public Shape addRotation(double radians)
  {
    // TODO Auto-generated method stub
    return null;
  }


}
