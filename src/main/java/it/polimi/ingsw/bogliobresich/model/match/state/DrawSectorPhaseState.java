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
            match.serviceMessage("Comando non valido");
            return;
        }

        if(action instanceof DrawSectorAction){
            Deck deckItem= match.getSectorDeck();
            Card tempCard;
            try {
                tempCard = deckItem.drawCard();
                if(!(tempCard instanceof SectorCard)){
                    match.serviceMessage("Fatal error draw sector card");
                    return;
                }
                SectorCard card= (SectorCard) tempCard;
                if(card.isThereSilence()){
                    match.notifyPlayer(player, "Hai pescato: SILENZIO");
                    match.notifyAllPlayer("Player "+player.getNickName()+ " dichiara SILENZIO");
                    nextState(match,player,card.isThereAnItemToDraw());
                    return;
                }
                if(card.isThereNoiseInAnySector()){
                    match.notifyPlayer(player, "Hai pescato: RUMORE IN QUALUNQUE SETTORE");
                    match.notifyPlayer(player, "In quale settore vuoi dichiarare rumore?");
                    cardDraw=card;
                    return;
                }
                if(card.isThereNoiseInMySector()){
                    match.notifyPlayer(player, "Hai pescato: RUMORE NEL TUO SETTORE");
                    match.notifyAllPlayer("Player "+player.getNickName()+ " dichiara RUMORE in "+player.getCoordinate());
                    nextState(match,player,card.isThereAnItemToDraw());
                    return;
                }
            } catch (CardFinishedException e) {
                match.serviceMessage("Fatal error draw sector card");
            } 
            return;
        }

        if(action instanceof RumorCoordinate && cardDraw!=null){
            Coordinate coord = ((RumorCoordinate)action).getCoordinate();
            HexMap gameMap=match.getGameMap();
            if(gameMap.isValidCoordinate(coord)){
                match.notifyAllPlayer("Player "+player.getNickName()+ " dichiara RUMORE in "+coord);
                nextState(match,player,cardDraw.isThereAnItemToDraw());
                return;
            }
            else{
                match.notifyPlayer(player, "Posizione non valida");
            }
            return;
        }

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
