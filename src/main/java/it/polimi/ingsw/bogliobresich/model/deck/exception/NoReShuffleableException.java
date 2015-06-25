package it.polimi.ingsw.bogliobresich.model.deck.exception;

/**
 * This class is thrown if a deck can't be reshuffled.
 * @author matteo bresich
 * @author simone boglio
 * @version 1.0 
 * @see java.lang.Exception
 */
public class NoReShuffleableException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -615390806944221762L;
    public NoReShuffleableException () {
        super();
    }
    public NoReShuffleableException (String m) {
        super (m);
    }
}
