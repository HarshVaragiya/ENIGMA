package Mechanics;

public class Rotor {
	
	private int lookup_table_size = 26;
	
	private int position;
	
	private int lookup_table[] = new int[this.lookup_table_size];
	
	private boolean debug = false;
	
	public Rotor(int conf, int init_pos,boolean debug) {
		setRotor(conf,init_pos,debug);
	}

	public void setRotor(int configuration,int initial_position,boolean debug) {
		this.debug = debug;
		
		/*
		 * http://users.telenet.be/d.rijmenants/en/enigmatech.htm
		    Entry = ABCDEFGHIJKLMNOPQRSTUVWXYZ (rotor right side)   
			        ||||||||||||||||||||||||||
			I     = EKMFLGDQVZNTOWYHXUSPAIBRCJ
			II    = AJDKSIRUXBLHWTMCQGZNPYFVOE
			III   = BDFHJLCPRTXVZNYEIWGAKMUSQO
			IV    = ESOVPZJAYQUIRHXLNFTGKDCMWB
			V     = VZBRGITYUPSDNHLXAWMJQOFECK 
			Lookup Table depending on configurations: 
			I     = [4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9]
			II    = [0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4]
			III   = [1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14]
			IV    = [4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1]
			V     = [21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10]
		 */
		
		this.setPosition(initial_position);
		if(configuration == 1) {
			this.lookup_table = new int[] {4, 10, 12, 5, 11, 6, 3, 16, 21, 25, 13, 19, 14, 22, 24, 7, 23, 20, 18, 15, 0, 8, 1, 17, 2, 9};
		}
		else if(configuration == 2) {
			this.lookup_table = new int[] {0, 9, 3, 10, 18, 8, 17, 20, 23, 1, 11, 7, 22, 19, 12, 2, 16, 6, 25, 13, 15, 24, 5, 21, 14, 4};
		}
		else if(configuration == 3) {
			this.lookup_table = new int[] {1, 3, 5, 7, 9, 11, 2, 15, 17, 19, 23, 21, 25, 13, 24, 4, 8, 22, 6, 0, 10, 12, 20, 18, 16, 14};
		}
		else if(configuration == 4) {
			this.lookup_table = new int[] {4, 18, 14, 21, 15, 25, 9, 0, 24, 16, 20, 8, 17, 7, 23, 11, 13, 5, 19, 6, 10, 3, 2, 12, 22, 1};
		}
		else if(configuration == 5) {
			this.lookup_table = new int[] {21, 25, 1, 17, 6, 8, 19, 24, 20, 15, 18, 3, 13, 7, 11, 23, 0, 22, 12, 9, 16, 14, 5, 4, 2, 10};
		}
		
	}
	
	public void setPosition(int pos) {
		this.position = pos;
	}
	
	public int getPosition() {
		return this.position;
	}
	
	public boolean click() {
		int new_pos = this.position +1;
		boolean rot = false;
		if(new_pos >= this.lookup_table_size) {
			new_pos %= this.lookup_table_size;
			rot = true;
		}
		this.position = new_pos;
		return rot;
	}	
	
	public int lookup(int input) {
		int output = lookup_table[(input + this.position) % this.lookup_table_size];
		if(this.debug)System.out.println(input + " -> " + output);
		return output;
		
	}
	
	public int reverse_lookup(int input) {
		int output = 0x00;
		for(int i=0;i<this.lookup_table_size;i++) {
			if(lookup_table[(i + this.position) % this.lookup_table_size] == input)output = i;
		}
		if(this.debug)System.out.println(input + " -> " + output);
		return output;
	}
		
}
