/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.model.Characters;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.ConstantMatch;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.awt.Color;
import java.awt.Font;

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
    
    private ImagesHolder imagesHolder = ImagesHolder.getInstance();

    private JLabel[] labelUsers = new JLabel[ConstantMatch.MAXPLAYERS];
    private JButton[] btnCards = new JButton[ConstantMatch.MAXCARDINHAND];
    private JLabel labelTurnNumber;
    private JButton btnPlayTheCard;
    private JButton btnDrawSectorCard;
    private JButton btnDiscardTheCard;
    private JButton btnAttack;
    private JLabel lblCurrentPosition;
    private JLabel lblPlayerName;
    private JLabel lblPlayerState;
    private JLabel lblPlayerNickname;
    private JLabel lblPlayerIcon;
    
    
    public CommandPanel() {
        JLabel lblUtenti = new JLabel("Utenti:");
        lblUtenti.setBounds(86, 50, 61, 16);
        add(lblUtenti);
        lblUtenti.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        JLabel lblCarteInMano = new JLabel("Carte in mano:");
        lblCarteInMano.setBounds(86, 371, 116, 33);
        add(lblCarteInMano);

        JLabel lblNickname = new JLabel("NICKNAME:");
        lblNickname.setBounds(162, 278, 75, 16);
        add(lblNickname);
        lblNickname.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        lblPlayerNickname = new JLabel();
        lblPlayerNickname.setBounds(238, 278, 180, 16);
        add(lblPlayerNickname);

        lblPlayerIcon = new JLabel();
        lblPlayerIcon.setBounds(94, 275, 54, 55);
        add(lblPlayerIcon);


        JLabel lblPlayer = new JLabel("PLAYER:");
        lblPlayer.setBounds(162, 296, 61, 16);
        add(lblPlayer);
        lblPlayer.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        lblPlayerName = new JLabel();
        lblPlayerName.setBounds(218, 296, 200, 16);
        add(lblPlayerName);

        JLabel lblState = new JLabel("STATE:");
        lblState.setBounds(162, 314, 54, 16);
        add(lblState);
        lblState.setFont(new Font("Lucida Grande", Font.BOLD, 13));

        labelTurnNumber = new JLabel();
        labelTurnNumber.setBounds(218, 333, 200, 16);
        add(labelTurnNumber);

        JSeparator separator = new JSeparator();
        separator.setBounds(28, 264, 403, 16);
        add(separator);

        JSeparator separator_1 = new JSeparator();
        separator_1.setBounds(31, 365, 400, 16);
        add(separator_1);

        btnPlayTheCard = new JButton("Gioca la carta");
        btnPlayTheCard.setBounds(86, 483, 145, 30);
        add(btnPlayTheCard);
        btnPlayTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDiscardTheCard = new JButton("Scarta la carta");
        btnDiscardTheCard.setBounds(238, 483, 145, 30);
        add(btnDiscardTheCard);
        btnDiscardTheCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        btnDrawSectorCard = new JButton("Pesca una carta settore");
        btnDrawSectorCard.setBounds(86, 513, 145, 30);
        add(btnDrawSectorCard);
        btnDrawSectorCard.setFont(new Font("Lucida Grande", Font.PLAIN, 10));

        JLabel lblCartaSettore = new JLabel("Carta settore:");
        lblCartaSettore.setBounds(86, 590, 154, 55);
        add(lblCartaSettore);
        lblCartaSettore.setIcon(imagesHolder.getRumorXY());

        JLabel lblRumoreIn = new JLabel("Rumore in XY");
        lblRumoreIn.setBounds(241, 609, 116, 16);
        add(lblRumoreIn);

        JLabel lblTurno = new JLabel("TURNO:");
        lblTurno.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblTurno.setBounds(162, 332, 54, 16);
        add(lblTurno);

        btnAttack = new JButton("Attacca");
        btnAttack.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
        btnAttack.setBounds(238, 513, 145, 30);
        add(btnAttack);

        JLabel lblPosizioneCorrente = new JLabel("POSIZIONE CORRENTE:");
        lblPosizioneCorrente.setFont(new Font("Lucida Grande", Font.BOLD, 13));
        lblPosizioneCorrente.setBounds(162, 350, 154, 16);
        add(lblPosizioneCorrente);

        lblPlayerState = new JLabel();
        lblPlayerState.setBounds(209, 314, 209, 16);
        add(lblPlayerState);

        lblCurrentPosition = new JLabel("");
        lblCurrentPosition.setBounds(317, 350, 101, 16);
        add(lblCurrentPosition);
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

    public void printHand() {
        for (int index = 0; index < ConstantMatch.MAXCARDINHAND; index++) {
            btnCards[index] = new JButton("");
            btnCards[index].setBounds((86+76*index), 401, 64, 64);
            add(btnCards[index]);
            btnCards[index].setIcon(imagesHolder.getAdrenaline());
        }
    }

    public void printUserList(User[] list) {
        int index = 0;
        if(list != null) {
            for(User user : list) {
                labelUsers[index] = new JLabel((index+1) + " - ");
                labelUsers[index].setBounds(86, 75+20*index, 300, 15);
                add(labelUsers[index]);
                labelUsers[index].setForeground(Color.BLACK);
                index++;
            }
        }
        for(int remainderUsers = index; remainderUsers < ConstantMatch.MAXPLAYERS; remainderUsers++) {
            labelUsers[remainderUsers] = new JLabel((remainderUsers+1) + " - [Missing User]");
            labelUsers[remainderUsers].setBounds(86, 75+20*remainderUsers, 300, 15);
            add(labelUsers[remainderUsers]);
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
        return null;
    }
}
