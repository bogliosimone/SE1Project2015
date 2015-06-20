package it.polimi.ingsw.bogliobresich.GUI.loginView;
import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.GuiConstants;
import it.polimi.ingsw.bogliobresich.GUI.View;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        setTitle(GuiConstants.LOGIN_TITLE);
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
                JButton cancelButton = new JButton("Esci");
                cancelButton.setActionCommand("Cancel");
                buttonPane.add(cancelButton);
            }
            {
                JButton doLogin = new JButton("Login");
                doLogin.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nickname = textField.getText();
                        String password = passwordField.getPassword().toString();
                        GUIController.doLogin(nickname, password);
                    }
                });
                doLogin.setActionCommand("OK");
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
}
