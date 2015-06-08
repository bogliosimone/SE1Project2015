/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.ArrayList;
import java.util.List;

import it.polimi.ingsw.bogliobresich.model.match.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionListUser;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.TimerRoomEndAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class WaitRoomState implements State {
    List<User> users=new ArrayList<User>();
    @Override
    public void doAction(Match match, Player player, Action action) {
        if(action instanceof AddPlayerAction){
            if(users.isEmpty());
                //fai partire timer
            User tmpUser=((AddPlayerAction) action).getUser();
            users.add(tmpUser);
            match.notifyAllPlayer(tmpUser.getNickname()+" si è aggiunto alla sala di attesa");
            if(users.size()==3){
                //ferma timer
                match.setState(new InitState());
                match.doAction(null, new ActionListUser(users));
            }  
            return;
        }
        if(action instanceof TimerRoomEndAction){
            match.notifyAllPlayer("Tempo di attesa terminato");
            if(users.size()<ConstantMatch.MINPLAYERS){
                //fai ripartire timer
                match.notifyAllPlayer("Non ci sono abbastanza giocatori");
            match.notifyAllPlayer("La partita sta per iniziare");
            match.setState(new InitState());
            match.doAction(null, new ActionListUser(users));
            }
                
            return;
        }
        
        
        match.notifyAllPlayer("Azione non disponibile nella sala d'attesa, attendi che si aggiungano altri giocatori");
    }

}
