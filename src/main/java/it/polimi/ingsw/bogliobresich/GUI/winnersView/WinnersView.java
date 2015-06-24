package it.polimi.ingsw.bogliobresich.GUI.winnersView;

import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.CharacterCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class WinnersView extends JFrame implements View {

    private ImagesHolder imagesHolder = ImagesHolder.getInstance();
    private GUIController guiController = GUIController.getInstance();

    private JPanel contentPane;
    
    final Color TEXT = Color.WHITE;
    final Color WINNER = new Color(51, 204, 51);
    final Color LOOSER = new Color(204, 0, 0);
    

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
        setTitle(GUIConstants.WINNERS_TITLE);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 800, 600);
        contentPane = new JPanel();
        contentPane.setBackground(Color.BLACK);
        contentPane.setForeground(TEXT);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setUndecorated(true);
        setLocationRelativeTo(null);


        printPlayerList(guiController.getPlayers());
//        List <Player> p = new ArrayList <Player>();
//        p.add(new Player(new User(1, "matteo", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.CAPTAIN)));
//        p.add(new Player(new User(2, "simone", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.PILOT)));
//        p.add(new Player(new User(3, "chira", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.PSYCHOLOGIST)));
//        p.add(new Player(new User(4, "daniele", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.SOLDIER)));
//        p.add(new Player(new User(5, "mirko", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.ALIENONE)));
//        p.add(new Player(new User(6, "romina", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.ALIENTWO)));
//        p.add(new Player(new User(7, "july", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.ALIENTHREE)));
//        p.add(new Player(new User(8, "simone", "non si sa"), new Coordinate('L',2), new CharacterCard(Characters.ALIENFOUR)));
//        printPlayerList(p);


        JButton btnOk = new JButton("Esci");
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnOk.setBounds(694, 565, 100, 29);
        contentPane.add(btnOk);
    }

    public void printPlayerList(List <Player> playerList) {
        int index = 0;
        for(Player player : playerList) {
            if(player.isWinner()) {
                JLabel lblWinner = new JLabel("WINNER");
                lblWinner.setForeground(WINNER);
                lblWinner.setBounds(30, 40+65*index, 61, 16);
                contentPane.add(lblWinner);
            } else {
                JLabel lblLoser = new JLabel("LOSER");
                lblLoser.setForeground(LOOSER);
                lblLoser.setBounds(30, 40+65*index, 61, 16);
                contentPane.add(lblLoser);
            }
            
            JLabel lblNewLabel = new JLabel();
            lblNewLabel.setBounds(100, 20+65*index, 60, 60);
            lblNewLabel.setIcon(getImageByPlayer(player));
            contentPane.add(lblNewLabel);

            JLabel lblNickname = new JLabel("Giocatore:");
            lblNickname.setForeground(TEXT);
            lblNickname.setBounds(172, 30+65*index, 70, 16);
            contentPane.add(lblNickname);
            
            JLabel lblNome = new JLabel(player.getUser().getNickname());
            lblNome.setForeground(TEXT);
            lblNome.setBounds(290, 30+65*index, 71, 16);
            contentPane.add(lblNome);

            JLabel lblPlayer = new JLabel("Personaggio:");
            lblPlayer.setForeground(TEXT);
            lblPlayer.setBounds(172, 50+65*index, 89, 16);
            contentPane.add(lblPlayer);

            JLabel lblTuccioBrendon = new JLabel(player.getCharacterCard().getCharacterName() + " [ " + player.getCharacterCard().getCharacterType() + " ] ");
            lblTuccioBrendon.setForeground(TEXT);
            lblTuccioBrendon.setBounds(290, 50+65*index, 350, 16);
            contentPane.add(lblTuccioBrendon);
            index++;
        }
    }
    
    public ImageIcon getImageByPlayer(Player player) {
        if(player.getCharacterCard().getCharacterName().equals(Characters.CAPTAIN.getCharacterName())) {
            return imagesHolder.getCaptain();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.PILOT.getCharacterName())) {
            return imagesHolder.getPilot();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.PSYCHOLOGIST.getCharacterName())) {
            return imagesHolder.getPsychologist();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.SOLDIER.getCharacterName())) {
            return imagesHolder.getSoldier();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENONE.getCharacterName())) {
            return imagesHolder.getFirstAlien();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENTWO.getCharacterName())) {
            return imagesHolder.getSecondAlien();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENTHREE.getCharacterName())) {
            return imagesHolder.getThirdAlien();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENFOUR.getCharacterName())) {
            return imagesHolder.getFourthAlien();
        }
        return imagesHolder.getCaptain();
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
