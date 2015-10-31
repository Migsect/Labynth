package net.samongi.Labynth.Render;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public abstract class Scene
{
  private List<SceneLayer> layers = new ArrayList<>();
  
  public void render(Graphics g, JPanel p, double frame_ahead){for(SceneLayer layer : layers) layer.paint((Graphics2D)g, p, frame_ahead);}
  
  protected void addLayer(SceneLayer layer){this.layers.add(layer);}
  protected void addLayer(SceneLayer layer, int index){this.layers.add(index, layer);}
  
}
