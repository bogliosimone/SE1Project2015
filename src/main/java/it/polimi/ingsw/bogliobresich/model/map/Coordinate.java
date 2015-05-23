/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * @author simoneboglio
 *
 */
public class Coordinate {
    private static int offsetLetterA = 65;
    private char letter;
    private int number;

    public Coordinate (char letter, int number){
        this.letter = letter;
        this.number = number;
    }

    public Coordinate (int x,int y){
        this.letter = (char) (x+offsetLetterA-1);
        this.number = y;
    }

    public char getLetter(){
        return this.letter;
    }

    public int getNumber(){
        return this.number;
    }


    public int getX(){
        return this.letter - offsetLetterA + 1;
    }

    public int getY(){
        return this.number;
    }

    public boolean equals(Coordinate cord){
        if(this.letter == cord.letter && this.number == cord.number )
            return true;
        return false;
    }

    public String toString(){
        return new String( "Hex: "+Character.toString(this.letter) + Integer.toString(this.number));
    }

}