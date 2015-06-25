package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * class for player with nature human, he can't attack by default, he move 1 step by default, he can draw sector card by default, he can't attack by defautl
 * this class is serializable
 * @author simoneboglio
 *
 */
public class HumanPlayer extends Player {

    /**
     * serial version id
     */
    private static final long serialVersionUID = 8216655866206414070L;

    /**
     * create a player with nature human
     * @param user info of the player
     * @param coordinate where the player is locate in the map
     * @param characterCard character that he play in a game
     */
    public HumanPlayer(User user,Coordinate coordinate,CharacterCard characterCard){
        super(user,coordinate,characterCard);
    }
    
    /**
     * change the movement step of the player
     * @param step number he can move
     */
    public void setMovementStep (int step){
        this.movementStep=step;
    }

    /**
     * set true if human can attack
     * @param canAttack true if he can attack
     */
    public void setCanAttack(boolean canAttack){
        this.canAttack = canAttack;
    }
    
    /**
     * set false if the player can no draw a sector card
     * @param canDraw true if he can draw sector card
     */
    public void setCanDrawSectorCard(boolean canDraw){
        this.canDrawSectorCard = canDraw;
    }

    /**
     * reset to default value all human player ability
     */
    public void resetHumanPlayerAbility(){
        this.canAttack=false;
        this.canDrawSectorCard=true;
        this.movementStep=1;
    }
}
