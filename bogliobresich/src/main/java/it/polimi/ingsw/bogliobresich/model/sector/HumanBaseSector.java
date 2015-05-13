/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.sector;

/**
 * @author simoneboglio
 *
 */
public class HumanBaseSector extends BaseSector {
	public HumanBaseSector(char letter,int number){
		super(letter,number);
		this.crossable = false;
	}
}
