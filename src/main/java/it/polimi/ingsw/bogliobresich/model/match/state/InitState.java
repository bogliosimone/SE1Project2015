/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.state;

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
        if(action instanceof ActionListUser){
            createDecks(match);
            createPlayers(match,(ActionListUser)action);
            setFirstTurn(match);
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
        match.setNumberOfPlayers(users.size()); //set number of players
        match.serviceMessage("Numero di gioactori: "+ users.size());
        Deck deckChar=match.getCharacterDeck();
        int id=1;
        for(User user: users){
            Player newPlayer;

            try{
                CharacterCard card = (CharacterCard)deckChar.drawCard();

                if(id%2==1)
                    newPlayer=new AlienPlayer(id,user.getNickname(),map.getCoordinateAlienBase(),card);
                else
                    newPlayer=new HumanPlayer(id,user.getNickname(),map.getCoordinateHumanBase(),card);
                id++;
                match.addPlayer(newPlayer);
                match.serviceMessage("Creato player: "+newPlayer.toString());
            }
            catch (CardFinishedException e) { match.serviceMessage("CARTA PERSONAGGIO NON ESISTENTE");
            }
        }
    }
    private void createDecks(Match match){
        DeckFactory factory = new MyDeckFactory();
        match.setItemDeck(factory.createItemDeck());
        match.setCharacterDeck(factory.createCharacterDeck());
        match.setSectorDeck(factory.createSectorDeck());
        match.setPortholeDeck(factory.createPortholeDeck());
        match.serviceMessage("Mazzi creati");
    }

    private void setFirstTurn(Match match){
        match.setCurrentTurn(1);
        match.serviceMessage("Primo turno");
    }


}