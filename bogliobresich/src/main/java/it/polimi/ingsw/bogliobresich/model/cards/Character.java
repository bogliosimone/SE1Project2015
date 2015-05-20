/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

/**
 * @author Matteo
 *
 */
public enum Character {
    CAPTAIN("Ennio Maria Dominoni"),
    PILOT("Julia Niguloti"),
    PSYCHOLOGIST("Silvano Porpora"),
    SOLDIER("Tuccio Brendon"),
    ALIENONE("Piero Ceccarella"),
    ALIENTWO("Vittorio Martana"),
    ALIENTHREE("Maria Galbani"),
    ALIENFOUR("Paolo Landon");
    
    private String name;
    
    private Character(String name) {
        this.name = name;
    }

}
