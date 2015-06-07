/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Vector;

/**
 * @author matteobresich
 *
 */
public class CommunicationUtil {
    
    
    public static final int SOCKET_REQUEST_SERVER_TCP_PORT = 4200;
    public static final int RMI_REQUEST_SERVER_TCP_PORT = 4201;
    
    private static final String LOCALHOST_IP_ADDRESS = "127.0.0.1";
    private static String localIPAddress = null;
    
    
    public static int N_MIN_PLAYERS_TO_START = 2;
    
    
    public static int WAITING_TIMER_RESCHEDULE_EVERY_X_MILLISECONDS = 10*1000;
    public static int WAITING_TIMER_START_SCHEDULE = 0;
    
    
    public static String getLocalIp() {
        if(localIPAddress == null) {
            try {
                localIPAddress = InetAddress.getLocalHost().getHostAddress () ;
            } catch (UnknownHostException e) {
                localIPAddress = LOCALHOST_IP_ADDRESS;
                e.printStackTrace();
            }
        }
        return localIPAddress;
    }
    
    private CommunicationUtil (){
        //not called
    }
}
