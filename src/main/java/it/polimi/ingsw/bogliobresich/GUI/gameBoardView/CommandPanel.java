/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnAttackListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnCardListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnDiscardTheCardListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnDrawSectorCardListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnEndMovementListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnEndTurnListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnPlayTheCardListener;
import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.cards.AdrenalineItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.AttackItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.DefenceItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SedativesItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.TeleportItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.ItemHand;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.awt.Color;
import java.awt.Font;
import java.awt.TextArea;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

/**
 * @author matteobresich
 *
 */
public class CommandPanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -8666358068178077903L;
    private final static Color MISSING_USER = Color.GRAY;
    private final static Color CONNECTED_USER = Color.WHITE;
    private final static Color PLAYING_USER = Color.GREEN;
    private final static Color PLAYER_INFO = Color.WHITE;
    private final static Color OTHER_MESSAGES_AREA = Color.BLACK;
    private final static Color OTHER_MESSAGES_TEXT = Color.GRAY;
    private static final Color BUTTON_TEXT = new Color(0, 102, 255);


    private ImagesHolder imagesHolder = ImagesHolder.getInstance();

    private JButton[] btnCards = new JButton[ConstantMatch.MAXCARDINHAND];
    private JButton btnPlayTheCard;
    private JButton btnDrawSectorCard;
    private JButton btnDiscardTheCard;
    private JButton btnAttack;
    private JButton btnEndMovement;
    private JButton btnEndTurn;

    private JLabel[] labelUsers = new JLabel[ConstantMatch.MAXPLAYERS];
    private JLabel labelTurnNumber;
    private JLabel lblCurrentPosition;
    private JLabel lblUserNickname;
    private JLabel lblPlayerName;
    private JLabel lblPlayerState;
    private JLabel lblPlayerIcon;
    private TextArea lblOtherMessages;
    private JLabel lblPhaseTurnMessage;

    private BtnCardListener [] cardListeners;

    private HexagonMapPanel map;

    public CommandPanel(HexagonMapPanel map) {


        this.map = map;
        
        lblPhaseTurnMessage = new JLabel("SEI NELLA PHASE BHA");
        lblPhaseTurnMessage.setBounds(86, 20, 200, 16);
        lblPhaseTurnMessage.setForeground(PLAYER_INFO);
        add(lblPhaseTurnMessage);
        lblPhaseTurnMessage.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        
        JLabel lblUtenti = new JLabel("Utenti:");
        lblUtenti.setBounds(86, 50, 61, 16);
        lblUtenti.setForeground(PLAYER_INFO);
        add(lblUtenti);
        lblUtenti.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        JSeparator separator = new JSeparator();
        separator.setBounds(28, 264, 403, 16);
        add(separator);



        lblPlayerIcon = new JLabel();
        lblPlayerIcon.setBounds(94, 300, 54, 55);
        add(lblPlayerIcon);

        
        JLabel lblNickname = new JLabel("NICKNAME:");
        lblNickname.setBounds(162, 278, 80, 16);
        lblNickname.setForeground(PLAYER_INFO);
        add(lblNickname);
        lblNickname.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        lblUserNickname = new JLabel();
        lblUserNickname.setBounds(242, 278, 200, 16);
        lblUserNickname.setForeground(PLAYER_INFO);
        add(lblUserNickname);
        lblUserNickname.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        
        JLabel lblPlayer = new JLabel("PLAYER:");
        lblPlayer.setBounds(162, 296, 61, 16);
        lblPlayer.setForeground(PLAYER_INFO);
        add(lblPlayer);
        lblPlayer.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        lblPlayerName = new JLabel();
        lblPlayerName.setBounds(218, 296, 200, 16);
        lblPlayerName.setForeground(PLAYER_INFO);
        add(lblPlayerName);

        JLabel lblState = new JLabel("STATE:");
        lblState.setBounds(162, 314, 54, 16);
        lblState.setForeground(PLAYER_INFO);
        add(lblState);
        lblState.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        lblPlayerState = new JLabel();
        lblPlayerState.setBounds(209, 314, 209, 16);
        lblPlayerState.setForeground(PLAYER_INFO);
        add(lblPlayerState);

        JLabel lblTurno = new JLabel("TURNO:");
        lblTurno.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblTurno.setBounds(162, 332, 54, 16);
        lblTurno.setForeground(PLAYER_INFO);
        add(lblTurno);

        labelTurnNumber = new JLabel();
        labelTurnNumber.setBounds(218, 333, 200, 16);
        labelTurnNumber.setForeground(PLAYER_INFO);
        add(labelTurnNumber);

        JLabel lblPosizioneCorrente = new JLabel("POSIZIONE CORRENTE:");
        lblPosizioneCorrente.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblPosizioneCorrente.setBounds(162, 350, 154, 16);
        lblPosizioneCorrente.setForeground(PLAYER_INFO);
        add(lblPosizioneCorrente);

        lblCurrentPosition = new JLabel("");
        lblCurrentPosition.setBounds(317, 350, 101, 16);
        lblCurrentPosition.setForeground(PLAYER_INFO);
        add(lblCurrentPosition);




        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(31, 365, 400, 16);
        add(separator_1);



        JLabel lblCarteInMano = new JLabel("Carte in mano:");
        lblCarteInMano.setBounds(86, 371, 116, 33);
        add(lblCarteInMano);



        btnPlayTheCard = new JButton("Gioca la carta");
        btnPlayTheCard.setBounds(86, 483, 145, 30);
        btnPlayTheCard.addActionListener(new BtnPlayTheCardListener(map,this));
        add(btnPlayTheCard);
        btnPlayTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDiscardTheCard = new JButton("Scarta la carta");
        btnDiscardTheCard.setBounds(238, 483, 145, 30);
        btnPlayTheCard.addActionListener(new BtnDiscardTheCardListener(this));
        add(btnDiscardTheCard);
        btnDiscardTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDrawSectorCard = new JButton("Pesca una carta settore");
        btnDrawSectorCard.setBounds(86, 513, 145, 30);
        btnDrawSectorCard.addActionListener(new BtnDrawSectorCardListener(this));
        add(btnDrawSectorCard);
        btnDrawSectorCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnAttack = new JButton("Attacca");
        btnAttack.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnAttack.setBounds(238, 513, 145, 30);
        btnAttack.addActionListener(new BtnAttackListener(this));
        add(btnAttack);

        btnEndMovement = new JButton("Fine movimento");
        btnEndMovement.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnEndMovement.setBounds(86, 543, 145, 30);
        btnEndMovement.addActionListener(new BtnEndMovementListener(this));
        add(btnEndMovement);

        btnEndTurn = new JButton("Fine turno");
        btnEndTurn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnEndTurn.setBounds(238, 543, 145, 30);
        btnEndTurn.addActionListener(new BtnEndTurnListener(this));
        add(btnEndTurn);

        lblOtherMessages = new TextArea();
        lblOtherMessages.setFont(new Font("Lucida Grande", Font.PLAIN, 12));
        lblOtherMessages.setBounds(86, 580, 297, 200);
        lblOtherMessages.setBackground(OTHER_MESSAGES_AREA);
        lblOtherMessages.setForeground(OTHER_MESSAGES_TEXT);
        lblOtherMessages.setEditable(false);
        add(lblOtherMessages);
    }



    public void disableCommandPanel() {
        setBtnPlayTheCardEnabled(false);
        setBtnDrawSectorCardEnabled(false);
        setBtnDiscardTheCardEnabled(false);
        setBtnAttackEnabled(false);
        setBtnEndMovementEnabled(false);
        setBtnEndTurnEnabled(false);
        setCardsEnabled(false);
    }

    public void setCardsEnabled(boolean b) {
        if(b == true) {
            for(int index = 0; index < btnCards.length; index++) {
                if(btnCards[index].getActionListeners().length > 0) {
                    btnCards[index].setEnabled(true);
                }
            }
        } else {
            for(int index = 0; index < btnCards.length; index++) {
                btnCards[index].setEnabled(false);
            }
        }
    }

    public void setBtnPlayTheCardEnabled(boolean b) {
        btnPlayTheCard.setEnabled(b);
    }

    public void setBtnDrawSectorCardEnabled(boolean b) {
        btnDrawSectorCard.setEnabled(b);
    }

    public void setBtnDiscardTheCardEnabled(boolean b) {
        btnDiscardTheCard.setEnabled(b);
    }

    public void setBtnAttackEnabled(boolean b) {
        btnAttack.setEnabled(b);
    }

    public void setBtnEndMovementEnabled(boolean b) {
        btnEndMovement.setEnabled(b);
    }

    public void setBtnEndTurnEnabled(boolean b) {
        btnEndTurn.setEnabled(b);
    }

    public void printOtherMessage(String msg) {
        lblOtherMessages.setText(msg);
    }
    
    public void printPhaseTurnMessage(String msg, Color c) {
        lblPhaseTurnMessage.setText(msg);
        lblPhaseTurnMessage.setForeground(c);;
    }

    public void initHand() {
        for(int index = 0; index < ConstantMatch.MAXCARDINHAND; index++) {  
            btnCards[index] = new JButton("");
            btnCards[index].setBounds((86+76*index), 401, 64, 64);
            add(btnCards[index]);
            validate();
        }
        setCardsEnabled(false);
    }

    public void printHand(ItemHand hand) {
        setCardsEnabled(false);
        for(JButton b : btnCards) {
            for(ActionListener al : b.getActionListeners()) {
                b.removeActionListener(al);
            }
            b.setIcon(null);
        }

        List<ItemCard> cards = hand.getAllCard();
        int index = 0;
        for(ItemCard card : cards) {
            //TODO            //REMOVE
            System.out.println(card.getId() + "");
            btnCards[index].setIcon(getImageByItemCard(card));
            btnCards[index].addActionListener(new BtnCardListener(card.getId()));
            add(btnCards[index]);
            validate();
            index++;
        }
    }

    public ImageIcon getImageByItemCard(ItemCard card) {
        if(card instanceof AdrenalineItemCard) {
            return imagesHolder.getAdrenaline();
        } else if(card instanceof AttackItemCard) {
            return imagesHolder.getAttack();
        } else if(card instanceof DefenceItemCard) {
            return imagesHolder.getDefense();
        } else if(card instanceof SedativesItemCard) {
            return imagesHolder.getSedatives();
        } else if(card instanceof SpotlightItemCard) {
            return imagesHolder.getSpotlight();
        } else if(card instanceof TeleportItemCard) {
            return imagesHolder.getTeleport();
        }
        return imagesHolder.getItemIcon();
    }

    public void initAndPrintUserList(List <User> userList, UserListProprieties userListProprieties) {
        if(userListProprieties != null) {
            int index = 0;
            for(User user : userList) {
                String text;
                if(!userListProprieties.getStateByUser(user).isEmpty()) {
                    text = (index+1) + " - " + user.getNickname() + " [ " + userListProprieties.getStateByUser(user) + " ]";
                } else {
                    text = (index+1) + " - " + user.getNickname();
                }
                labelUsers[index] = new JLabel(text);
                labelUsers[index].setBounds(86, 75+20*index, 300, 15);
                add(labelUsers[index]);
                labelUsers[index].setForeground(userListProprieties.getColorByUser(user));
                index++;
            }
        }
    }

    public void printUserList(List <User> userList, UserListProprieties userListProprieties) {
        if(userListProprieties != null) {
            int index = 0;
            for(User user : userList) {
                String text;
                if(!userListProprieties.getStateByUser(user).isEmpty()) {
                    text = (index+1) + " - " + user.getNickname() + " [ " + userListProprieties.getStateByUser(user) + " ]";
                } else {
                    text = (index+1) + " - " + user.getNickname();
                }
                labelUsers[index].setText(text);
                labelUsers[index].setForeground(userListProprieties.getColorByUser(user));
                index++;
            }
        }
    }

    public void printMyCoordinate(Coordinate c) {
        lblCurrentPosition.setText(c.toString());
    }

    public void printCurrentTurnNumber(Integer turnNumber) {
        labelTurnNumber.setText(turnNumber.toString());
    }

    public void printPlayer(Player player) {
        if(player != null) {
            lblUserNickname.setText(player.getUser().getNickname());
            lblPlayerName.setText(player.getCharacterCard().getCharacterName());
            lblPlayerState.setText(player.getCharacterCard().getCharacterType());
            lblPlayerIcon.setIcon(getImageByPlayer(player));
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
}
