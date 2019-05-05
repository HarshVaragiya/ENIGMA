package Mechanics;

public class Reflector {
	
	private int lookup_table_size = 26;
	
	private int lookup_table[] = new int[this.lookup_table_size];
	
	private boolean debug = false;
	
	public Reflector(int configuration,boolean debug) {
		
		/*
		 *  http://users.telenet.be/d.rijmenants/en/enigmatech.htm
		
		 *  Contacts    = ABCDEFGHIJKLMNOPQRSTUVWXYZ                
	     *                ||||||||||||||||||||||||||
		 *  Reflector B = YRUHQSLDPXNGOKMIEBFZCWVJAT
		 *  Reflector C = FVPJIAOYEDRZXWGCTKUQSBNMHL 
		 *  
		 *  Lookup table : 
		 *  
		 *  Reflector B =  [24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19]
         *  Reflector C =  [5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11]
		 */
	
		if(configuration == 1) {
			this.lookup_table = new int[] {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
		}
		else if(configuration == 2) {
			this.lookup_table = new int[] {5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};
			
		}
		
	}
	
	
	public int lookup(int input) {
		int output = lookup_table[input];
		if(this.debug)System.out.println(input + " <--->  " + output);
		return output;
	}

}
