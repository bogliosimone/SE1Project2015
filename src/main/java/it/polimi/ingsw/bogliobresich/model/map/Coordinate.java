/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

/**
 * Coordinate with two dimensional in letter-number or x-y 
 * letter-number <-> x-y = A-1 <-> 1-1
 * @author simoneboglio
 */
public class Coordinate {
    private static int offsetLetterA = 65;
    private char letter;
    private int number;

    /**
     * Create coordinate from letter and number
     * @param letter    coordinate
     * @param number    coordinate
     */
    public Coordinate (char letter, int number){
        this.letter = letter;
        this.number = number;
    }

    /**
     * Create coordinate from X and Y
     * @param x coordinate
     * @param y coordinate
     */
    public Coordinate (int x,int y){
        this.letter = (char) (x+offsetLetterA-1);
        this.number = y;
    }

    /**
     * Get letter of coordinate
     * @return  letter coordinate
     */
    public char getLetter(){
        return this.letter;
    }

    /**
     * Get number of coordinate
     * @return  number coordinate
     */
    public int getNumber(){
        return this.number;
    }


    /**
     * Get X of coordinate
     * @return  X coordinate
     */
    public int getX(){
        return this.letter - offsetLetterA + 1;
    }

    /**
     * Get Y of coordinate
     * @return  Y coordinate
     */
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
        return new String( Character.toString(this.letter) + Integer.toString(this.number));
    }

}