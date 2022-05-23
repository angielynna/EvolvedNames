//package EvolvedNames;
/**
  * TCSS 342
  * Instructor: Paulo Barretto
  * Assignment 2 - Main.java
  */

/**
 * Main class is the driver class and will instantiate population class,
 * call day() from the Population class until the fitness of the most fit 
 * genome is zero, output simulation progress, and output runtime.
 * 
 * @author Angelynna Pyeatt and Amtoj Kaur
 * @version 22 April 2022
 */
public class Main {
	
	/** holds constant int value for genomes*/
	public static final int NUMGENOMES = 100;
	
	/**holds constant double value for mutation rate*/
	public static final double MUTATION = 0.05;
	
	/**
	 * Instantiate population class, call day(), output simulation and 
	 * runtime
	 * 
	 * @param theArgs - command line arguments
	 */
	public static void main(String[] theArgs) {
		Population pop = new Population(NUMGENOMES, MUTATION);
		
		long start = System.currentTimeMillis();   
		int generation = 0;
		
		while (pop.getMostFit().fitness() != 0) {
			pop.day();
			System.out.println(pop.getMostFit());
			generation++;
		}
		
		System.out.println("Generations: " + generation);
		
		long end = System.currentTimeMillis();
		
		System.out.println("Running time: " + (end-start) 
							+ " milliseconds");
		
		//testGenome();
		//testPopulation();
		
	}
	/**
	 * test function for Genome class
	 */
	public static void testGenome() {
		Genome g2 = new Genome("AAA", MUTATION);
		Genome g3 = new Genome("BBBBB", MUTATION);
		g2.crossover(g3);
		System.out.println(g2);
		
		Genome g4 = new Genome("AAAA", MUTATION);
		Genome g5 = new Genome("BBB", MUTATION);
		System.out.println(g4.fitness());
		System.out.println(g5.fitness());
		
		Genome g6 = new Genome(MUTATION);
		g6.mutate();
		System.out.println(g6);
		
		Genome g7 = new Genome(g2);
		System.out.println(g7);
	}
	
	/**
	 * test function for population class
	 */
	public static void testPopulation() {
		Genome g2 = new Genome("AAA", MUTATION);
		Genome g3 = new Genome("XYZ", MUTATION);
		Genome g4 = new Genome("ABA", MUTATION);
		Genome g5 = new Genome("BBBB", MUTATION);
		Genome g6 = new Genome("BBBBB", MUTATION);
		
		Population pop = new Population();
		pop.add(g2);
		pop.add(g3);
		pop.add(g4);
		pop.add(g5);
		pop.add(g6);
		pop.day();
		
		System.out.println(pop);
	}
	
	
}