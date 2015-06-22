package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;
import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class MessageView extends JDialog {

    private final JPanel contentPanel = new JPanel();
    static ImagesHolder imagesHolder = ImagesHolder.getInstance();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            MessageView dialog = new MessageView("Complimenti hai pescato un porthole verde!", imagesHolder.getGreenPorthole());
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            dialog.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Create the dialog.
     */
    public MessageView(String message, ImageIcon image) {
        setTitle(GUIConstants.MESSAGE_TITLE);
        setBounds(100, 100, 450, 180);
        getContentPane().setLayout(new BorderLayout());
        contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JLabel label = new JLabel("");
        label.setIcon(image);
        label.setBounds(20, 35, 59, 60);
        contentPanel.add(label);
        
        JLabel lblRumoreNelTuo = new JLabel(message);
        lblRumoreNelTuo.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblRumoreNelTuo.setBounds(91, 35, 340, 60);
        contentPanel.add(lblRumoreNelTuo);
        {
            JPanel buttonPane = new JPanel();
            buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
            getContentPane().add(buttonPane, BorderLayout.SOUTH);
            {
                JButton okButton = new JButton("OK");
                okButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                    }
                });
                okButton.setActionCommand("OK");
                buttonPane.add(okButton);
                getRootPane().setDefaultButton(okButton);
            }
        }
    }
}
