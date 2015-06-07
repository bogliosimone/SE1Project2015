package it.polimi.ingsw.bogliobresich.model.player;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

public class HumanPlayer extends Player {
    
    public HumanPlayer(int idPlayer,String nickName,Coordinate coordinate,CharacterCard characterCard){
        super(idPlayer,nickName,coordinate,characterCard);
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

    public void resetHumanPlayerEndTurn(){
        this.canAttack=false;
        this.canDrawSectorCard=true;
        this.movementStep=1;
    }
    
    @Override
    public String toString(){
        return new String("Umano: "+this.nickName+" coordinate: "+this.coordinate);
    }
}
