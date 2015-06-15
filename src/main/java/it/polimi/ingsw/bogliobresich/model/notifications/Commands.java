/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

/**
 * @author matteobresich
 * @author simoneboglio
 */
public enum Commands {
    GENERIC_MESSAGE, //(String) generic string notification for all player
    
    FATAL_ERROR, //(String) fatal error, something wrong, the game is broken, end it
    GENERIC_ERROR, //(String) maybe you try to do something you can't do in that moment
    
    PLAYER_JOIN_WAIT_ROOM, // when user join wait room
    
    WHO_ARE_YOU, //(Player) player know is role in the game
    LIST_USERS, //(List User) player know his game mate
    GAME_START, // the game begin
    
    
    
    
    

    
    PLAYER_MESSAGE,
    ALL_PLAYERS_MESSAGE,
    PLAYER_COMMAND;
}
