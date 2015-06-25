/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;


import it.polimi.ingsw.bogliobresich.GUI.gameBoardView.listeners.MouseListenerGameBoard;
import it.polimi.ingsw.bogliobresich.model.map.ConstantMap;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JPanel;

/**
 * @author simone boglio
 * @author matteo bresich
 *
 */
public class HexagonMapPanel extends JPanel {

    private static final long serialVersionUID = -7028080785459384175L;
    
    //constants and global variables
    final static Color COLOURBACKGROUND =  Color.BLACK;  
    final static Color COLOURGRID =  Color.BLACK;    
    final static Color COLOURSAFESECTOR = Color.WHITE;
    final static Color COLOURUNSAFESECTOR = Color.GRAY;
    final static Color COLOURACTIVEPH = new Color(12,149,16);
    final static Color COLOURBROKENPH = new Color (255,51,51);
    final static Color COLOURHUMANBASE = new Color(36,126,229);
    final static Color COLOURALIENBASE = new Color(229,132,36);
    final static Color COLOURTEXTSECTOR = Color.BLACK;
    public final static Color COLOURTEXTSECTORAVAIABLE = new Color(107,229,36);
    final static Color COLORACTUALPOSITION = new Color(204,255,255);
    
    
    final static int SIZETEXT = 10; //text size in pixel
    final static int HEXSIZE = 34;  //hex size in pixels
    final static int BORDERSIZE = 10;  //border size
    final static int YSIZE=ConstantMap.ROWMAP;
    final static int XSIZE=ConstantMap.COLUMNMAP;
    final static int XSCRSIZE=(int)((HEXSIZE * (XSIZE+1) + BORDERSIZE*3)/1.25);
    final static int YSCRSIZE=HEXSIZE * (YSIZE + 1) + BORDERSIZE*3;
    
    private Coordinate actualCoordinate;
    private String stateMoveRumorSpotlight;
    public final String STATE_RUMOR = "rumor-xy";
    public final String STATE_SPOTLIGHT = "spotlight";
    public final String STATE_MOVE = "move";

    private Map<Coordinate,GUICoordinate> guiMap  = new HashMap<Coordinate,GUICoordinate>();
    
    MouseListenerGameBoard ml;


    public HexagonMapPanel(String mapFile, CommandPanel commandPanel)
    {       
        guiMap=loadHashMapFromFile(mapFile);
        initMech();
        setBackground(COLOURBACKGROUND);
        ml = new MouseListenerGameBoard(this,commandPanel);            
        addMouseListener((MouseListener)ml);
    }
    
    public MouseListenerGameBoard getMyML() {
        return ml;
    }
    

