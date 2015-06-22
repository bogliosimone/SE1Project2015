/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import java.awt.Color;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * @author simoneboglio
 */
public class GUICoordinate {
    Coordinate coord;
    Color defaultColour;
    Color actualColour;
    Color defaultTextColour;
    Color actualTextColour;
    char sectorType;
    String stringValue;
    
    public GUICoordinate(){
        
    }
    
    public GUICoordinate(int x, int y, char sectorType, Color color,Color textColor){
        this.coord=new Coordinate(x,y);
        this.sectorType=sectorType;
        this.defaultColour=color;
        this.actualColour=color;
        this.defaultTextColour=textColor;
        this.actualTextColour=textColor;
        if(sectorType=='1'||sectorType=='2'||sectorType=='3'||sectorType=='4')
                this.stringValue=Character.toString(sectorType);
        else 
            this.stringValue=Character.toString((char)(coord.getLetter()+1))+(coord.getNumber()+1);
        
    }
    
    public int getX(){
        return coord.getX();
    }
    
    public int getY(){
        return coord.getY();
    }
    
    public void setCoord(Coordinate coord) {
        this.coord = coord;
    }
    public Color getDefaultColour() {
        return defaultColour;
    }
    
    public void resetColour(){
        actualColour=defaultColour;
    }
    
    public Color getActualColour() {
        return actualColour;
    }
    public void setActualColour(Color color) {
        this.actualColour = color;
    }
    
    public void setDefaultColour(Color color) {
        this.defaultColour = color;
    }
    
    
    public Color getDefaultTextColour() {
        return defaultTextColour;
    }
    
    public void resetTextColour(){
        actualTextColour=defaultTextColour;
    }
    
    public Color getActualTextColour() {
        return actualTextColour;
    }
    public void setActualTextColour(Color color) {
        this.actualTextColour = color;
    }
    
    
    
    public char getSectorType() {
        return sectorType;
    }
    public void setSectorType(char sectorType) {
        this.sectorType = sectorType;
    }
    public String getTextLabel(){
        return this.coord.toString();
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }
    
    @Override
    public String toString(){
        return this.getX()+"x"+this.getY()+" value: "+stringValue;
    }
}
