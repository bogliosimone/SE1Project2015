/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.waitingRoomView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author matteobresich
 *
 */
public class CloseListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}