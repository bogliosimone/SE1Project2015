/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.FilePaths;

import javax.swing.ImageIcon;

/**
 * @author matteobresich
 *
 */
public class ImagesHolder {
    private ImageIcon greenPorthole;
    private ImageIcon redPorthole;
    
    private ImageIcon itemIcon;
    private ImageIcon defense;
    private ImageIcon attack;
    private ImageIcon adrenaline;
    private ImageIcon spotlight;
    private ImageIcon sedatives;
    private ImageIcon teleport;
    
    private ImageIcon captain;
    private ImageIcon pilot;
    private ImageIcon psychologist;
    private ImageIcon soldier;
    private ImageIcon firstAlien;
    private ImageIcon secondAlien;
    private ImageIcon thirdAlien;
    private ImageIcon fourthAlien;
    
    public ImagesHolder () {
        loadImages();
    }
    
    private void loadImages() {
        greenPorthole = new ImageIcon(FilePaths.PORTHOLE_GREEN);
        redPorthole = new ImageIcon(FilePaths.PORTHOLE_RED);
        
        itemIcon = new ImageIcon(FilePaths.ITEM_CARD_ICON);
        defense = new ImageIcon(FilePaths.ITEM_CARD_DEFENSE);
        attack = new ImageIcon(FilePaths.ITEM_CARD_ATTACK);
        adrenaline = new ImageIcon(FilePaths.ITEM_CARD_ADRENALINE);
        spotlight = new ImageIcon(FilePaths.ITEM_CARD_SPOTLIGHT);
        sedatives = new ImageIcon(FilePaths.ITEM_CARD_SEDATIVES);
        teleport = new ImageIcon(FilePaths.ITEM_CARD_TELEPORT);
        
        captain = new ImageIcon(FilePaths.CAPTAIN_ICON);
        pilot = new ImageIcon(FilePaths.PILOT_ICON);
        psychologist = new ImageIcon(FilePaths.PSYCHOLOGIST_ICON);
        soldier = new ImageIcon(FilePaths.SOLDIER_ICON);
        firstAlien = new ImageIcon(FilePaths.FIRST_ALIEN_ICON);
        secondAlien = new ImageIcon(FilePaths.SECOND_ALIEN_ICON);
        thirdAlien = new ImageIcon(FilePaths.THIRD_ALIEN_ICON);
        fourthAlien = new ImageIcon(FilePaths.FOURTH_ALIEN_ICON);
    }

}
