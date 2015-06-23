/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

/**
 * @author simoneboglio
 */
public enum Commands {
    
    //generic commands
    GENERIC_MESSAGE, //(String) generic string notification for all player
    PLAYER_MESSAGE, //(String) info for a single player
    GAME_INFO_MESSAGE, //(String) info of game for all player
    MOVES_AVAIABLE, //(MovesAvaiable) list of all moves that a player can do (if he can moves there is a set of Coordinates in the object)
    YOU_WIN, //when a player win 
    YOU_LOST, // when a player lost
    YOU_DIE, //when a player die after an attack
    HUMAN_ESCAPE, //(Player) human that escape from the alien, he win (broadcast)
    PLAYER_DIE, //(Player) user that die after attack (broadcast)
    USER_DISCONNECTED, //(User) when user disconnected (broadcast)
    YOU_DISCONNECTED, //advise the player he can't play anymore until end of the game cause time is up
    YOU_ARE_FEED, //when alien eat another player
    
    
    //item card
    DISCARD_CARD, //(ItemCard) when player play in right mode a card he need to discard its
    ITEM_PLAYED, //(String) notify all player when a player play a card
    //error
    FATAL_ERROR, //(String) fatal error, something wrong, the game is broken, end it
    GENERIC_ERROR, //(String) maybe you try to do something you can't do in that moment
    IS_NOT_YOUR_TURN, // when a player try to play in a turn of another player
    CANT_PLAY_CARD, //error when player try to play that can't play
    MOVE_NO_AVAIABLE, //error when player try to do something he can't do in a specific phase of game
    COORDINATE_ERROR, //coordinate not valid
    
    //wait room
    PLAYER_JOIN_WAIT_ROOM, // when user join wait room
    //initial state
    WHO_ARE_YOU, //(Player) player know is role in the game
    LIST_USERS, //(List User) player know his game mate
    GAME_MAP_FILE_NAME, //(String) name of the file map that need to used in this game
    GAME_START, // the game begin
    //start turn
    USER_START_TURN, //(User) user that actually play (broadcast)
    START_TURN, //(Integer) advise the player is his turn, start phase turn
    SET_YOUR_COORDINATE, //(Coordinate) set the current player position (used in teleport card too)
    START_TIMER, //when begin player turn, timer start 120sec
    SECTOR_TYPE_MESSAGE, //(String) type of sector where the player moved
    //movement phase
    START_MOVEMENT_PHASE, //advise the player that he is in movement phase of the turn
    //porthole phase
    PORTHOLE_BROKEN, //(Coordinate) when players can't use anymore a porthole
    //end Phase
    START_END_PHASE, //advise the player that he is in end phase of the turn
    //end turn
    END_TURN, //advise the player that his turn is end (stop timer if necessary)
    USER_END_TURN, //(User) user that actually end turn (broadcast)
    //end game
    GAME_END, //game is end
    //attack phase
    ATTACK, //(String) when a player attack other players are advise
    DISCARD_HAND, // when a player die need to discard all hand
    //draw item card phase
    DRAW_CARD, //(ItemCard) player draw a new item card
    CARDS_END, //item card deck is empty
    HAND_FULL, //hand of player is full, need to discard or play object
    //hand full phase
    CANT_DISCARD_CARD, // error when player try to discard item card that don't have
    //draw sector phase
    DRAW_SECTOR_CARD, // (SectorCard) card sector drawed
    CALL_RUMOR, // player need to call rumor in some coordiante
    
    SERVER_NOT_RESPONDING, //(String) server connection error
    
    
    //da cancellare
    ALL_PLAYERS_MESSAGE,
    PLAYER_COMMAND;
}