    void initMech(){
        HexMech2.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
        HexMech2.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
        HexMech2.setBorders(BORDERSIZE);
    }

    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D)g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, SIZETEXT));
        super.paintComponent(g2);
        //draw grid
        Collection<GUICoordinate> collection=guiMap.values();
        for(GUICoordinate tmp:collection){
            HexMech2.drawHex(tmp.getX(),tmp.getY(),tmp.getActualColour(),g2);
        }
        if(actualCoordinate!=null){
            GUICoordinate actualGuiCoordinate=this.guiMap.get(actualCoordinate);
            HexMech2.drawLittleHex(actualGuiCoordinate.getX(), actualGuiCoordinate.getY(), COLORACTUALPOSITION, g2);
        }
        for(GUICoordinate tmp:collection){
            HexMech2.fillHex(tmp.getX(),tmp.getY(),tmp.getStringValue(),tmp.getActualTextColour(),g2);
        }
    }
    
    private Map<Coordinate,GUICoordinate> loadHashMapFromFile (String fileName) {
        Map<Coordinate,GUICoordinate> mp= new HashMap<Coordinate,GUICoordinate>();
        int column=XSIZE;
        int row=YSIZE;
        char letterType;
        GUICoordinate guiCoord;
        Coordinate coord;
        FileReader reader = null;
        BufferedReader buffer = null;
        String stringa;
        try{
            reader=new FileReader(fileName);
            buffer=new BufferedReader(reader);
            for(int i=0;i<column;i++) {
                stringa=buffer.readLine();
                for(int j=0;j<row;j++){
                    letterType=stringa.charAt(j);
                    guiCoord = newGUICoordinateFromLetterType(i,j,letterType);
                    if(guiCoord!=null){
                        coord = new Coordinate (i+1,j+1);
                        mp.put(coord,guiCoord); 
                    }
                }
            }
        }
        catch (IOException e){
            System.out.println(e+" Errore apertura file");
        }
        finally{
            if(reader != null){
                try {
                    reader.close();
                    buffer.close();
                } catch (IOException e) {
                    System.out.println(e+" Errore chiusura file");
                }
            }
        }
        return mp;
    }

    private GUICoordinate newGUICoordinateFromLetterType(int x, int y, char charType){
        if(charType=='U')
            return new GUICoordinate (x,y,charType,COLOURUNSAFESECTOR,COLOURTEXTSECTOR);
        if(charType=='S')
            return new GUICoordinate (x,y,charType,COLOURSAFESECTOR,COLOURTEXTSECTOR);
        if(charType=='D')
            return null;
        if(charType=='1'||charType=='2'||charType=='3'||charType=='4')
            return new GUICoordinate (x,y, charType,COLOURACTIVEPH,COLOURTEXTSECTOR);
        if(charType=='H')
            return new GUICoordinate (x,y, charType,COLOURHUMANBASE,COLOURTEXTSECTOR);
        if(charType=='A')
            return new GUICoordinate (x,y, charType,COLOURALIENBASE,COLOURTEXTSECTOR);
        return null;
    }

    public static void printDataGuiMap( Map<Coordinate,GUICoordinate> mp) {
        Set<Coordinate> keys = mp.keySet();
        for(Coordinate key : keys){
            GUICoordinate sec = mp.get(key);
            System.out.println(key + " - " + sec);
        }
    }

    public void resetGuiMapColour(){
        Collection<GUICoordinate> collection=guiMap.values();
        for(GUICoordinate tmp:collection){
            tmp.resetColour();
            tmp.resetTextColour();
        }
    }
    
    public Map<Coordinate,GUICoordinate> getGuiMap(){
        return this.guiMap;
    }
    
    public void setAvaiableMoves(Set<Coordinate> listCoord){
        resetGuiMapColour();
        for(Coordinate coord:listCoord){
            GUICoordinate tmp=this.guiMap.get(coord);
            tmp.setActualTextColour(COLOURTEXTSECTORAVAIABLE);
        }
        repaint();
    }
    
    public void setActualCoordinate(Coordinate coord){
        resetGuiMapColour();
        this.actualCoordinate=coord;
        repaint();
    }
    
    public void setBreakPorthole(Coordinate coord){
        GUICoordinate tmp = this.guiMap.get(coord);
        if(tmp!=null){
            tmp.setDefaultColour(COLOURBROKENPH);
            resetGuiMapColour();
            repaint();
        }
    }
    
    public void setAvaiableAllMoves(){
        resetGuiMapColour();
        Collection<GUICoordinate> tmpCollect=this.guiMap.values();
        for(GUICoordinate tmp:tmpCollect){
            tmp.setActualTextColour(COLOURTEXTSECTORAVAIABLE);
        }
        repaint();
    }
    
    /**
     * @return the stateMoveRumorSpotlight
     */
    public String getStateMoveRumorSpotlight() {
        return stateMoveRumorSpotlight;
    }


    /**
     * @param stateMoveRumorSpotlight the stateMoveRumorSpotlight to set
     */
    public void setStateMoveRumorSpotlight(String stateMoveRumorSpotlight) {
        this.stateMoveRumorSpotlight = stateMoveRumorSpotlight;
    }
} 
