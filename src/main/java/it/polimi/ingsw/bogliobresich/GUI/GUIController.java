/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.communication.client.ClientController;

import java.util.Observable;
import java.util.Observer;

/**
 * @author matteobresich
 *
 */
public class GUIController implements Observer, Runnable {
    
    private static GUIController instance;
    private static ClientController controller;
    private View currentView;
    private ViewFactory viewFactory = new ViewFactory();
    
    private GUIController() {
        //Not called
    }
    
    public static synchronized GUIController getInstance() {
        if (instance == null) {
            instance = new GUIController();
        }
        return instance;
    }

    @Override
    public void update(Observable o, Object arg) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void run() {
        initUI();
    }
    
    public void initUI() {
        controller = ClientController.getInstance();
        currentView = viewFactory.getView(GUIViews.LOGIN_VIEW);
        currentView.initView();
        currentView = viewFactory.getView(GUIViews.WAITING_ROOM_VIEW);
        currentView.initView();
    }
    
    public static void doLogin(String nickname, String password) {
        System.out.println(nickname + " " + password);
        //controller.doLogin(nickname, password);
    }
    
}
