/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;

/**
 * @author simoneboglio
 *
 */
public class DrawItemCardAction implements Action {
    SectorCard card;
    public DrawItemCardAction(SectorCard card){
        this.card=card;
    }
    public SectorCard getSectorCard(){
        return this.card;
    }
}
