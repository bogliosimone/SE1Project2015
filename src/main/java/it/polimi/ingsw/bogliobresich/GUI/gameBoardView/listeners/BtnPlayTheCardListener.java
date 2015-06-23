/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.CommandPanel;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.HexagonMapPanel;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author matteobresich
 *
 */
public class BtnPlayTheCardListener implements ActionListener{

    private HexagonMapPanel map;
    private CommandPanel commandPanel;

    public BtnPlayTheCardListener(HexagonMapPanel map,CommandPanel commandPanel) {
        this.map = map;
        this.commandPanel = commandPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GUIController guiController = GUIController.getInstance();
        ItemHand handOfCards = guiController.getHandOfCards();

        int id = guiController.getIdCardSelected();
        if(id != -1) {
            guiController.setIdCardSelected(-1);
            commandPanel.disableCommandPanel();

            if(handOfCards.getCard(id) instanceof SpotlightItemCard) {
                map.setStateMoveRumorSpotlight(map.STATE_SPOTLIGHT);
                map.setAvaiableAllMoves();
            } else {
                GUIController.sendCommand(new ClientCommand(CommandType.DO_PLAY_ITEM_REQUEST, handOfCards.getCard(id)));
            }
        }
    }
}

