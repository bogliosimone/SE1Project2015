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
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class AttackPhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage("Comando non valido");
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di attack phase");
            return;
        }
        if(action instanceof AttackAction){
            Coordinate attackCoord=player.getCoordinate();
            match.notifyAllPlayer(player.getNickName()+" sta attaccando nel settore: "+attackCoord);
            match.notifyAllPlayer(Commands.ATTACK, player.getNickName()+" sta attaccando nel settore: "+attackCoord);
            List<Player> tmpPlayers = match.getAllPlayer();
            boolean eat=false;
            for(Player tmpPlayer: tmpPlayers){
                if(tmpPlayer.getCoordinate().equals(attackCoord)&&tmpPlayer.isAlive()&& !(tmpPlayer.equals(player))){
                    eat=true;
                    if(tmpPlayer instanceof HumanPlayer){
                        ItemCard tmpCard=tmpPlayer.getHand().getDefenceCard();
                        if(tmpCard!=null){
                            tmpCard = match.playItemCard(player, tmpCard);
                            if(tmpCard!=null){
                                match.notifyAllPlayer("ha giocato la carta: "+tmpCard.toString());
                                match.notifyAllPlayer(Commands.ITEM_PLAYED, player+" ha giocato la carta: "+tmpCard.toString());
                                match.notifyPlayer(Commands.DISCARD_CARD, tmpCard, player);
                            }
                        }
                        else{
                            tmpPlayer.SetIsAlive(false);
                            match.notifyAllPlayer(tmpPlayer.getNickName()+" è morto, era un UMANO");
                            match.notifyPlayer(tmpPlayer, "Sei morto!");
                            match.notifyAllPlayer(Commands.PLAYER_DIE, tmpPlayer);
                            match.notifyPlayer(Commands.YOU_DIE, null,tmpPlayer);
                            match.setIsLastPlayerKill(true);
                            if(player instanceof AlienPlayer && !((AlienPlayer) player).isFeed()){
                                ((AlienPlayer) player).feed();
                                match.notifyPlayer( player,"Ti sei nutrito di un umano, ora puoi muoverti di tre caselle");
                                match.notifyPlayer(Commands.YOU_ARE_FEED,null,player);
                            }
                        }
                    }
                    if(tmpPlayer instanceof AlienPlayer){
                        tmpPlayer.SetIsAlive(false);
                        match.notifyAllPlayer(tmpPlayer.getNickName()+" è morto, era un ALIENO");
                        match.notifyPlayer(tmpPlayer, "Sei morto!");
                        match.notifyAllPlayer(Commands.PLAYER_DIE,tmpPlayer);
                        match.notifyPlayer(Commands.YOU_DIE, null,tmpPlayer);
                    }
                    if(!tmpPlayer.isAlive()){
                        match.discardItemHandInItemDeck(tmpPlayer);
                        match.notifyPlayer(Commands.DISCARD_HAND,null,tmpPlayer);
                    }
                }
            }
            if(!eat){
                match.notifyAllPlayer( "Non c'è nessun player nel settore "+attackCoord);
                match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE,"Non c'è nessun player nel settore "+attackCoord);
            }
            if(!match.atLeastOneHumaAlive()){
                match.setState(new EndTurnState()); 
                match.doAction(player, new EndTurnAction());
            }
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, new EndPhaseAction());
            return;
        }
        match.notifyPlayer(player, "Mossa non consentita durante AttackPhase");
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        return;
    }

}
