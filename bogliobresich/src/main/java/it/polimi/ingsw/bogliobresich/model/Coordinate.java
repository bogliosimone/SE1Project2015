/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model;

/**
 * @author simoneboglio
 *
 */
public class Coordinate {
	private char letter;
	private int number;
	
	public Coordinate (char letter, int number){
		this.letter = letter;
		this.number = number;
	}
	
	public char getLetter(){
		return this.letter;
	}
	
	public int getNumber(){
		return this.number;
	}
	
	public boolean equals(Coordinate cord){
		if(this.letter == cord.letter && this.number == cord.number )
			return true;
		return false;
	}
	
	public String toString(){
		return new String( Character.toString(this.letter) + Integer.toString(this.number));
	}
	
}
