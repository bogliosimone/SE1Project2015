/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author matteobresich
 *
 */
public class BtnAttackListener implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        GUIController.sendCommand(new ClientCommand(CommandType.DO_ATTACK_REQUEST,null));
    }
}
