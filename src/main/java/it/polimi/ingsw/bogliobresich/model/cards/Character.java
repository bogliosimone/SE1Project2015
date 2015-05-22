/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public enum Character {
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
    
    Character(String name, String type) {
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
