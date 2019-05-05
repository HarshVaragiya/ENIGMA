package Mechanics;

import java.util.ArrayList;

public class ENIGMA {
	
	private ArrayList<Rotor> Rotors = new ArrayList<Rotor>();
	private Reflector Ref;
	private boolean debug = false;
	
	public ENIGMA(int rotors[],int positions[],int reflector,boolean debug) {
		this.debug = debug;
		for(int i=0;i<rotors.length;i++) {
			if(this.debug)System.out.println("Rotor " + i + "\n -> RotorCode : " + rotors[i] + " -> Rotor Position : " + positions[i]);
			Rotor new_rotor = new Rotor(rotors[i],positions[i],this.debug);
			this.Rotors.add(new_rotor);
		}
		
		this.Ref = new Reflector(reflector,this.debug);
	}
	
	public void setPositions(int positions[]) {
		for(int i=0;i<this.Rotors.size();i++) {
			this.Rotors.get(i).setPosition(positions[i]);
		}
	}
	
	public int[] getPositions() {
		int positions[] = new int[this.Rotors.size()];
		for(int i=0;i<this.Rotors.size();i++) {
			positions[i] = this.Rotors.get(i).getPosition();
		}
		return positions;
	}

	public String cipher(String text) {
		char in_chars[]  = text.toCharArray();
		int input_int[]  = new int[in_chars.length];
		int output_int[] = new int[in_chars.length];
		char out_chars[] = new char[in_chars.length];
		
		for(int i=0;i<in_chars.length;i++) {
			input_int[i] = (int)in_chars[i] - 65;
		}
		
		output_int = this.performLookup(input_int);
		
		for(int i=0;i<in_chars.length;i++) {
			int num = output_int[i] + 65;
			char ch = (char)num;
			out_chars[i] = ch;
		}
		
		return new String(out_chars);
	}

	private int[] performLookup(int[] input) {
		int out[] = new int[input.length];
		for(int i=0;i<input.length;i++) {
			out[i] = this.lookup(input[i]);
		}
		return out;
	}
	
	private int lookup(int in) {
		int out = in;
		boolean forward_click = true;
		
		for(int i=0;i<this.Rotors.size();i++) {
			out = Rotors.get(i).lookup(out);
		}
		
		out = Ref.lookup(out);
		
		for(int i=this.Rotors.size()-1;i>=0;i--) {
			out = Rotors.get(i).reverse_lookup(out);
		}
		
		int selected_rotor = 0x00;
		
		while(forward_click && selected_rotor < this.Rotors.size()) {
			forward_click = this.Rotors.get(selected_rotor).click();			
			selected_rotor +=1;
		}
		
		if(this.debug)System.out.print("Position : " + this.Rotors.get(0).getPosition() + "  ");
		if(this.debug)System.out.println(this.Rotors.get(1).getPosition());
		
		return out;
	}
	
}
