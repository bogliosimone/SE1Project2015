/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.GameBoardView;
import it.polimi.ingsw.bogliobresich.GUI.loginView.LoginView;
import it.polimi.ingsw.bogliobresich.GUI.logoView.LogoView;
import it.polimi.ingsw.bogliobresich.GUI.waitingRoomView.WaitingRoomView;
import it.polimi.ingsw.bogliobresich.GUI.winnersView.WinnersView;

/**
 * @author matteobresich
 *
 */
public class ViewFactory {

    public View getView(String viewType){
        if(viewType == null){
            return null;
        }     
        if(viewType.equalsIgnoreCase("logo")){
            return new LogoView();
        }
        else if(viewType.equalsIgnoreCase("login")){
            return new LoginView();
        }
        else if(viewType.equalsIgnoreCase("waiting-room")){
            return new WaitingRoomView();
        }
        else if(viewType.equalsIgnoreCase("game-board")){
            return new GameBoardView();
        }
        else if(viewType.equalsIgnoreCase("winners")){
            return new WinnersView();
        }
        
        return null;
    }
}
