package it.polimi.ingsw.bogliobresich.model.deck.exception;

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
