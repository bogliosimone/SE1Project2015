package it.polimi.ingsw.bogliobresich.model.deck.exception;

/**
 * This class is thrown when there aren't cards in a deck.
 * @author matteo bresich
 * @author simone boglio
 * @version 1.0 
 * @see java.lang.Exception
 */

public class CardFinishedException extends Exception {
    /**
     * 
     */
    private static final long serialVersionUID = 1489313280178849811L;
    public CardFinishedException () {
        super();
    }
    public CardFinishedException (String m) {
        super(m);
    }
}
