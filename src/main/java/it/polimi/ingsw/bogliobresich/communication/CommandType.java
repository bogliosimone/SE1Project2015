/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication;

/**
 * @author matteobresich
 * 
 * This Enums contains all the possible net operations supported by the game. 
 */

public enum CommandType {
    
    DO_MOVE_REQUEST                 (1,"move",""),
    DO_ATTACK_REQUEST               (2,"attack",""),
    DO_PLAY_ITEM_REQUEST            (3,"play-item",""),
    DO_END_MOVEMENT_PHASE_REQUEST   (4,"end-movement",""),
    DO_RUMOR_IN_COORDINATE_REQUEST  (5,"rumor-in",""),
    DO_DRAW_SECTOR_REQUEST          (6,"draw-sector",""),    
    DO_DISCARD_ITEM_REQUEST         (7,"discard-item",""),
    DO_END_TURN_REQUEST             (8,"end-turn","");
    
    private String cmdName;
    private String cmdDescription;
    int num;
    
    CommandType(int n, String name, String description) {
        this.num = n;
        this.cmdName = name;
        this.cmdDescription = description;
    }
    
    public String getCommandName() {
        return cmdName;
    }
    
    public String getCommandDescription() {
        return cmdDescription;
    }
    
    public int getNum() {
        return num;
    }
}
