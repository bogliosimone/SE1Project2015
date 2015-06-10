/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.List;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class AttackPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }
        if(action instanceof AttackAction){
            Coordinate attackCoord=player.getCoordinate();
            match.notifyAllPlayer(player.getNickName()+" sta attaccando nel settore: "+attackCoord);
            List<Player> tmpPlayers = match.getAllPlayer();
            boolean eat=false;
            for(Player tmpPlayer: tmpPlayers){
                if(tmpPlayer.getCoordinate().equals(attackCoord)&&tmpPlayer.isAlive()&& !(tmpPlayer.equals(player))){
                    eat=true;
                    if(tmpPlayer instanceof HumanPlayer){
                        ItemHand tmpHand=tmpPlayer.getHand();
                        ItemCard tmpCard=tmpHand.getDefenceCard();
                        if(tmpCard!=null){
                            match.playItemCard(tmpPlayer, tmpCard);
                            match.notifyPlayer(tmpPlayer,"ha giocato la carta: "+tmpCard.toString());
                        }
                        else{
                            tmpPlayer.SetIsAlive(false);
                            match.notifyAllPlayer(tmpPlayer.getNickName()+" è morto, era un UMANO");
                            match.setIsLastPlayerKill(true);
                            if(player instanceof AlienPlayer && !((AlienPlayer) player).isFeed()){
                                ((AlienPlayer) player).feed();
                                match.notifyPlayer(player, "Ti sei nutrito di un umano, ora puoi muoverti di tre caselle");
                            }
                        }
                    }
                    if(tmpPlayer instanceof AlienPlayer){
                        tmpPlayer.SetIsAlive(false);
                        match.notifyAllPlayer(tmpPlayer.getNickName()+" è morto, era un ALIENO");
                    }
                    if(!tmpPlayer.isAlive()){
                        match.discardItemHandInItemDeck(tmpPlayer);
                        match.serviceMessage("Mano scartata");
                    }
                }
            }
            if(!eat)
                match.notifyPlayer(player, "Non c'è nessun player in questo settore");
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, new EndPhaseAction());
            return;
        }
        match.notifyPlayer(player, "Mossa non consentita durante AttackPhase");
        return;
    }

}
