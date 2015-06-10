/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;
import it.polimi.ingsw.bogliobresich.model.deck.DeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.MyDeckFactory;
import it.polimi.ingsw.bogliobresich.model.deck.exception.CardFinishedException;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.Match;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.match.action.Action;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionListUser;
import it.polimi.ingsw.bogliobresich.model.match.action.ActionStartTurn;
import it.polimi.ingsw.bogliobresich.model.player.AlienPlayer;
import it.polimi.ingsw.bogliobresich.model.player.HumanPlayer;
import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author simoneboglio
 *
 */
public class InitState implements State {
    @Override
    public void doAction(Match match,Player player, Action action){
        if(player!=null){
            match.notifyPlayer(player, "La partita è in fase di inizializzazione, attendi");
            return;
        }
        if(action instanceof ActionListUser){
            match.setIsActive(true);
            match.notifyAllPlayer("La partita è attiva");
            int numbOfPlayers=(((ActionListUser) action).getListUser()).size();
            createDecks(match,numbOfPlayers);
            createPlayers(match,(ActionListUser)action);
            match.serviceMessage("Numero di gioactori: "+ match.getNumberOfPlayers());
            match.setState(new StartTurnState());
            match.doAction(null, new ActionStartTurn());
        }
        else
            match.serviceMessage("Azione non disponibile in fase di inizializzazione del gioco");
    }

    private void createPlayers(Match match,ActionListUser action){
        HexMap map=match.getGameMap();
        List<User> users=action.getListUser();
        Collections.shuffle(users);
        Deck deckChar=match.getCharacterDeck();
        int id=1;
        List<Player> tempList = new ArrayList<Player>();
        for(User user: users){
            Player newPlayer;

            try{
                CharacterCard card = (CharacterCard)deckChar.drawCard();

                if(id%2==1)
                    newPlayer=new AlienPlayer(user,map.getCoordinateAlienBase(),card);
                else
                    newPlayer=new HumanPlayer(user,map.getCoordinateHumanBase(),card);
                id++;
                tempList.add(newPlayer);
            }
            catch (CardFinishedException e) { match.serviceMessage("CARTA PERSONAGGIO NON ESISTENTE");
            }
        }
        Collections.shuffle(tempList);
        for(Player newPlayer: tempList){
            match.addPlayer(newPlayer);
            match.serviceMessage("Creato e aggiunto player: "+newPlayer.toString());
        }
        return;
    }
    private void createDecks(Match match, int numbOfPlayers){
        DeckFactory factory = new MyDeckFactory();
        match.setItemDeck(factory.createItemDeck());
        match.setCharacterDeck(factory.createCharacterDeck(numbOfPlayers));
        match.setSectorDeck(factory.createSectorDeck());
        match.setPortholeDeck(factory.createPortholeDeck());
    }


}
