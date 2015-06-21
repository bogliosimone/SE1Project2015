package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;
import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.map.ConstantMap;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;


public class GameBoardView extends JFrame implements View{

    private JTextArea txtMessagesArea;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameBoardView window = new GameBoardView();
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
    public GameBoardView() {
        setResizable(false);
        initialize();
    }
    

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        
        getContentPane().setBackground(Color.BLACK);
        setTitle(GUIConstants.APP_TITLE);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setAlwaysOnTop(true);
        setBounds(100, 100, 1280, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);
        
        JPanel panel = new HexagonMapPanel(ConstantMap.NAMEFILEMAP);
        panel.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel.setForeground(Color.LIGHT_GRAY);
        panel.setBounds(60, 18, 676, 540);
        getContentPane().add(panel);
        
        JPanel command_panel = new JPanel();
        command_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        command_panel.setBounds(814, 6, 460, 686);
        getContentPane().add(command_panel);
        command_panel.setLayout(null);
        
        JLabel lblUtenti = new JLabel("Utenti:");
        lblUtenti.setBounds(86, 50, 61, 16);
        command_panel.add(lblUtenti);
        lblUtenti.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        JLabel lblNewLabel = new JLabel("1 - Simone [Is playing...]");
        lblNewLabel.setBounds(86, 75, 300, 15);
        command_panel.add(lblNewLabel);
        lblNewLabel.setForeground(Color.GREEN);
        
        JLabel lblMatteo = new JLabel("2 - Matteo");
        lblMatteo.setBounds(86, 95, 300, 15);
        command_panel.add(lblMatteo);
        lblMatteo.setForeground(Color.BLACK);
        
        JLabel lblAntonio = new JLabel("3 - Antonio [Dead]");
        lblAntonio.setBounds(86, 115, 300, 15);
        command_panel.add(lblAntonio);
        lblAntonio.setForeground(Color.RED);
        
        JLabel lblChiara = new JLabel("4 - Chiara [Disconnected]");
        lblChiara.setBounds(86, 135, 300, 15);
        command_panel.add(lblChiara);
        lblChiara.setForeground(Color.GRAY);
        
        JLabel lblno = new JLabel("5 - [Missing User]");
        lblno.setBounds(86, 155, 300, 15);
        command_panel.add(lblno);
        lblno.setForeground(Color.GRAY);
        
        JLabel lblmissing = new JLabel("6 - [Missing User]");
        lblmissing.setBounds(86, 175, 300, 15);
        command_panel.add(lblmissing);
        lblmissing.setForeground(Color.GRAY);
        
        JLabel lblmissing_1 = new JLabel("7 - [Missing User]");
        lblmissing_1.setBounds(86, 195, 300, 15);
        command_panel.add(lblmissing_1);
        lblmissing_1.setForeground(Color.GRAY);
        
        JLabel lblmissing_2 = new JLabel("8 - [Missing User]");
        lblmissing_2.setBounds(86, 215, 300, 15);
        command_panel.add(lblmissing_2);
        lblmissing_2.setForeground(Color.GRAY);
        
        JButton btnNewButton = new JButton("");
        btnNewButton.setBounds(86, 382, 64, 64);
        command_panel.add(btnNewButton);
        btnNewButton.setIcon(new ImageIcon("/Users/matteobresich/Google Drive/Ingegneria del Software/img/game_icons/defense_icon.png"));
        
        JButton button = new JButton("");
        button.setBounds(162, 382, 64, 64);
        command_panel.add(button);
        button.setIcon(new ImageIcon("/Users/matteobresich/Google Drive/Ingegneria del Software/img/game_icons/adrenaline_icon.png"));
        
        JButton button_1 = new JButton("");
        button_1.setBounds(238, 382, 64, 64);
        command_panel.add(button_1);
        button_1.setIcon(new ImageIcon("/Users/matteobresich/Google Drive/Ingegneria del Software/img/game_icons/attack_icon.png"));
        
