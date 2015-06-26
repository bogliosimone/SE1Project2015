/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.logoView;

import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.communication.notification.NotificationMessage;

import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author matteobresich
 *
 */
public class LogoView extends JFrame implements View {
    ImagesHolder imagesHolder = ImagesHolder.getInstance();
    
    
    public static void main (String args[]) {
        try {
            LogoView logov = new LogoView();
            logov.setLocationRelativeTo(null);
            logov.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            logov.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public LogoView () {
        this.setBackground(Color.WHITE);
        ImageIcon image = imagesHolder.getLogo();
        JLabel imagelabel = new JLabel(image);
        setLayout(new GridBagLayout());
        add(imagelabel);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(460, 230);
        
    }

    @Override
    public void initView() {
        try {
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doUpdate(NotificationMessage notification) {
        // TODO Auto-generated method stub
        
    }
    @Override
    public void dispose() {
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(GUIConstants.LOGO_WAIT_BEFORE_DISPOSE);
                } catch (InterruptedException e) {
                    //TODO
                }
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            //TODO
        }
        super.dispose();
    }
}
