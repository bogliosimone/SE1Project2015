package it.polimi.ingsw.bogliobresich.model.player;

public class HumanPlayer extends Player {
    
    HumanPlayer(int idPlayer,String nickName){
        super(idPlayer,nickName);
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
    
}
