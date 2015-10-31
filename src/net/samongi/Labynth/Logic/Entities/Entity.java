package net.samongi.Labynth.Logic.Entities;

import net.samongi.Labynth.Logic.GameRunner;
import net.samongi.Labynth.Logic.Collision.Hitbox;
import net.samongi.Labynth.Logic.Shapes.Vector2D;

public class Entity
{
  // Location vector, in units
  private double location_x;
  private double location_y;
  
  // Velocity vector, in units/sec
  private double vel_x = 0;
  private double vel_y = 0;
  
  // Acceleration vector, in units/sec/sec
  private double accel_x = 0;
  private double accel_y = 0;
  
  // Hitbox
  private Hitbox hitbox;
  
  // Entity size scale
  private double scale = 1.0;
  
  public double getLocX(){return this.location_x;}
  public double getLocY(){return this.location_y;}
  public Vector2D getLoc(){return Vector2D.create(location_x, location_y);}
  
  public double getVelX(){return this.vel_x;}
  public double getVelY(){return this.vel_y;}
  public Vector2D getVel(){return Vector2D.create(vel_x, vel_y);}
  
  public double getAccelX(){return this.accel_x;}
  public double getAccelY(){return this.accel_y;}
  public Vector2D getAccel(){return Vector2D.create(accel_x, accel_y);}
  
  public void setLocX(double x){this.location_x = x;}
  public void setLocY(double y){this.location_y = y;}
  public void setLoc(Vector2D v)
  {
    this.location_x = v.getX();
    this.location_y = v.getY();
  }
  public void addLocX(double x){this.location_x += x;}
  public void addLocY(double y){this.location_y += y;}
  public void addLoc(Vector2D v)
  {
    this.location_x += v.getX();
    this.location_y += v.getY();
  }
  
  public void setVelX(double x){this.vel_x = x;}
  public void setVelY(double y){this.vel_y = y;}
  public void setVel(Vector2D v)
  {
    this.vel_x = v.getX();
    this.vel_y = v.getY();
  }
  public void addVelX(double x){this.vel_x += x;}
  public void addVelY(double y){this.vel_y += y;}
  public void addVel(Vector2D v)
  {
    this.vel_x += v.getX();
    this.vel_y += v.getY();
  }
  
  public void setAccelX(double x){this.accel_x = x;}
  public void setAccelY(double y){this.accel_y = y;}
  public void setAccel(Vector2D v)
  {
    this.accel_x = v.getX();
    this.accel_y = v.getY();
  }
  public void addAccelX(double x){this.accel_x += x;}
  public void addAccelY(double y){this.accel_y += y;}
  public void addAccel(Vector2D v)
  {
    this.accel_x += v.getX();
    this.accel_y += v.getY();
  }
  
  public Hitbox getHitbox(){return this.hitbox;}
  public void setHitbox(Hitbox hitbox){this.hitbox = hitbox;}
  
  public double getScale(){return this.scale;}
  public void setScale(double scale){this.scale = scale;}
  
  public void update(long update_ms)
  {
    Vector2D old_location = this.getLoc();
    this.vel_x += (this.accel_x * GameRunner.MS_PER_SEC / update_ms);
    this.vel_y += (this.accel_y * GameRunner.MS_PER_SEC / update_ms);
    Vector2D movement = this.getVel();
    this.location_x += (this.vel_y * GameRunner.MS_PER_SEC / update_ms);
    this.location_y += (this.vel_y * GameRunner.MS_PER_SEC / update_ms);
    Vector2D new_location = this.getLoc();
    
    // TODO collision detection and resolution
  }
}
