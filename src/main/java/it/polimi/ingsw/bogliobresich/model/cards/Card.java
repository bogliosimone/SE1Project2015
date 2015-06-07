/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.cards;

import it.polimi.ingsw.bogliobresich.model.player.Player;

/**
 * @author Matteo
 *
 */
public interface Card {
    public abstract void play(Player p);
    public abstract void setId(int index);
    public abstract int getId();
}