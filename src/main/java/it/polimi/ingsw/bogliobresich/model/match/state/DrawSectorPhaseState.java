/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class DrawSectorPhaseState implements State {
    SectorCard cardDraw=null;
    @Override

    public void doAction(Match match, Player player, Action action) {
        if(player==null){
            match.serviceMessage(Commands.GENERIC_ERROR,"Azione non disponibile in fase di drawSectorCard");
            match.serviceMessage("Comando non valido");
            return;
        }

        if(action instanceof DrawSectorAction && cardDraw==null){
            Deck deckItem= match.getSectorDeck();
            Card tempCard;
            try {
                tempCard = deckItem.drawCard();
                if(!(tempCard instanceof SectorCard)){
                    match.serviceMessage("Fatal error draw sector card");
                    match.serviceMessage(Commands.FATAL_ERROR, "errore nel sector deck, FATAL ERROR");
                    return;
                }
                SectorCard card= (SectorCard) tempCard;
                if(card.isThereSilence()){
                    match.notifyPlayer(player, "Hai pescato: SILENZIO");
                    match.notifyPlayer(Commands.DRAW_SECTOR_CARD, card, player);
                    match.notifyAllPlayer("Player "+player.getNickName()+ " dichiara SILENZIO");
                    match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"Player "+player.getNickName()+ " dichiara SILENZIO");
                    nextState(match,player,card.isThereAnItemToDraw());
                    return;
                }
                if(card.isThereNoiseInAnySector()){
                    match.notifyPlayer(player, "Hai pescato: RUMORE IN QUALUNQUE SETTORE");
                    match.notifyPlayer(Commands.DRAW_SECTOR_CARD, card, player);
                    match.notifyPlayer(player, "In quale settore vuoi dichiarare rumore?");
                    match.notifyPlayer(Commands.CALL_RUMOR, null,player);
                    cardDraw=card;
                    return;
                }
                if(card.isThereNoiseInMySector()){
                    match.notifyPlayer(player, "Hai pescato: RUMORE NEL TUO SETTORE");
                    match.notifyPlayer(Commands.DRAW_SECTOR_CARD, card, player);
                    match.notifyAllPlayer("Player "+player.getNickName()+ " dichiara RUMORE in "+player.getCoordinate());
                    match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"Player "+player.getNickName()+ " dichiara RUMORE in "+player.getCoordinate());
                    nextState(match,player,card.isThereAnItemToDraw());
                    return;
                }
            } catch (CardFinishedException e) {
                match.serviceMessage(Commands.FATAL_ERROR, "errore nel sector deck, FATAL ERROR");
                match.serviceMessage("Fatal error draw sector card");
            } 
            return;
        }

        if(action instanceof RumorCoordinate && cardDraw!=null){
            Coordinate coord = ((RumorCoordinate)action).getCoordinate();
            HexMap gameMap=match.getGameMap();
            if(gameMap.isValidCoordinate(coord)){
                match.notifyAllPlayer("Player "+player.getNickName()+ " dichiara RUMORE in "+coord);
                match.notifyAllPlayer(Commands.GENERIC_MESSAGE,"Player "+player.getNickName()+ " dichiara RUMORE in "+coord);
                nextState(match,player,cardDraw.isThereAnItemToDraw());
                return;
            }
            else{
                match.notifyPlayer(player, "Posizione non valida");
                match.notifyPlayer(Commands.COORDINATE_ERROR, null, player);
                match.notifyPlayer(Commands.CALL_RUMOR, null,player);
            }
            return;
        }
        
        match.notifyPlayer(Commands.MOVE_NO_AVAIABLE, null, player);
        if(cardDraw!=null)
            match.notifyPlayer(Commands.CALL_RUMOR,null,player);

    }
    
    private void nextState(Match match,Player player,boolean thereIsItemToDraw){
        if(thereIsItemToDraw){
            match.setState(new DrawItemPhaseTurnState());
            match.doAction(player, new DrawItemCardAction());
        }
        else{
            match.setState(new EndPhaseTurnState()); 
            match.doAction(player, new EndPhaseAction());
            return;
        }
    }
}
