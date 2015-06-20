/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.loginView;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * @author matteobresich
 *
 */
public class LoginButtonListener implements ActionListener {
    
    private JPasswordField passwordField;
    private JTextField textField;
    
    public LoginButtonListener(JTextField textField, JPasswordField passwordField) {
        this.textField = textField;
        this.passwordField = passwordField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nickname = textField.getText();
        String password = new String(passwordField.getPassword());
        GUIController.doLogin(nickname, password);
    }
}
