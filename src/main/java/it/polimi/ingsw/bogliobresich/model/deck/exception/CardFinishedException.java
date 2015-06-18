package it.polimi.ingsw.bogliobresich.model.deck.exception;

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
