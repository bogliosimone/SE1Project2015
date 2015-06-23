/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.CommandPanel;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author matteobresich
 *
 */
public class BtnEndMovementListener implements ActionListener{

    private CommandPanel commandPanel;
    public BtnEndMovementListener(CommandPanel commandPanel) {
        this.commandPanel = commandPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commandPanel.disableCommandPanel();
        GUIController.sendCommand(new ClientCommand(CommandType.DO_END_MOVEMENT_PHASE_REQUEST,null));
    }
}

