package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;
import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.map.ConstantMap;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;


public class GameBoardView extends JFrame implements View{


    private static final int BLINK_STEP = 500;
    private ImagesHolder imagesHolder = new ImagesHolder();
    private JPanel command_panel;
    private JTextArea txtMessagesArea;
    private User[] userList;
    private JLabel labelTurnNumber;
    private JButton btnPlayTheCard;
    private JButton btnDrawSectorCard;
    private JButton btnDiscardTheCard;
    private JButton btnAttack;
    private JLabel lblCurrentPosition;
    private JLabel lblPlayerName;
    private JLabel lblPlayerState;
    private JLabel lblPlayerNickname;

    private JLabel[] labelUsers = new JLabel[ConstantMatch.MAXPLAYERS];

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

        JPanel map = new HexagonMapPanel(ConstantMap.NAMEFILEMAP);
        map.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        map.setForeground(Color.LIGHT_GRAY);
        map.setBounds(60, 18, 676, 540);
        getContentPane().add(map);

        command_panel = new JPanel();
        command_panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        command_panel.setBounds(814, 6, 460, 686);
        getContentPane().add(command_panel);
        command_panel.setLayout(null);

        JLabel lblUtenti = new JLabel("Utenti:");
        lblUtenti.setBounds(86, 50, 61, 16);
        command_panel.add(lblUtenti);
        lblUtenti.setFont(new Font("Lucida Grande", Font.BOLD, 13));




        JButton btnNewButton = new JButton("");
        btnNewButton.setBounds(86, 401, 64, 64);
        command_panel.add(btnNewButton);
        btnNewButton.setIcon(imagesHolder.getAdrenaline());

        JButton button = new JButton("");
        button.setBounds(162, 401, 64, 64);
        command_panel.add(button);
        button.setIcon(imagesHolder.getAdrenaline());

        JButton button_1 = new JButton("");
        button_1.setBounds(238, 401, 64, 64);
        command_panel.add(button_1);
        button_1.setIcon(imagesHolder.getAdrenaline());

        JButton button_2 = new JButton("");
        button_2.setBounds(314, 401, 64, 64);
        command_panel.add(button_2);
        button_2.setIcon(imagesHolder.getAdrenaline());

        JLabel lblCarteInMano = new JLabel("Carte in mano:");
        lblCarteInMano.setBounds(86, 371, 116, 33);
        command_panel.add(lblCarteInMano);

        JLabel lblNickname = new JLabel("NICKNAME:");
        lblNickname.setBounds(162, 278, 75, 16);
        command_panel.add(lblNickname);
        lblNickname.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        lblPlayerNickname = new JLabel();
        lblPlayerNickname.setBounds(238, 278, 180, 16);
        command_panel.add(lblPlayerNickname);

        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setBounds(94, 275, 54, 55);
        command_panel.add(lblNewLabel_1);
        lblNewLabel_1.setIcon(imagesHolder.getPilot());

        JLabel lblPlayer = new JLabel("PLAYER:");
        lblPlayer.setBounds(162, 296, 61, 16);
        command_panel.add(lblPlayer);
        lblPlayer.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        lblPlayerName = new JLabel("Capitano Tuccio Brendon");
        lblPlayerName.setBounds(218, 296, 200, 16);
        command_panel.add(lblPlayerName);

        JLabel lblState = new JLabel("STATE:");
        lblState.setBounds(162, 314, 54, 16);
        command_panel.add(lblState);
        lblState.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        labelTurnNumber = new JLabel();
        labelTurnNumber.setBounds(218, 333, 200, 16);
        command_panel.add(labelTurnNumber);

        JSeparator separator = new JSeparator();
        separator.setBounds(28, 264, 403, 16);
        command_panel.add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(31, 365, 400, 16);
        command_panel.add(separator_1);

        btnPlayTheCard = new JButton("Gioca la carta");
        btnPlayTheCard.setBounds(86, 483, 145, 30);
        command_panel.add(btnPlayTheCard);
        btnPlayTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDiscardTheCard = new JButton("Scarta la carta");
        btnDiscardTheCard.setBounds(238, 483, 145, 30);
        command_panel.add(btnDiscardTheCard);
        btnDiscardTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDrawSectorCard = new JButton("Pesca una carta settore");
        btnDrawSectorCard.setBounds(86, 513, 145, 30);
        command_panel.add(btnDrawSectorCard);
        btnDrawSectorCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        JLabel lblCartaSettore = new JLabel("Carta settore:");
        lblCartaSettore.setBounds(86, 590, 154, 55);
        command_panel.add(lblCartaSettore);
        lblCartaSettore.setIcon(imagesHolder.getRumorXY());

        JLabel lblRumoreIn = new JLabel("Rumore in XY");
        lblRumoreIn.setBounds(241, 609, 116, 16);
        command_panel.add(lblRumoreIn);

        JLabel lblTurno = new JLabel("TURNO:");
        lblTurno.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblTurno.setBounds(162, 332, 54, 16);
        command_panel.add(lblTurno);
        
        btnAttack = new JButton("Attacca");
        btnAttack.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnAttack.setBounds(238, 513, 145, 30);
        command_panel.add(btnAttack);
        
        JLabel lblPosizioneCorrente = new JLabel("POSIZIONE CORRENTE:");
        lblPosizioneCorrente.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblPosizioneCorrente.setBounds(162, 350, 154, 16);
        command_panel.add(lblPosizioneCorrente);
        
