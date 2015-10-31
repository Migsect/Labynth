package net.samongi.Labynth.Render;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;

import javax.swing.JPanel;

import net.samongi.Labynth.Application;
import net.samongi.Labynth.Logic.World.WorldArea;
import net.samongi.Labynth.Logic.World.Generators.AreaGenerator;
import net.samongi.Labynth.Logic.World.Generators.RoomGenerator;

public class SceneManager extends JPanel
{
  private static final long serialVersionUID = 2446750846257125996L;
  
  private final Application app;
  
  private Scene current_scene;
  
  private double last_frames_ahead = 0;
  
  // TODO Remove this, testing
  WorldArea area;
  
  public SceneManager(Application app)
  {
    this.app = app;
    
    this.setFocusable(true);
    this.setBackground(Color.WHITE);
    
    this.setPreferredSize(new Dimension(app.INIT_WIDTH, app.INIT_HEIGHT));
    this.setDoubleBuffered(true);
    
    this.current_scene = null;
    
    AreaGenerator generator = new RoomGenerator(System.currentTimeMillis(), 20, 20, 5, 5);
    this.area = generator.generate();
  }
  
  public void render(double frames_ahead)
  {
    //System.out.println("Scenemanager : Render Called");
    this.last_frames_ahead = frames_ahead;
    this.repaint();
  }
  
  @Override
  public void paintComponent(Graphics g)
  {
    System.out.println("Scenemanager : paintComponent : Render Called");
    super.paintComponent(g);

    area.render((Graphics2D) g, this, 0, 0, 2);
    
    // Calling the rendered for the current scene
    if(this.current_scene != null) this.current_scene.render(g, this, last_frames_ahead);
    
    
    Toolkit.getDefaultToolkit().sync();
  }
}
