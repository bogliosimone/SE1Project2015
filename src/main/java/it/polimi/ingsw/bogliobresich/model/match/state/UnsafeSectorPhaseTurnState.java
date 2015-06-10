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
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawItemCardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.UnsafeSectorAction;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class UnsafeSectorPhaseTurnState implements State {
    SectorCard cardDraw;
    boolean ThereIsItemCardToDraw=false;
    @Override
    public void doAction(Match match, Player player, Action action) {
        if(!player.equals(match.getCurrentPlayer())){
            match.notifyPlayer(player, "Non è il tuo turno");
            return;
        }
        if(action instanceof UnsafeSectorAction){
            if(!player.canAttack() && !player.canDrawSectorCard()){
               match.setState(new EndPhaseTurnState()); 
               match.doAction(player, new EndPhaseAction());
               return;
            }
            if(!player.canAttack() && player.canDrawSectorCard()){
                match.doAction(player, new DrawSectorAction());
                return;
             }
            match.notifyPlayer(player, "Attacchi o peschi una carta settore?");
            return;
        }
        if(action instanceof AttackAction){
            match.setState(new AttackPhaseTurnState()); 
            match.doAction(player, action);
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
                    match.setState(new EndPhaseTurnState()); 
                    match.doAction(player, new EndPhaseAction());
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
                    if(card.isThereAnItemToDraw()){
                        ThereIsItemCardToDraw=true;
                        match.doAction(player, new DrawItemCardAction());
                        return;
                    }
                    else{
                        match.setState(new EndPhaseTurnState()); 
                        match.doAction(player, new EndPhaseAction());
                        return;
                    }
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
            if(cardDraw.isThereAnItemToDraw()){
                ThereIsItemCardToDraw=true;
                match.doAction(player, new DrawItemCardAction());
                return;
                }
            else{
                match.setState(new EndPhaseTurnState()); 
                match.doAction(player, new EndPhaseAction());
                return;
            }
        }
        else{
            match.notifyPlayer(player, "Posizione non valida");
        }
        return;
    }
    if(action instanceof DrawItemCardAction && ThereIsItemCardToDraw){
        match.notifyPlayer(player, "La tua carta settore contiene un oggetto");
        match.setState(new DrawPhaseTurnState());
        match.doAction(player, new DrawItemCardAction());
        return;
    }
        
        match.notifyPlayer(player, "Mossa non consentita durante UnsafeSectorPhase");
    }

}
