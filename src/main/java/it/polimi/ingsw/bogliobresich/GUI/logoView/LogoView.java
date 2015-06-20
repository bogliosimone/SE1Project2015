/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.logoView;

import it.polimi.ingsw.bogliobresich.FilePaths;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.awt.GridBagLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * @author matteobresich
 *
 */
public class LogoView extends JFrame implements View {
    
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
        ImageIcon image = new ImageIcon(FilePaths.PRODUCER_LOGO);
        JLabel imagelabel = new JLabel(image);
        setLayout(new GridBagLayout());
        add(imagelabel);
        setAlwaysOnTop(true);
        setUndecorated(true);
        setSize(533, 300);
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

}
