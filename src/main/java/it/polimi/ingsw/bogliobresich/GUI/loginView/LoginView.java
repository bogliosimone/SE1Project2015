package it.polimi.ingsw.bogliobresich.GUI.loginView;
import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;


public class LoginView extends JDialog implements View {

    private final JPanel contentPanel = new JPanel();
    private JPasswordField passwordField;
    private JTextField textField;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            LoginView dialog = new LoginView();
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public LoginView() {
        setResizable(false);
        setTitle(GUIConstants.LOGIN_TITLE);
        setBounds(100, 100, 320, 165);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        passwordField = new JPasswordField();
        passwordField.setBounds(110, 55, 180, 28);
        contentPanel.add(passwordField);
        
        textField = new JTextField();
        textField.setBounds(110, 21, 180, 28);
        contentPanel.add(textField);
        textField.setColumns(10);
        
        JLabel lblNickname = new JLabel("Nickname:");
        lblNickname.setBounds(34, 27, 70, 16);
        contentPanel.add(lblNickname);
        
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setBounds(44, 61, 63, 16);
        contentPanel.add(lblPassword);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton exitButton = new JButton("Esci");
                exitButton.addActionListener(new CloseListener());
                buttonPane.add(exitButton);
            }
            {
                JButton doLogin = new JButton("Login");
                doLogin.addActionListener(new LoginButtonListener(textField,passwordField));
                buttonPane.add(doLogin);
                getRootPane().setDefaultButton(doLogin);
            }
        }
    }

    @Override
    public void initView() {
        try {
            this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            this.setLocationRelativeTo(null);
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doUpdate(NotificationMessage notification) {
        //TODO
    }
}
