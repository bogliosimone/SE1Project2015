/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.deck.concretedeck;

import it.polimi.ingsw.bogliobresich.model.cards.AdrenalineItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.AttackItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.Card;
import it.polimi.ingsw.bogliobresich.model.cards.ConstantsCard;
import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SedativesItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.deck.ConstantsDeck;
import it.polimi.ingsw.bogliobresich.model.deck.Deck;

/**
 * @author matteobresich
 *
 */

public class ItemDeck extends Deck {
    private int lastId = 0;

    /**
     * Class constructor
     */
    public ItemDeck() {
        addItemCard(ConstantsCard.ATTACK,ConstantsDeck.N_ITEMCARD_ATTACK);
        addItemCard(ConstantsCard.DEFENCE,ConstantsDeck.N_ITEMCARD_DEFENCE);
        addItemCard(ConstantsCard.TELEPORT,ConstantsDeck.N_ITEMCARD_TELEPORT);
        addItemCard(ConstantsCard.SPOTLIGHT,ConstantsDeck.N_ITEMCARD_SPOTLIGHT);
        addItemCard(ConstantsCard.SEDATIVES,ConstantsDeck.N_ITEMCARD_SEDATIVES);
        addItemCard(ConstantsCard.ADRENALINE,ConstantsDeck.N_ITEMCARD_ADRENALINE);
        super.shuffle();
    }
    
    private int generateId() { return lastId++; }
    
    private void addItemCard(String type,int nItem) {
        Card card;
        if (type == ConstantsCard.ATTACK) {
            for(int index = 0; index < nItem; index++) {
                card = new AttackItemCard(generateId());
                super.addCard(card,generateId());
            }
        } else if (type == ConstantsCard.DEFENCE) {
            for(int index = 0; index < nItem; index++) {
                card = new DefenceItemCard(generateId());
                super.addCard(card,generateId());
            }
        } else if (type == ConstantsCard.TELEPORT) {
            for(int index = 0; index < nItem; index++) {
                card = new TeleportItemCard(generateId());
                super.addCard(card,generateId());
            }
        } else if (type == ConstantsCard.SPOTLIGHT) {
            for(int index = 0; index < nItem; index++) {
                card = new SpotlightItemCard(generateId());
                super.addCard(card,generateId());
            }
        } else if (type == ConstantsCard.SEDATIVES) {
            for(int index = 0; index < nItem; index++) {
                card = new SedativesItemCard(generateId());
                super.addCard(card,generateId());
            }
        } else if (type == ConstantsCard.ADRENALINE) {
            for(int index = 0; index < nItem; index++) {
                card = new AdrenalineItemCard(generateId());
                super.addCard(card,generateId());
            }
        }
    }
}
