package net.samongi.Labynth.Logic.World.Generators;

import java.util.Random;

import net.samongi.Labynth.Logic.World.WorldArea;

public abstract class AreaGenerator
{
  private Random rand_gen;
  private final long seed;
  
  /**Constructs an Area Generator
   * 
   * @param seed The seed for the random number generator
   */
  public AreaGenerator(long seed)
  {
    this.rand_gen = new Random(seed);
    this.seed = seed;
  }
  
  /**Calls implementation that generates an area.  
   * This will continue generating new and distinct areas unless
   * the resetRandom method is called.  
   * 
   * @return A WorldArea object
   */
  public abstract WorldArea generate();
  
  /**Resets the random number generator to where it was when it 
   */
  public void resetRandom(){this.rand_gen = new Random(seed);}
  /**Returns the random number generator object.
   * 
   * @return A random number generator
   */
  public Random getRandom(){return this.rand_gen;}
  /**Gets the seed of the random number generator.
   * 
   * @return The seed used for the random number generator.
   */
  public long getSeed(){return this.seed;}
}