        JButton button_2 = new JButton("");
        button_2.setBounds(314, 382, 64, 64);
        command_panel.add(button_2);
        button_2.setIcon(new ImageIcon("/Users/matteobresich/Google Drive/Ingegneria del Software/img/game_icons/spotlight_icon.png"));
        
        JLabel lblCarteInMano = new JLabel("Carte in mano:");
        lblCarteInMano.setBounds(86, 352, 116, 33);
        command_panel.add(lblCarteInMano);
        
        JLabel lblNicknameSimone = new JLabel("NICKNAME:");
        lblNicknameSimone.setBounds(160, 278, 75, 16);
        command_panel.add(lblNicknameSimone);
        lblNicknameSimone.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        JLabel lblSimone = new JLabel("Simone");
        lblSimone.setBounds(239, 278, 61, 16);
        command_panel.add(lblSimone);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(94, 275, 54, 55);
        command_panel.add(lblNewLabel_1);
        lblNewLabel_1.setIcon(new ImageIcon("/Users/matteobresich/Google Drive/Ingegneria del Software/img/game_icons/the_captain_icon.png"));
        
        JLabel lblPlayer = new JLabel("PLAYER:");
        lblPlayer.setBounds(160, 296, 61, 16);
        command_panel.add(lblPlayer);
        lblPlayer.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        JLabel lblCapitanoTuccioBrendon = new JLabel("Capitano Tuccio Brendon");
        lblCapitanoTuccioBrendon.setBounds(218, 296, 165, 16);
        command_panel.add(lblCapitanoTuccioBrendon);
        
        JLabel lblTurno = new JLabel("TURNO:");
        lblTurno.setBounds(160, 314, 61, 16);
        command_panel.add(lblTurno);
        lblTurno.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        JLabel label = new JLabel("1");
        label.setBounds(216, 314, 61, 16);
        command_panel.add(label);
        
        JSeparator separator = new JSeparator();
        separator.setBounds(28, 264, 403, 16);
        command_panel.add(separator);
        
        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(31, 332, 400, 16);
        command_panel.add(separator_1);
        
        JButton btnGiocaLaCarta = new JButton("Gioca la carta");
        btnGiocaLaCarta.setBounds(86, 464, 145, 30);
        command_panel.add(btnGiocaLaCarta);
        btnGiocaLaCarta.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        
        JButton btnScartaLaCarta = new JButton("Scarta la carta");
        btnScartaLaCarta.setBounds(238, 464, 145, 30);
        command_panel.add(btnScartaLaCarta);
        btnScartaLaCarta.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        
        JButton btnPescaUnaCarta = new JButton("Pesca una carta settore");
        btnPescaUnaCarta.setBounds(86, 494, 145, 30);
        command_panel.add(btnPescaUnaCarta);
        btnPescaUnaCarta.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        
        JLabel lblCartaSettore = new JLabel("Carta settore:");
        lblCartaSettore.setBounds(86, 536, 154, 55);
        command_panel.add(lblCartaSettore);
        lblCartaSettore.setIcon(new ImageIcon("/Users/matteobresich/Google Drive/Ingegneria del Software/img/game_icons/rumor_xy_sector_icon.png"));
        
        JLabel lblRumoreIn = new JLabel("Rumore in XY");
        lblRumoreIn.setBounds(241, 555, 116, 16);
        command_panel.add(lblRumoreIn);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 559, 676, 133);
        getContentPane().add(scrollPane);
        
        txtMessagesArea = new JTextArea();
        scrollPane.setViewportView(txtMessagesArea);
        txtMessagesArea.setText("> testo di prova");
        txtMessagesArea.setBackground(new Color(0, 0, 0));
        txtMessagesArea.setForeground(new Color(0, 102, 255));
        
        
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
            txtMessagesArea.append(notification.getString() + "\n");
        }
    }
}
