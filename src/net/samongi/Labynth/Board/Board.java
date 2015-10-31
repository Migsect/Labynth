package net.samongi.Labynth.Board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import net.samongi.Labynth.Map.Map;

public class Board extends JPanel implements Runnable
{
	private static final long serialVersionUID = 8090325089586943953L;
	
	private final int B_WIDTH = 800;
  private final int B_HEIGHT = 800;
  private final int DELAY = 25;

	private Thread animator;
	private MovableObject movable;
	private Map map;
  
	public Board() 
	{
		this.initBoard();
		
		animator = new Thread(this);
		animator.start();
	}
	
	private void initBoard()
	{
		this.addKeyListener(new TAdaptor());
		this.setFocusable(true);
		this.setBackground(Color.WHITE);

		this.map = new Map(200, 200);
		this.movable = new MovableObject(map);
		
		this.setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
		this.setDoubleBuffered(true);
		
		
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		
		this.doDrawing(g);
		
	  Toolkit.getDefaultToolkit().sync();
	}

	private void doDrawing(Graphics g)
	{
		// Converting the graphics into a 2d grapgics
	  Graphics2D g2d = (Graphics2D) g;
	  
		// Getting the window height and width
		JFrame window = (JFrame) SwingUtilities.getWindowAncestor(this);
		Rectangle bounds = window.getBounds();
		int height = bounds.height;
		int width = bounds.width;

		int center_x = width / 2;
    int center_y = height / 2;
		
    // Getting the player location
    int player_x = movable.getLocation().getPixelX();
    int player_y = movable.getLocation().getPixelY();
    
	  // Drawing the map image.
		map.draw(g2d, this, center_x + player_x, center_y + player_y);
		
		// Drawing the player image
	  g2d.drawImage(movable.getImage(), center_x - 20, center_y - 20, this);
	  
	}
	
	private void cycle()
	{
		this.movable.updateLocation();
		this.movable.updatePhysics();
	}
	
	@Override
	public void run()
	{
		long before_time;
		long time_diff;
		long sleep;
		
		before_time = System.currentTimeMillis();
		
		while(true)
		{
			
			this.cycle();
			this.repaint();
			
			time_diff = System.currentTimeMillis() - before_time;
			
			sleep = DELAY - time_diff;
			if(sleep < 0) sleep = 2;
		  
			try 
			{
				Thread.sleep(sleep);
			}
			catch (InterruptedException e)
			{
				System.out.println("Interrupted: " + e.getMessage());
			}
			
			before_time = System.currentTimeMillis();
		}
		
	}
	
	private class TAdaptor extends KeyAdapter
	{
		@Override
		public void keyPressed(KeyEvent e)
		{
			movable.onKeyPressed(e);
		}
		@Override
		public void keyReleased(KeyEvent e)
		{
			movable.onKeyReleased(e);
		}
	}

}
