//package EvolvedNames;
/**
  * TCSS 342
  * Instructor: Paulo Barretto
  * Assignment 2 - Genome.java
  */

import java.util.*;

/**
 * Genome implements Comparable and mutates a string, crossover with 
 * another genome, measure genome fitness
 * 
 * @author Angelynna Pyeatt and Amtoj Kaur
 * @version 22 April 2022
 */

public class Genome implements Comparator<Genome> {
	/**holds constant alphabet*/
	public static final char[] ALPHABET = {'A', 'B', 'C', 'D', 'E', 'F',
						'G', 'H', 'I', 'J', 'K', 'L',
						'M', 'N', 'O', 'P', 'Q', 'R',
						'S', 'T', 'U', 'V', 'W', 'X',
						'Y', 'Z', ' ', '-', '\''};
	
	/** holds random constant variable*/
	public static Random R;
	
	/** holds String target*/
	private static final String TARGET = "PAULO SERGIO LICCIARDI MESSEDER"
										+ " BARRETO";;
	
	/** holds private StringBuilder which holds the current name*/
	private StringBuilder sbName;
	
	/** holds mutation rate*/
	private double mutationRate;
	
	/*
	 * default constructor
	 */
	public Genome() {
		R = new Random();
		sbName = new StringBuilder();
		sbName.append('A');
		mutationRate = 0.05;
	}
	
	/**
	 * constructor
	 * 
	 * @param mutationRate
	 */
	public Genome(double mutationRate) {
		R = new Random();
		sbName = new StringBuilder();
		sbName.append('A');
		this.mutationRate = mutationRate;
	}
	
	/**
	 * constructor 
	 * 
	 * @param gene - Genome
	 */
	public Genome(Genome gene) {
		R = new Random();
		sbName = new StringBuilder(gene.sbName.toString());
		this.mutationRate = gene.mutationRate;
	}
	
	/**
	 * Constructor used for testing
	 * 
	 * @param name - String
	 * @param mutationRate - double
	 */
	public Genome(String name, double mutationRate) {
		R = new Random();
		sbName = new StringBuilder();
		
		for (int i = 0; i < name.length(); i++) {
			char ch = name.charAt(i);
			sbName.append(ch);
		}
		
		this.mutationRate = mutationRate;
	}
	
	/**
	 * this function mutates the string in this Genome using the 
	 * following rules:
	 * 1. with mutationRate chance add a randomly selected character
	 *  to a randomly selected position in the string.
	 *  2. with mutationRate chance  delete a single character from a
	 *   randomly selected position of the string but do this only if
	 *   the string has length at least 2. 
	 *  3. for each character in the string: with mutationRate chance
	 *  the character is replaced by a randomly selected character. 
	 */
	public void mutate() {
		double rNum = Math.random();	// 0 - 1
		
		//add
		if (rNum < mutationRate) {
			int rInt = R.nextInt(ALPHABET.length);
			char rChar = ALPHABET[rInt];
				
			rInt = R.nextInt(sbName.length() + 1);
			sbName.insert(rInt, rChar);	
		}
		
		rNum = Math.random();
		
		//delete
		if (rNum < mutationRate) {
			
			if (sbName.length() >= 2) {
				int rInt = R.nextInt(sbName.length());
				sbName.deleteCharAt(rInt);
			}
		}
		
		//replace
		for (int i = 0; i < sbName.length(); i++) {
			
			rNum = Math.random();
			
			if (rNum < mutationRate) {
				int rInt = R.nextInt(ALPHABET.length);
				char rChar = ALPHABET[rInt];
				sbName.setCharAt(i, rChar);
			}
		}
	}
	
	/**
	 * this function will update the current Genome by crossing 
	 * it over with other.
	 * 
	 * @param other - Genome
	 */
	public void crossover(Genome other) {
		Genome parent;
		Genome L = this;
		StringBuilder sb = new StringBuilder();
		
		if (sbName.length() < other.sbName.length()) {
			L = other;
		}
		
		for (int i = 0; i < L.sbName.length(); i++) {
			
			double r_d = Math.random();
			
			if (r_d <= .5) {
				parent = other;
			} else {
				parent = this;
			}
				
			if (parent.sbName.length() > i) {
				sb.append(parent.sbName.charAt(i));
			} else {
				break;
			}	
		}
		
		sbName = sb;
	}
	
	/**
	 * returns the fitness of the Genome calculated using the following 
	 * algorithm: 
	 * Let n be the length of the current string. 
	 * Let m be the length of the target string. 
	 * Let L be the L(n, m). 
	 * Let f be initialized to |m – n|. 
	 * For each character position 0≤i<L add one to f if the character in 
	 * the current string is different from the character in the target 
	 * string (or if no character exists at that position in either 
	 * string). Otherwise leave f unchanged. 
	 * 
	 * @return f - Integer
	 */
	public Integer fitness() {
		int n = sbName.length();
		int m = TARGET.length();
		
		int L = Math.max(n, m);
		int f = Math.abs(m - n);
		
		if (m > n) {
			if (!sbName.toString().equals(TARGET)) {
				
				for (int i = 0; i < L; i++) {
					char charTarget = TARGET.charAt(i);
					int charTargetIndex = sbName.toString().indexOf(charTarget, i);
					
					if (charTargetIndex != i) {
						f++;
					}
				}
			}
		} else {
			if (!TARGET.equals(sbName.toString())) {
				
				for (int i = 0; i < L; i++) {
					char nT = sbName.charAt(i);
					int nTInd = TARGET.indexOf(nT, i);
					
					if (nTInd != i) {
						f++;
					}
				}
			}
		}
		
		
		return f;
	}
	
	/**
	 * compare compares two genomes
	 * 
	 * @param g1 - Genome
	 * @param g2 - Genome
	 * @return g1.fitness() - g2.fitness() - int
	 */
	@Override
	public int compare(Genome g1, Genome g2) {
		return g1.fitness() - g2.fitness();
	}
	
	/**
	 * returns to String representation of Genome
	 * 
	 * @return sb.toString() - String
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(\"");
		sb.append(sbName.toString());
		sb.append("\", ");
		sb.append(fitness());
		sb.append(")");
		return sb.toString();
	}
}
