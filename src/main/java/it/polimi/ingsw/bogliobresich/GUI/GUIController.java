/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.communication.client.Client;

import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class GUIController implements Observer {
    Client controller = new Client((Observer)this);

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }
}
