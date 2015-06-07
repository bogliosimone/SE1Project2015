/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

/**
 * @author matteobresich
 *
 */
public class WaitingRoomFullException extends Exception {
    public WaitingRoomFullException() {
        super();
    }
    public WaitingRoomFullException(String msg) {
        super(msg);
    }
}
