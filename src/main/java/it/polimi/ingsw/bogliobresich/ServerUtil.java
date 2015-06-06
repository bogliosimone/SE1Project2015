/**
 * 
 */
package it.polimi.ingsw.bogliobresich;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author matteobresich
 *
 */
public class ServerUtil {
    
    private static final String LOCALHOST_IP_ADDRESS = "127.0.0.1";
    private static String localIPAddress = null;
    
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
}
