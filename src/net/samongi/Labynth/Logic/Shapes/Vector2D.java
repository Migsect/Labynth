package net.samongi.Labynth.Logic.Shapes;

public interface Vector2D
{
  public static Vector2D create(double x, double y){return new StaticVector2D(x, y);}
  public static Vector2D createRelative(Vector2D vector, double x, double y){return new RelativeVector2D(vector, x, y);}
  
  public double getX();
  public double getY();
  
  public default double lengthSquared(){return Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2);}
  public default double length(){return Math.sqrt(this.lengthSquared());}
  
  public default double angle(){return Math.cos(this.getX() / this.length());}
  public default double angle(Vector2D other){return Math.cos(this.dot(other) / (this.length() * other.length()));}
  
  public default double dot(Vector2D other){return this.getX() * other.getX() + this.getY() * other.getY();}
  
  public Vector2D add(Vector2D other);
  public default Vector2D subtract(Vector2D other){return this.add(other.scale(-1));}
  public Vector2D rotate(double radians);
  public Vector2D scale(double scalar);
  
  public default Vector2D makeStatic(){return new StaticVector2D(this.getX(), this.getY());}
  public default Vector2D makeRelative(Vector2D relative){return new RelativeVector2D(relative, this.getX(), this.getY());}
  
  public class StaticVector2D implements Vector2D
  {
    private final double x;
    private final double y;
    
    StaticVector2D(double x, double y)
    {
      this.x = x;
      this.y = y;
    }
    
    @Override
    public double getX(){return this.x;}
    @Override
    public double getY(){return this.y;}

    @Override
    public Vector2D add(Vector2D other){return new StaticVector2D(this.x + other.getX(), this.y + other.getY());}
    @Override
    public Vector2D rotate(double radians)
    {
      double sin = Math.sin(radians);
      double cos = Math.cos(radians);
      double n_x = this.x * cos - this.y * sin;
      double n_y = this.x * sin + this.y * cos;
      return new StaticVector2D(n_x, n_y);
    }
    @Override
    public Vector2D scale(double scalar){return new StaticVector2D(this.x * scalar, this.y * scalar);}
  }
  
  public class RelativeVector2D implements Vector2D
  {
    private final Vector2D relative_to;
    
    private final double rel_x;
    private final double rel_y;
    
    RelativeVector2D(Vector2D relative_to, double rel_x, double rel_y)
    {
      this.relative_to = relative_to;
      this.rel_x = rel_x;
      this.rel_y = rel_y;
    }
    
    public Vector2D getRelativeTo(){return this.relative_to;}
    
    @Override
    public double getX(){return this.getX() + this.rel_x;}
    @Override
    public double getY(){return this.getY() + this.rel_y;}

    @Override
    public Vector2D add(Vector2D other)
    {
      if(other instanceof RelativeVector2D) return this.add((RelativeVector2D)other);
      if(other instanceof StaticVector2D) return this.add((StaticVector2D)other);
      return null;
    }
    private Vector2D add(StaticVector2D other){return new RelativeVector2D(this.relative_to, this.rel_x + other.getX(), this.rel_y + other.getY());}
    private Vector2D add(RelativeVector2D other){return new RelativeVector2D(this.relative_to, this.rel_x + other.rel_x, this.rel_y + other.rel_y);}

    @Override
    public Vector2D rotate(double radians)
    {
      double sin = Math.sin(radians);
      double cos = Math.cos(radians);
      double n_x = this.rel_x * cos - this.rel_y * sin;
      double n_y = this.rel_x * sin + this.rel_y * cos;
      return new RelativeVector2D(this.relative_to, n_x, n_y);
    }

    @Override
    public Vector2D scale(double scalar){return new RelativeVector2D(this.relative_to, this.rel_x * scalar, this.rel_y * scalar);}
    
    public Vector2D updateRelative(Vector2D new_relative){return new RelativeVector2D(new_relative, this.rel_x, this.rel_y);}
  }
  
}
