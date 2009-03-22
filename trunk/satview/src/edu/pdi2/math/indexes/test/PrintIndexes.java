package edu.pdi2.math.indexes.test;

import edu.pdi2.math.indexes.Rayleigh.L5Rayleigh;
import edu.pdi2.math.indexes.Rayleigh.Rayleigh;

public class PrintIndexes {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Rayleigh rayleigh = new L5Rayleigh();
		System.out.println(rayleigh.printIndexes());

	}

}
