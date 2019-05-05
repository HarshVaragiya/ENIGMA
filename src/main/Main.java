package main;

import Mechanics.ENIGMA;

public class Main {

	public static void main(String[] args) {
		
		int rotors[]    = {1,2,3};
		
		int positions[] = {10,10,10};
		
		int reflector   = 2;
		
		boolean debug = false;
		
		ENIGMA X = new ENIGMA(rotors,positions,reflector,debug);
		
		String input = new String("NRFBBPVKSZ");
		
		System.out.println("\nCipher String   : " + input);
		
		String output = X.cipher(input);
		
		System.out.println("Ciphered Output : " + output);

	}

}
