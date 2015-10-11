package net.samongi.Labynth.Screens;

import javax.swing.JFrame;

import net.samongi.Labynth.Application.Board.Board;

public class Application extends JFrame
{
	private static final long serialVersionUID = -8571219961645888155L;

	public Application() 
  {
  	this.initUI();
  }
  
  private void initUI() 
  {
    this.add(new Board());
    
    this.setSize(800, 600);
    
    this.setTitle("Application");
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLocationRelativeTo(null);
  }
}
