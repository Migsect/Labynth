package net.samongi.Labynth.Board;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

import net.samongi.Labynth.Map.Location;
import net.samongi.Labynth.Map.Map;

public class MovableObject
{
  private double x_vel;
  private double y_vel;
  
  Location location;
  
  private Image image;
  
  public MovableObject(Map map)
  {
  	ImageIcon img_icon = new ImageIcon("assets/characters/TestObject_small.png");
  	image = img_icon.getImage();
  	
  	this.location = new Location(map, 0, 0);
  }
  
  public void updateLocation()
  {
  	this.location = new Location(this.location.getMap(), this.location.getX() + x_vel, this.location.getY() + y_vel);
  }
  
  public void updatePhysics()
  {
  	double friction = 0.01;
  	
  	if(x_vel > friction) x_vel -= friction;
  	if(x_vel < -friction) x_vel += friction;
  	if(x_vel < friction && x_vel > 0) x_vel = 0;
  	if(x_vel > -friction && x_vel < 0) x_vel = 0;
  	
  	if(y_vel > friction) y_vel -= friction;
  	if(y_vel < -friction) y_vel += friction;
  	if(y_vel < friction && y_vel > 0) y_vel = 0;
  	if(y_vel > -friction && y_vel < 0) y_vel = 0;
  }
  
  public Location getLocation(){return this.location;}
  
  public Image getImage(){return this.image;}
  
  public void onKeyPressed(KeyEvent e)
  {
  	// Note, 0,0 is top right window corner
  	// down is positive
  	// right is positive
  	double accel_amnt = 0.1;
  	double max_vel = 2;
  	double slow_down_multiplier = 10;
  	switch (e.getKeyCode())
  	{
	    case KeyEvent.VK_LEFT:
	    	if(x_vel > 0) x_vel += slow_down_multiplier*(-accel_amnt);
	    	else x_vel += -accel_amnt;
	    	if(x_vel < -max_vel) x_vel = -6;
	      break;
	    case KeyEvent.VK_RIGHT:
	    	if(x_vel < 0) x_vel += slow_down_multiplier*(accel_amnt);
	    	else x_vel += accel_amnt;
	    	if(x_vel > max_vel) x_vel = 6;
	      break;
	    case KeyEvent.VK_UP:
	    	if(y_vel > 0) y_vel += slow_down_multiplier*(-accel_amnt);
	    	else y_vel += -accel_amnt;
	    	if(y_vel < -max_vel) y_vel = -6;
	      break;
	    case KeyEvent.VK_DOWN:
	    	if(y_vel > 0) y_vel += slow_down_multiplier*(accel_amnt);
	    	else y_vel += accel_amnt;
	    	if(y_vel > max_vel) y_vel = 6;
	      break;
  	}
  	
  }
  
  public void onKeyReleased(KeyEvent e)
  {
  	/*
  	switch (e.getKeyCode())
  	{
	    case KeyEvent.VK_LEFT:
	    	dx = 0;
	      break;
	    case KeyEvent.VK_RIGHT:
	    	dx = 0;
	      break;
	    case KeyEvent.VK_UP:
	    	dy = 0;
	      break;
	    case KeyEvent.VK_DOWN:
	    	dy = 0;
	      break;
  	}
  	*/
  }
  
}
