package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;

public class HumanPlayer extends Player {
    
    public HumanPlayer(User user,Coordinate coordinate,CharacterCard characterCard){
        super(user,coordinate,characterCard);
    }
    
    public void setMovementStep (int step){
        this.movementStep=step;
    }

    public void setCanAttack(boolean canAttack){
        this.canAttack = canAttack;
    }
    
    public void setCanDrawSectorCard(boolean canDraw){
        this.canDrawSectorCard = canDraw;
    }

    public void resetHumanPlayerAbility(){
        this.canAttack=false;
        this.canDrawSectorCard=true;
        this.movementStep=1;
    }
    
    @Override
    public String toString(){
        return new String("Umano ; Personaggio; "+ this.characterCard.toString()  +"; Coordinate: "+this.coordinate);
    }
}
