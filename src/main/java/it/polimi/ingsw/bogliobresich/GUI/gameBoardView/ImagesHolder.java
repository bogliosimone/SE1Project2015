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
    
    private ImageIcon rumorXY;
    private ImageIcon rumorMySector;
    private ImageIcon silence;
    
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

        rumorXY = new ImageIcon(FilePaths.SECTORCARD_RUMOR_XY);
        rumorMySector = new ImageIcon(FilePaths.SECTORCARD_RUMOR_MY_SECTOR);
        silence = new ImageIcon(FilePaths.SECTORCARD_SILENCE);
    }

    /**
     * @return the greenPorthole
     */
    public ImageIcon getGreenPorthole() {
        return greenPorthole;
    }

    /**
     * @return the redPorthole
     */
    public ImageIcon getRedPorthole() {
        return redPorthole;
    }

    /**
     * @return the itemIcon
     */
    public ImageIcon getItemIcon() {
        return itemIcon;
    }

    /**
     * @return the defense
     */
    public ImageIcon getDefense() {
        return defense;
    }

    /**
     * @return the attack
     */
    public ImageIcon getAttack() {
        return attack;
    }

    /**
     * @return the adrenaline
     */
    public ImageIcon getAdrenaline() {
        return adrenaline;
    }

    /**
     * @return the spotlight
     */
    public ImageIcon getSpotlight() {
        return spotlight;
    }

    /**
     * @return the sedatives
     */
    public ImageIcon getSedatives() {
        return sedatives;
    }

    /**
     * @return the teleport
     */
    public ImageIcon getTeleport() {
        return teleport;
    }

    /**
     * @return the captain
     */
    public ImageIcon getCaptain() {
        return captain;
    }

    /**
     * @return the pilot
     */
    public ImageIcon getPilot() {
        return pilot;
    }

    /**
     * @return the psychologist
     */
    public ImageIcon getPsychologist() {
        return psychologist;
    }

    /**
     * @return the soldier
     */
    public ImageIcon getSoldier() {
        return soldier;
    }

    /**
     * @return the firstAlien
     */
    public ImageIcon getFirstAlien() {
        return firstAlien;
    }

    /**
     * @return the secondAlien
     */
    public ImageIcon getSecondAlien() {
        return secondAlien;
    }

    /**
     * @return the thirdAlien
     */
    public ImageIcon getThirdAlien() {
        return thirdAlien;
    }

    /**
     * @return the fourthAlien
     */
    public ImageIcon getFourthAlien() {
        return fourthAlien;
    }

    /**
     * @return the rumorXY
     */
    public ImageIcon getRumorXY() {
        return rumorXY;
    }

    /**
     * @return the rumorMySector
     */
    public ImageIcon getRumorMySector() {
        return rumorMySector;
    }

    /**
     * @return the silence
     */
    public ImageIcon getSilence() {
        return silence;
    }
}
