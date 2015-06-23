/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnAttackListener;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.BtnCardListener;
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
    final static Color MISSING_USER = Color.GRAY;
    final static Color CONNECTED_USER = Color.WHITE;
    final static Color PLAYING_USER = Color.GREEN;
    final static Color PLAYER_INFO = Color.WHITE;
    
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
    private JLabel lblPlayerName;
    private JLabel lblPlayerState;
    private JLabel lblPlayerIcon;
    
    private HexagonMapPanel map;
    
    public CommandPanel(HexagonMapPanel map) {
        this.map = map;
        JLabel lblUtenti = new JLabel("Utenti:");
        lblUtenti.setBounds(86, 50, 61, 16);
        add(lblUtenti);
        lblUtenti.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        
        
        JSeparator separator = new JSeparator();
        separator.setBounds(28, 264, 403, 16);
        add(separator);
        
        
        
        lblPlayerIcon = new JLabel();
        lblPlayerIcon.setBounds(94, 300, 54, 55);
        add(lblPlayerIcon);

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
        btnPlayTheCard.addActionListener(new BtnPlayTheCardListener(map));
        add(btnPlayTheCard);
        btnPlayTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDiscardTheCard = new JButton("Scarta la carta");
        btnDiscardTheCard.setBounds(238, 483, 145, 30);
        add(btnDiscardTheCard);
        btnDiscardTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDrawSectorCard = new JButton("Pesca una carta settore");
        btnDrawSectorCard.setBounds(86, 513, 145, 30);
        btnDrawSectorCard.addActionListener(new BtnDrawSectorCardListener());
        add(btnDrawSectorCard);
        btnDrawSectorCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        
        btnAttack = new JButton("Attacca");
        btnAttack.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnAttack.setBounds(238, 513, 145, 30);
        btnAttack.addActionListener(new BtnAttackListener());
        add(btnAttack);
        
        btnEndMovement = new JButton("Fine movimento");
        btnEndMovement.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnEndMovement.setBounds(86, 543, 145, 30);
        btnEndMovement.addActionListener(new BtnEndMovementListener());
        add(btnEndMovement);
        
        btnEndTurn = new JButton("Fine turno");
        btnEndTurn.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnEndTurn.setBounds(238, 543, 145, 30);
        btnEndTurn.addActionListener(new BtnEndTurnListener());
        add(btnEndTurn);

    }
    
    
    
    public void disableCommandPanel() {
        btnPlayTheCard.setEnabled(false);
        btnDrawSectorCard.setEnabled(false);
        btnDiscardTheCard.setEnabled(false);
        btnAttack.setEnabled(false);
        for(int index = 0; index < ConstantMatch.MAXCARDINHAND; index++) {
            btnCards[index].setEnabled(false);
        }
    }
    
    public void printHand(ItemHand hand) {
        int index = 0;
        List<ItemCard> cards = hand.getAllCard();
        for(ItemCard card : cards) {
            System.out.println(card);
            btnCards[index] = new JButton("");
            btnCards[index].setBounds((86+76*index), 401, 64, 64);
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

    public void printUserList(List <User> list) {
        int index = 0;
        if(list != null) {
            for(User user : list) {
                labelUsers[index] = new JLabel((index+1) + " - " + user.getNickname());
                labelUsers[index].setBounds(86, 75+20*index, 300, 15);
                add(labelUsers[index]);
                labelUsers[index].setForeground(CONNECTED_USER);
                index++;
            }
        }
        for(int remainderUsers = index; remainderUsers < ConstantMatch.MAXPLAYERS; remainderUsers++) {
            labelUsers[remainderUsers] = new JLabel((remainderUsers+1) + " - [Missing User]");
            labelUsers[remainderUsers].setBounds(86, 75+20*remainderUsers, 300, 15);
            add(labelUsers[remainderUsers]);
            labelUsers[remainderUsers].setForeground(MISSING_USER);
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
            lblPlayerName.setText(player.getCharacterCard().getCharacterName());
            lblPlayerState.setText(player.getCharacterCard().getCharacterType());
            lblPlayerIcon.setIcon(getImageByPlayer(player));
        }
    }
    
    public ImageIcon getImageByPlayer(Player player) {
        if(player.getCharacterCard().getCharacterName().equals(Characters.CAPTAIN)) {
            return imagesHolder.getCaptain();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.PILOT)) {
            return imagesHolder.getPilot();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.PSYCHOLOGIST)) {
            return imagesHolder.getPsychologist();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.SOLDIER)) {
            return imagesHolder.getSoldier();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENONE)) {
            return imagesHolder.getFirstAlien();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENTWO)) {
            return imagesHolder.getSecondAlien();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENTHREE)) {
            return imagesHolder.getThirdAlien();
        } else if(player.getCharacterCard().getCharacterName().equals(Characters.ALIENFOUR)) {
            return imagesHolder.getFourthAlien();
        }
        return imagesHolder.getCaptain();
    }
}
