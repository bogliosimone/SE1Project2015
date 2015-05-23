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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + letter;
        result = prime * result + number;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Coordinate other = (Coordinate) obj;
        if (letter != other.letter || number != other.number)
            return false;
        return true;
    }

    @Override
    public String toString(){
        return new String( "Hex: "+Character.toString(this.letter) + Integer.toString(this.number));
    }

}