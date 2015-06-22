/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author matteobresich
 *
 */
public class BtnCardListener implements ActionListener{

    private int id;
    public BtnCardListener(int idCard) {
        this.id = idCard;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        GUIController.getInstance().setIdCardSelected(id);
    }
}