        lblPlayerState = new JLabel("Human");
        lblPlayerState.setBounds(209, 314, 209, 16);
        command_panel.add(lblPlayerState);
        
        lblCurrentPosition = new JLabel("");
        lblCurrentPosition.setBounds(317, 350, 101, 16);
        command_panel.add(lblCurrentPosition);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 559, 676, 133);
        getContentPane().add(scrollPane);

        txtMessagesArea = new JTextArea();
        scrollPane.setViewportView(txtMessagesArea);
        txtMessagesArea.setBackground(new Color(0, 0, 0));
        txtMessagesArea.setForeground(new Color(0, 102, 255));
        
        printUserList(null);
        disableCommandPanel();
        

        new Thread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    while(true)
                    {
                        txtMessagesArea.append("_");
                        Thread.sleep(BLINK_STEP);
                        String appo = txtMessagesArea.getText().substring(0,txtMessagesArea.getText().length() - 1);
                        txtMessagesArea.setText(appo);
                        Thread.sleep(BLINK_STEP);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
    
    public void disableCommandPanel() {
        btnPlayTheCard.setEnabled(false);
        btnDrawSectorCard.setEnabled(false);
        btnDiscardTheCard.setEnabled(false);
        btnAttack.setEnabled(false);
    }

    public synchronized void printMessage(String msg) {
        txtMessagesArea.append("> " + msg + "\n");
    }

    public void printUserList(User[] list) {
        int index = 0;
        if(list != null) {
            for(User user : list) {
                labelUsers[index] = new JLabel((index+1) + " - ");
                labelUsers[index].setBounds(86, 75+20*index, 300, 15);
                command_panel.add(labelUsers[index]);
                labelUsers[index].setForeground(Color.BLACK);
                index++;
            }
        }
        for(int remainderUsers = index; remainderUsers < ConstantMatch.MAXPLAYERS; remainderUsers++) {
            labelUsers[remainderUsers] = new JLabel((remainderUsers+1) + " - [Missing User]");
            labelUsers[remainderUsers].setBounds(86, 75+20*remainderUsers, 300, 15);
            command_panel.add(labelUsers[remainderUsers]);
            labelUsers[remainderUsers].setForeground(Color.GRAY);
        }
    }

    public void printMyCoordinate(Coordinate c) {
        lblCurrentPosition.setText(c.toString());
    }
    
    public void printCurrentTurnNumber(Integer turnNumber) {
        labelTurnNumber.setText(turnNumber.toString());
    }
    
    public void printPlayer(Player player) {
        lblPlayerName.setText(player.getCharacterCard().getCharacterName());
        lblPlayerState.setText(player.getCharacterCard().getCharacterType());
        
        //NON OTTIENE IL NICKNAME
        //lblPlayerNickname.setText(player.getUser().getNickname());
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
        switch (notification.getCommand()) {
        case ALL_PLAYERS_MESSAGE:
            printMessage(notification.getString());
            break;
        case ATTACK:
            break;
        case CALL_RUMOR:
            break;
        case CANT_DISCARD_CARD:
            break;
        case CANT_PLAY_CARD:
            break;
        case CARDS_END:
            break;
        case COORDINATE_ERROR:
            break;
        case DISCARD_CARD:
            break;
        case DISCARD_HAND:
            break;
        case DRAW_CARD:
            break;
        case DRAW_SECTOR_CARD:
            break;
        case END_TURN:
            break;
        case FATAL_ERROR:
            break;
        case GAME_END:
            break;
        case GAME_INFO_MESSAGE:
            printMessage(notification.getString());
            break;
        case GAME_START:
            break;
        case GENERIC_ERROR:
            break;
        case GENERIC_MESSAGE:
            break;
        case HAND_FULL:
            break;
        case IS_NOT_YOUR_TURN:
            break;
        case ITEM_PLAYED:
            break;
        case LIST_USERS:
            userList = (User[]) notification.getListOfUsers().toArray();
            printUserList(userList);
            break;
        case MOVES_AVAIABLE:
            break;
        case MOVE_NO_AVAIABLE:
            break;
        case PLAYER_COMMAND:
            break;
        case PLAYER_JOIN_WAIT_ROOM:
            break;
        case PLAYER_MESSAGE:
            break;
        case PORTHOLE_BROKEN:
            break;
        case SECTOR_TYPE_MESSAGE:
            break;
        case SERVER_NOT_RESPONDING:
            break;
        case SET_YOUR_COORDINATE:
            printMyCoordinate(notification.getCoordinate());
            break;
        case START_END_PHASE:
            break;
        case START_MOVEMENT_PHASE:
            break;
        case START_TIMER:
            break;
        case START_TURN:
            printCurrentTurnNumber(notification.getInteger());
            break;
        case USER_END_IS_GAME:
            break;
        case USER_END_TURN:
            break;
        case USER_START_TURN:
            break;
        case WHO_ARE_YOU:
            printPlayer(notification.getPlayer());
            break;
        case YOU_ARE_FEED:
            break;
        case YOU_DIE:
            break;
        case YOU_DISCONNECTED:
            break;
        case YOU_LOST:
            break;
        case YOU_WIN:
            break;
        default:
            break;

        }
    }
}
