/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.CommandPanel;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.HexagonMapPanel;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author matteobresich
 *
 */
public class BtnDiscardTheCardListener implements ActionListener{

    private CommandPanel commandPanel;
    private int idCardSelected = -1;
    private GUIController guiController = GUIController.getInstance();

    public BtnDiscardTheCardListener(CommandPanel commandPanel) {
        this.commandPanel = commandPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ItemHand handOfCards = guiController.getHandOfCards();
        idCardSelected = guiController.getIdCardSelected();

        if(!handOfCards.isEmpty()) {
            if(idCardSelected != -1) {
                commandPanel.disableCommandPanel();
                GUIController.sendCommand(new ClientCommand(CommandType.DO_DISCARD_ITEM_REQUEST,GUIController.getInstance().getHandOfCards().getCard(GUIController.getInstance().getIdCardSelected())));
            }
        }
    }
}

