/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model;

import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;

/**
 * @author Matteo
 *
 */
public enum Characters {
    CAPTAIN("Umano Ennio Maria Dominoni",ConstantsCard.HUMAN),
    PILOT("Umano Julia Niguloti",ConstantsCard.HUMAN),
    PSYCHOLOGIST("Umano Silvano Porpora",ConstantsCard.HUMAN),
    SOLDIER("Soldato Tuccio Brendon",ConstantsCard.HUMAN),
    ALIENONE("Alieno Piero Ceccarella",ConstantsCard.ALIEN),
    ALIENTWO("Alieno Vittorio Martana",ConstantsCard.ALIEN),
    ALIENTHREE("Alieno Maria Galbani",ConstantsCard.ALIEN),
    ALIENFOUR("Alieno Paolo Landon",ConstantsCard.ALIEN);
    
    
    private String name;
    private String type;
    
    Characters(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    public String getCharacterName() {
        return this.name;
    }
    
    public String getCharacterType() {
        return this.type;
    }

}
