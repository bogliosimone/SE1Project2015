package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Map;

public class MouseListenerGameBoard extends MouseAdapter { 
    
    private Map<Coordinate,GUICoordinate> guiMap;
    private HexagonMapPanel hp;
    
    public MouseListenerGameBoard(HexagonMapPanel hp){
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
        //what to do
        
        GUIController.sendCommand(new ClientCommand(CommandType.DO_MOVE_REQUEST,coordKey));
        /*hp.resetGuiMapColour();
        hp.setActualCoordinate(new Coordinate('L',10));
        tmp.setActualColour(Color.RED);
        hp.repaint();*/
    }                
}