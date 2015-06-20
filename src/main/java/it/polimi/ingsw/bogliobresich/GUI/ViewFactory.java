/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.GUI.loginView.LoginView;
import it.polimi.ingsw.bogliobresich.GUI.waitingRoomView.WaitingRoomView;

/**
 * @author matteobresich
 *
 */
public class ViewFactory {

    public View getView(String viewType){
        if(viewType == null){
            return null;
        }     
        if(viewType.equalsIgnoreCase("login")){
            return new LoginView();

        }
        else if(viewType.equalsIgnoreCase("waiting-room")){
            return new WaitingRoomView();
        }
        else if(viewType.equalsIgnoreCase("game-board")){
            //return new GameBoardView();
        }
        
        
        return null;
    }
}
