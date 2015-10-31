package net.samongi.Labynth.Logic.Shapes;

public interface Shape
{
  /**Will scale the Shape up or down based on the ratio.
   * This is relational to the current dimensions of the Shape.
   * 
   * @param ratio
   */
  public Shape scale(double ratio);
  /**Normalize will scale the Shape down to a normalized size, this varies based on type of Shape
   */
  public Shape normalize();

  public Shape setRotation(double radians);
  public double getRotation();
  public Shape addRotation(double radians);
  
  class Circle implements Shape
  {
    private final double radius;
    private final double rotation;
    
    private Circle(double radius)
    {
      this.radius = radius;
      this.rotation = 0;
    }
    private Circle(double radius, double rotation)
    {
      this.radius = radius;
      this.rotation = rotation;
    }
    
    @Override
    public Shape scale(double ratio){return new Circle(radius * ratio);}

    @Override
    public Shape normalize(){return new Circle(0.5);}

    @Override
    public Shape setRotation(double radians){return new Circle(radius, radians);}

    @Override
    public double getRotation(){return this.rotation;}

    @Override
    public Shape addRotation(double radians){return new Circle(radius, this.rotation + radians);}
    
    public double getRadius(){return this.radius;}
    public Shape setRadius(double radius){return new Circle(radius, this.rotation);}
    
  }
}
