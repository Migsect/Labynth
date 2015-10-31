package net.samongi.Labynth.Render;

import java.awt.Graphics2D;

import javax.swing.JPanel;

public interface SceneLayer
{
  public void paint(Graphics2D g, JPanel p, double frame_ahead);
}
