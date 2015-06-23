package it.polimi.ingsw.bogliobresich.GUI.winnersView;

import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WinnersView extends JFrame implements View {
    
    private ImagesHolder imagesHolder = ImagesHolder.getInstance();

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    WinnersView frame = new WinnersView();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public WinnersView() {
        setTitle("Winners");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setForeground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);
        
        JLabel lblNickname = new JLabel("Giocatore:");
        lblNickname.setForeground(Color.WHITE);
        lblNickname.setBounds(172, 30, 70, 16);
        contentPane.add(lblNickname);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(100, 20, 60, 60);
        lblNewLabel.setIcon(imagesHolder.getCaptain());
        contentPane.add(lblNewLabel);
        
        JLabel lblWinner = new JLabel("WINNER");
        lblWinner.setForeground(new Color(51, 204, 51));
        lblWinner.setBounds(30, 40, 61, 16);
        contentPane.add(lblWinner);
        
        JLabel lblNome = new JLabel("nome");
        lblNome.setForeground(Color.WHITE);
        lblNome.setBounds(290, 30, 71, 16);
        contentPane.add(lblNome);
        
        JLabel lblPlayer = new JLabel("Personaggio:");
        lblPlayer.setForeground(Color.WHITE);
        lblPlayer.setBounds(172, 50, 89, 16);
        contentPane.add(lblPlayer);
        
        JLabel lblTuccioBrendon = new JLabel("Tuccio Brendon");
        lblTuccioBrendon.setForeground(Color.WHITE);
        lblTuccioBrendon.setBounds(290, 50, 178, 16);
        contentPane.add(lblTuccioBrendon);
        
        JLabel lblLoser = new JLabel("LOSER");
        lblLoser.setForeground(new Color(204, 0, 0));
        lblLoser.setBounds(30, 120, 61, 16);
        contentPane.add(lblLoser);
        
        JLabel label_1 = new JLabel("");
        label_1.setBounds(100, 100, 60, 60);
        label_1.setIcon(imagesHolder.getFourthAlien());
        contentPane.add(label_1);
        
        JLabel label_2 = new JLabel("Giocatore:");
        label_2.setForeground(Color.WHITE);
        label_2.setBounds(172, 110, 70, 16);
        contentPane.add(label_2);
        
        JLabel label_3 = new JLabel("Personaggio:");
        label_3.setForeground(Color.WHITE);
        label_3.setBounds(172, 130, 89, 16);
        contentPane.add(label_3);
        
        JLabel label_4 = new JLabel("Tuccio Brendon");
        label_4.setForeground(Color.WHITE);
        label_4.setBounds(290, 130, 178, 16);
        contentPane.add(label_4);
        
        JLabel label_5 = new JLabel("nome");
        label_5.setForeground(Color.WHITE);
        label_5.setBounds(290, 110, 71, 16);
        contentPane.add(label_5);
    }

    @Override
    public void initView() {
        // TODO Auto-generated method stub
        
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
                    sleep(GUIConstants.WINNERS_WAIT_BEFORE_DISPOSE);
                } catch (InterruptedException e) {

                }
            }
        };
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {

        }
        super.dispose();
    }
}
