/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author Matteo
 *
 */
public abstract class BaseSector extends Sector {
	public BaseSector(char letter, int number){
		super(letter,number);
		this.crossable = false;
	}
}
