/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication;

/**
 * @author matteobresich
 * 
 * This Enums contains all the possible net operations supported by the game. 
 */

public enum ClientCommands {
    DO_MOVE_REQUEST,
    DO_ATTACK_REQUEST,
    DO_PLAY_ITEM_REQUEST,
    DO_END_MOVEMENT_PHASE_REQUEST,
    DO_RUMOR_IN_COORDINATE_REQUEST,
    DO_DRAW_SECTOR_REQUEST,    
    DO_DISCARD_ITEM_REQUEST,
    DO_END_TURN_REQUEST;
    
}
