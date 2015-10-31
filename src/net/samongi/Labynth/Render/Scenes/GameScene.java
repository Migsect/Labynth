package net.samongi.Labynth.Render.Scenes;

import java.awt.Graphics2D;

import javax.swing.JPanel;

import net.samongi.Labynth.Application;
import net.samongi.Labynth.Logic.Entities.Entity;
import net.samongi.Labynth.Logic.GameManager.GameManager;
import net.samongi.Labynth.Render.Scene;
import net.samongi.Labynth.Render.SceneLayer;

public class GameScene extends Scene
{ 
  
  public GameScene(Application app)
  {
    this.createWorldLayer(app.getGameManager());
    this.createEffectLayer();
    this.createEntityLayer();
    this.createParticleLayer();
    this.createLightLayer();
    this.createHUDLayer();
    this.createGUILayer();
  }
  
  private void createGUILayer()
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        //TODO
      }
    });
  }
  private void createHUDLayer()
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        //TODO
      }
    });
  }
  private void createLightLayer()
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        //TODO
      }
    });
  }
  private void createParticleLayer()
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        //TODO
      }
    });
  }
  private void createEntityLayer()
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        //TODO
      }
    });
  }
  private void createEffectLayer()
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        //TODO
      }
    });
  }
  private void createWorldLayer(GameManager game_manager)
  {
    this.addLayer(new SceneLayer()
    {
      @Override
      public void paint(Graphics2D g, JPanel p, double frame_ahead)
      {
        Entity focus = game_manager.getWorldFocus();
        game_manager.getUniverseManager().getLoaded().getLoaded().getLoaded().render(g, p, focus.getLocX(), focus.getLocY(), 1 / focus.getScale());
      }
    });
  }
  
}
