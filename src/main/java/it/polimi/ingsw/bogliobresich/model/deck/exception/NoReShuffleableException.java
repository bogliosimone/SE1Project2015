package it.polimi.ingsw.bogliobresich.model.deck.exception;

public class NoReShuffleableException extends RuntimeException {
    public NoReShuffleableException () {
        super();
    }
    public NoReShuffleableException (String m) {
        super (m);
    }
}
