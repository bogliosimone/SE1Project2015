/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.notifications;

/**
 * Enum list of all commands used in the game
 * GENERIC_MESSAGE, (String) generic string notification for all player <br />
 * PLAYER_MESSAGE, (String) info for a single player <br />
 * GAME_INFO_MESSAGE, (String) info of game for all player <br />
 * MOVES_AVAIABLE, (MovesAvaiable) list of all moves that a player can do (if he can moves there is a set of Coordinates in the object) <br />
 * YOU_WIN, when a player win  <br />
 * YOU_LOST, when a player lost <br />
 * YOU_DIE, when a player die after an attack <br />
 * HUMAN_ESCAPE, (Player) human that escape from the alien, he win (broadcast) <br />
 * PLAYER_DIE, (Player) user that die after attack (broadcast) <br />
 * USER_DISCONNECTED, (User) when user disconnected (broadcast) <br />
 * YOU_DISCONNECTED, advise the player he can't play anymore until end of the game cause time is up <br />
 * YOU_ARE_FEED, when alien eat another player <br />
 * LIST_PLAYERS_END_GAME, list player with winners and loser <br /> 
 * DISCARD_CARD, (ItemCard) when player play in right mode a card he need to discard its <br />
 * ITEM_PLAYED, (String) notify all player when a player play a card <br />
 * FATAL_ERROR, (String) fatal error, something wrong, the game is broken, end it <br />
 * GENERIC_ERROR, (String) maybe you try to do something you can't do in that moment <br />
 * IS_NOT_YOUR_TURN, when a player try to play in a turn of another player <br />
 * CANT_PLAY_CARD, error when player try to play that can't play <br />
 * MOVE_NO_AVAIABLE, error when player try to do something he can't do in a specific phase of game <br />
 * COORDINATE_ERROR, coordinate not valid <br />
 * PLAYER_JOIN_WAIT_ROOM, when user join wait room <br />
 * WHO_ARE_YOU, (Player) player know is role in the game <br />
 * LIST_USERS, (List User) player know his game mate <br />
 * GAME_MAP_FILE_NAME, (String) name of the file map that need to used in this game <br />
 * GAME_START, the game begin <br />
 * USER_START_TURN, (User) user that actually play (broadcast) <br />
 * START_TURN, (Integer) advise the player is his turn, start phase turn <br />
 * SET_YOUR_COORDINATE, (Coordinate) set the current player position (used in teleport card too) <br />
 * START_TIMER, when begin player turn, timer start 120sec <br />
 * SECTOR_TYPE_MESSAGE, (String) type of sector where the player moved <br />
 * START_MOVEMENT_PHASE, advise the player that he is in movement phase of the turn <br />
 * PORTHOLE_BROKEN, (Coordinate) when players can't use anymore a porthole <br />
 * START_END_PHASE, advise the player that he is in end phase of the turn <br />
 * END_TURN, advise the player that his turn is end (stop timer if necessary) <br />
 * USER_END_TURN, (User) user that actually end turn (broadcast) <br />
 * GAME_END, game is end <br />
 * ATTACK, (String) when a player attack other players are advise <br />
 * DISCARD_HAND, when a player die need to discard all hand <br />
 * DRAW_CARD, (ItemCard) player draw a new item card <br />
 * CARDS_END, item card deck is empty <br />
 * HAND_FULL, hand of player is full, need to discard or play object <br />
 * CANT_DISCARD_CARD, error when player try to discard item card that don't have <br />
 * DRAW_SECTOR_CARD, (SectorCard) card sector drawed <br />
 * CALL_RUMOR,  player need to call rumor in some coordinate  <br />
 * SERVER_NOT_RESPONDING, (String) server connection error <br />
 * @author simoneboglio
 */

public enum Commands {
    
    GENERIC_MESSAGE, 
    PLAYER_MESSAGE, 
    GAME_INFO_MESSAGE, 
    MOVES_AVAIABLE, 
    YOU_WIN, 
    YOU_LOST, 
    YOU_DIE,
    HUMAN_ESCAPE,
    PLAYER_DIE,
    USER_DISCONNECTED,
    YOU_DISCONNECTED,
    YOU_ARE_FEED,
    LIST_PLAYERS_END_GAME, 
    DISCARD_CARD,
    ITEM_PLAYED,
    FATAL_ERROR,
    GENERIC_ERROR, 
    IS_NOT_YOUR_TURN, 
    CANT_PLAY_CARD,
    MOVE_NO_AVAIABLE, 
    COORDINATE_ERROR,
    PLAYER_JOIN_WAIT_ROOM, 
    WHO_ARE_YOU, 
    LIST_USERS, 
    GAME_MAP_FILE_NAME,
    GAME_START,
    USER_START_TURN, 
    START_TURN, 
    SET_YOUR_COORDINATE, 
    START_TIMER, 
    SECTOR_TYPE_MESSAGE,
    START_MOVEMENT_PHASE, 
    PORTHOLE_BROKEN,
    START_END_PHASE,
    END_TURN, 
    USER_END_TURN,
    GAME_END,
    ATTACK, 
    DISCARD_HAND,
    DRAW_CARD,
    CARDS_END,
    HAND_FULL,
    CANT_DISCARD_CARD,
    DRAW_SECTOR_CARD, 
    CALL_RUMOR,
    SERVER_NOT_RESPONDING;
}
