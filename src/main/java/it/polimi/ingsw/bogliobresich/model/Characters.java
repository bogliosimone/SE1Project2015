/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model;

import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;

/**
 * The <code>Characters</code> enum contains all the characters of the game.<br>
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public enum Characters {
    CAPTAIN("Ennio Maria Dominoni",ConstantsCard.HUMAN),
    PILOT("Julia Niguloti",ConstantsCard.HUMAN),
    PSYCHOLOGIST("Silvano Porpora",ConstantsCard.HUMAN),
    SOLDIER("Tuccio Brendon",ConstantsCard.HUMAN),
    ALIENONE("Piero Ceccarella",ConstantsCard.ALIEN),
    ALIENTWO("Vittorio Martana",ConstantsCard.ALIEN),
    ALIENTHREE("Maria Galbani",ConstantsCard.ALIEN),
    ALIENFOUR("Paolo Landon",ConstantsCard.ALIEN);
    
    private String name;
    private String type;
    
    Characters(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    /**
     * @return the character name
     */
    public String getCharacterName() {
        return this.name;
    }
    
    /**
     * @return the character type
     */
    public String getCharacterType() {
        return this.type;
    }

}
