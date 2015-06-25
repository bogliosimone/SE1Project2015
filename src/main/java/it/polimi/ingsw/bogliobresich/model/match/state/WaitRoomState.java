/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import it.polimi.ingsw.bogliobresich.model.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionListUser;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerRoomEndAction;
import it.polimi.ingsw.bogliobresich.model.match.timer.TimerWaitRoom;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * state where the users join the game, if min player is reach game start after timer wait room is up or max player is reach
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
            match.notifyUser(Commands.PLAYER_JOIN_WAIT_ROOM,null,tmpUser);        
            match.notifyAllUser(Commands.GENERIC_MESSAGE, tmpUser.getNickname()+" si è aggiunto alla sala di attesa");
            if(users.size()==ConstantMatch.MINPLAYERS){
                TimerWaitRoom timerClass = new TimerWaitRoom(match);
                this.timerWaitRoom.schedule(timerClass, ConstantMatch.TIMEWAITROOM);
            }
            if(users.size()==match.getMaxNumberPlayer()){
                this.timerWaitRoom.cancel();
                match.setState(new InitState());
                match.doAction(null, new ActionListUser(users));
            }  
            return;
        }
        if(action instanceof TimerRoomEndAction){
            match.notifyAllPlayer("Tempo di attesa terminato");
            match.notifyAllUser(Commands.GENERIC_MESSAGE, "Tempo di attesa terminato");
            if(users.size()<ConstantMatch.MINPLAYERS){
                match.serviceMessage("Non ci sono abbastanza giocatori, FATAL ERROR");
                match.serviceMessage(Commands.FATAL_ERROR, new String("Non ci sono abbastanza giocatori, FATAL ERROR"));
                
            }
                
            else{
                this.timerWaitRoom.cancel();
                match.notifyAllPlayer("La partita sta per iniziare");
                match.notifyAllUser(Commands.GENERIC_MESSAGE,"La partita sta per iniziare");
                match.setState(new InitState());
                match.doAction(null, new ActionListUser(users));
            }
            return;
        }
        
        match.serviceMessage("Azione non disponibile nella sala d'attesa");
        match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile nella sala d'attesa");
    }

}
