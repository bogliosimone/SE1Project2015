package it.polimi.ingsw.bogliobresich.GUI.waitingRoomView;

import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.communication.notification.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.commands.Commands;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



public class WaitingRoomView extends JFrame implements View {

    private JTextArea textArea;
    private JFrame frmSalaDattesa;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WaitingRoomView window = new WaitingRoomView();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public WaitingRoomView() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        setLocationRelativeTo(null);
        setTitle(GUIConstants.WAITING_ROOM_TITLE);
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel contentPanel = new JPanel();
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 6, 438, 227);
        contentPanel.add(scrollPane);
        
        textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton(GUIConstants.EXIT_LABEL);
        btnNewButton.addActionListener(new CloseListener());
        buttonPane.add(btnNewButton);
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
        if(notification.getCommand() == Commands.GENERIC_MESSAGE) {
            textArea.append(notification.getString() + "\n");
        }
    }
    @Override
    public void dispose() {
        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(GUIConstants.WAITING_ROOM_WAIT_BEFORE_DISPOSE);
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
