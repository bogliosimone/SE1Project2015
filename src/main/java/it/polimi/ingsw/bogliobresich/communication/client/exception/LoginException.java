/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.client.exception;

/**
 * @author matteobresich
 *
 */
public class LoginException extends Exception{
    /**
     * 
     */
    private static final long serialVersionUID = -1277805594265889630L;
    public LoginException () {
        super();
    }
    public LoginException (String m) {
        super(m);
    }
}
