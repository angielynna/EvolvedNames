//package EvolvedNames;
/**
  * TCSS 342
  * Instructor: Paulo Barretto
  * Assignment 2 - Population.java
  */

import java.util.*;

/**
 * Maintains a list of Genomes representing the current population
 * initialize the population with a fixed number of default Genomes. 
 * update the list of Genomes every breeding cycle by: 
 * removing the least-fit members of the population. 
 * mutating or breeding the most-fit members of the population. 
 * display the entire population. 
 * display the most-fit individual in the population 
 * 
 * @author Angelynna Pyeatt and Amtoj Kaur
 * @version 22 April 2022
 */

public class Population {
	
	/** holds constant new random value*/
	public static final Random RAND = new Random();
	
	/** holds most fit genome*/
	private Genome mostFit;
	
	/** holds arraylist of genomes*/
	private List<Genome> genomes;
	
	/**
	 * constructor
	 * 
	 * @param numGenomes - int
	 * @param mutationRate - double
	 */
	public Population(Integer numGenomes, Double mutationRate) {
		genomes = new ArrayList<Genome>(); 
		for (int i = 0; i < numGenomes; i++) {
			 Genome genome = new Genome(mutationRate);
			 genomes.add(genome);
		 }
		 
		 mostFit = genomes.get(0);
	}
	
	/**
	 * default constructor
	 */
	public Population() {
		genomes = new ArrayList<Genome>();
	}
	
	/**
	 * updates mostFit, deletes the least fit, create new genomes from 
	 * the remaining (most-fit) population
	 */
	public void day() {	
		setMostFit();
		delete();	
		create();	
	}
	
	/**
	 * update mostFit variable to the most-fit Genome in the population. 
	 * (Remember this is the genome with the lowest fitness!) 
	 */
	private void setMostFit() {
		if (mostFit == null) {
			mostFit = genomes.get(0);
		}
		
		Collections.sort(genomes, new Genome());
		mostFit = genomes.get(0);
	}
	
	/**
	 * returns most fit genome
	 * 
	 * @return this.mostFit - Genome
	 */
	public Genome getMostFit() {
		return this.mostFit;
	}
	
	/**
	 * delete the least-fit half of the population
	 */
	private void delete() {
		genomes = genomes.subList(0, genomes.size() / 2);
	}
	
	private void create() {
		double rDub = Math.random();
		List<Genome> newGen = new ArrayList<Genome>();
		
		for (int i = 0; i < genomes.size(); i++) {	
			
			int randInt = RAND.nextInt(genomes.size());
			Genome randGenome = genomes.get(randInt);
			Genome clone = new Genome(randGenome);
			
			if (rDub <= .5) {
				clone.mutate();
				newGen.add(clone);
			} else {
				int randInt2 = RAND.nextInt(genomes.size());
				Genome randGenome2 = genomes.get(randInt2);
				clone.crossover(randGenome2);
				clone.mutate();
				newGen.add(clone);
			}
		}
				
		genomes.addAll(newGen);
	}
	
	//for testing
	/**
	 * adds genomes to arraylist genome
	 * 
	 * @param g - Genome
	 */
	public void add(Genome g) {
		genomes.add(g);
	}
	
	/**
	 * returns String representation of genomes
	 * 
	 * @return genomes.toString() - String
	 */
	public String toString() {
		return genomes.toString();
	}
}
