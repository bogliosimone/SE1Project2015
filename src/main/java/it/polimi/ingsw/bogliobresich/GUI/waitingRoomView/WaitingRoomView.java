package it.polimi.ingsw.bogliobresich.GUI.waitingRoomView;
import it.polimi.ingsw.bogliobresich.GUI.GuiConstants;
import it.polimi.ingsw.bogliobresich.GUI.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class WaitingRoomView extends JFrame implements View {

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
        
        setTitle(GuiConstants.WAITING_ROOM_TITLE);
        setBounds(100, 100, 450, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        
        JPanel contentPanel = new JPanel();
        getContentPane().add(contentPanel, BorderLayout.CENTER);
        contentPanel.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(6, 6, 438, 227);
        contentPanel.add(scrollPane);
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);
        
        JPanel buttonPane = new JPanel();
        getContentPane().add(buttonPane, BorderLayout.SOUTH);
        
        JButton btnNewButton = new JButton(GuiConstants.EXIT_LABEL);
        buttonPane.add(btnNewButton);
    }

    @Override
    public void initView() {
        setVisible(true);
    }
}
