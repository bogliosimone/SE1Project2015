/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.PortholeCard;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PortholeAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * state when a player go in a porthole sector
 * @author simoneboglio
 *
 */
public class PortholePhaseTurnState implements State {

    @Override
    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase porthole phase turn");
            match.serviceMessage("Comando non valido");
            return;
        }
        
        if(action instanceof PortholeAction){
            Deck deckPH= match.getPortholeDeck();
            Card tempCard;
            boolean phValid=true;
            try {
                tempCard = deckPH.drawCard();
                if(!(tempCard instanceof PortholeCard)){
                    match.serviceMessage("Fatal error draw porthole card");
                    match.serviceMessage(Commands.FATAL_ERROR, "errore nel porthole deck, FATAL ERROR");
                    return;
                }
                PortholeCard card=(PortholeCard) tempCard;
                if(card.isPortholeStateWorking()){
                    match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE, player.getNickName()+" ha pescato un carta scialuppa VERDE");
                    match.notifyAllPlayer(player.getNickName()+" ha pescato un carta scialuppa VERDE");
                    player.setIsWinner(true);
                    match.setIsLastPlayerKill(false);
                    match.notifyAllPlayer(Commands.HUMAN_ESCAPE,player);
                    match.notifyAllPlayer(player.getNickName()+" ha lasciato l'astronave, "+player.getNickName()+" ha vinto!");
                    match.notifyPlayer(Commands.YOU_WIN,null,player);
                }
                else{
                    match.notifyAllPlayer(player.getNickName()+" ha pescato un carta scialuppa ROSSA");
                    match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE,player.getNickName()+" ha pescato un carta scialuppa ROSSA");
                }
                HexMap gameMap= match.getGameMap();
                gameMap.setPortholeStatus(player.getCoordinate(), false);
                match.notifyAllPlayer("Porthole numero: "+gameMap.getNumberPorthole(player.getCoordinate())+" non è più utilizzabile");
                match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE,"Porthole numero: "+gameMap.getNumberPorthole(player.getCoordinate())+" non è più utilizzabile");
                match.notifyAllPlayer(Commands.PORTHOLE_BROKEN, player.getCoordinate());
                if(!gameMap.thereArePortholeActive()){
                    phValid=false;
                    match.notifyAllPlayer("Non ci sono più scialuppe di salvataggio disponibili");
                    match.notifyAllPlayer(Commands.GAME_INFO_MESSAGE,"Non ci sono più scialuppe di salvataggio disponibili");
                }
                if(player.isWinner()||!phValid){
                    match.setState(new EndTurnState());
                    match.doAction(player, new EndTurnAction());
                    return;
                }
                else{
                    match.setState(new EndPhaseTurnState());
                    match.doAction(player, new EndPhaseAction());
                }
                return;
            } catch (CardFinishedException e) {
                match.serviceMessage("Fatal error draw porthole card");
                match.serviceMessage(Commands.FATAL_ERROR, "errore nel porthole deck, FATAL ERROR");
            }
            return;
        }
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        return;
    }

}
