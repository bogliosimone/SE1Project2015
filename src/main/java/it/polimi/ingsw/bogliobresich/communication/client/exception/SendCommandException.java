/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client.exception;

/**
 * @author matteobresich
 *
 */
public class SendCommandException extends Exception {
    
    /**
     * 
     */
    private static final long serialVersionUID = -2357388193676769233L;
    public SendCommandException() {
        super();
    }
    public SendCommandException(String m) {
        super(m);
    }

}
