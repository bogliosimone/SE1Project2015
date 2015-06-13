/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import it.polimi.ingsw.bogliobresich.model.match.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionListUser;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerRoomEndAction;
import it.polimi.ingsw.bogliobresich.model.match.timer.TimerWaitRoom;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class WaitRoomState implements State {
    private List<User> users=new ArrayList<User>();
    private Timer timerWaitRoom = new Timer();
    @Override
    public void doAction(Match match, Player player, Action action) {
        if(action instanceof AddPlayerAction){
            User tmpUser=((AddPlayerAction) action).getUser();
            users.add(tmpUser);
            match.notifyAllPlayer(tmpUser.getNickname()+" si è aggiunto alla sala di attesa");
            if(users.size()==ConstantMatch.MINPLAYERS){
                TimerWaitRoom timerClass = new TimerWaitRoom(match);
                this.timerWaitRoom.schedule(timerClass, ConstantMatch.TIMEWAITROOM);
            }
            if(users.size()==ConstantMatch.MAXPLAYERS){
                this.timerWaitRoom.cancel();
                match.setState(new InitState());
                match.doAction(null, new ActionListUser(users));
            }  
            return;
        }
        if(action instanceof TimerRoomEndAction){
            match.notifyAllPlayer("Tempo di attesa terminato");
            if(users.size()<ConstantMatch.MINPLAYERS)
                match.notifyAllPlayer("Non ci sono abbastanza giocatori, FATAL ERROR");
            else{
                this.timerWaitRoom.cancel();
                match.notifyAllPlayer("La partita sta per iniziare");
                match.setState(new InitState());
                match.doAction(null, new ActionListUser(users));
            }
            return;
        }
        
        
        match.serviceMessage("Azione non disponibile nella sala d'attesa");
    }

}
