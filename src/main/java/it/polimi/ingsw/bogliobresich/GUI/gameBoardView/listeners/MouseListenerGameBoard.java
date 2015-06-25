package it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.CommandPanel;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.GUICoordinate;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.HexMech2;
import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.HexagonMapPanel;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class MouseListenerGameBoard extends MouseAdapter { 

    private Map<Coordinate,GUICoordinate> guiMap;
    private HexagonMapPanel hp;
    private CommandPanel commandPanel;
    private GUIController guiController = GUIController.getInstance();

    public MouseListenerGameBoard(HexagonMapPanel hp, CommandPanel commandPanel){
        this.commandPanel = commandPanel;
        this.hp=hp;
    }

    public void mouseClicked(MouseEvent e) { 
        this.guiMap=hp.getGuiMap();
        Point p = new Point( HexMech2.pxtoHex(e.getX(),e.getY()) );
        Coordinate coordKey=new Coordinate(p.x+1,p.y+1);
        if (!guiMap.containsKey(coordKey))
            return;
        GUICoordinate tmp=guiMap.get(coordKey);
        if(!tmp.getActualTextColour().equals(HexagonMapPanel.COLOURTEXTSECTORAVAIABLE))
            return;
        hp.resetGuiMapColour();
        hp.repaint();
        
        
        if(hp.getStateMoveRumorSpotlight().equals(hp.STATE_MOVE)) {
            GUIController.sendCommand(new ClientCommand(CommandType.DO_MOVE_REQUEST,coordKey));
        } else if(hp.getStateMoveRumorSpotlight().equals(hp.STATE_RUMOR)) {
            GUIController.sendCommand(new ClientCommand(CommandType.DO_RUMOR_IN_COORDINATE_REQUEST,coordKey));
        } else if(hp.getStateMoveRumorSpotlight().equals(hp.STATE_SPOTLIGHT)) {
            ItemCard card = guiController.getHandOfCards().getCard(guiController.getIdCardSelected()) ;
            if(card instanceof SpotlightItemCard) {
                SpotlightItemCard spotlightCard = (SpotlightItemCard)card;
                spotlightCard.setCoordToLight(coordKey);
                GUIController.sendCommand(new ClientCommand(CommandType.DO_PLAY_ITEM_REQUEST,spotlightCard));
                guiController.setIdCardSelected(-1);
            }
        }
        commandPanel.disableCommandPanel();

        /*hp.resetGuiMapColour();
        hp.setActualCoordinate(new Coordinate('L',10));
        tmp.setActualColour(Color.RED);
        hp.repaint();*/
    }

    public void setCommandPanel(CommandPanel commandPanel) {
        this.commandPanel = commandPanel;
    }                
}