package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.model.map.ConstantMap;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*; 
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HexGame2{

    //constants and global variables
    final static Color COLOURBACKGROUND =  Color.BLACK;  
    final static Color COLOURGRID =  Color.BLACK;    
    final static Color COLOURSAFESECTOR = Color.WHITE;
    final static Color COLOURUNSAFESECTOR = Color.GRAY;
    final static Color COLOURACTIVEPH = Color.GREEN;
    final static Color COLOURBROKENPH = Color.RED;
    final static Color COLOURHUMANBASE = Color.CYAN;
    final static Color COLOURALIENBASE = Color.ORANGE;
    
    final static int SIZETEXT = 10; //text size in pixel
    final static int HEXSIZE = 34;  //hex size in pixels
    final static int BORDERSIZE = 10;  //border size
    final static int YSIZE=ConstantMap.ROWMAP;
    final static int XSIZE=ConstantMap.COLUMNMAP;
    final static int XSCRSIZE=(int)((HEXSIZE * (XSIZE+1) + BORDERSIZE*3)/1.25);
    final static int YSCRSIZE=HEXSIZE * (YSIZE + 1) + BORDERSIZE*3;

    private Map<Coordinate,GUICoordinate> guiMap  = new HashMap<Coordinate,GUICoordinate>();

    private HexGame2() {
        guiMap=loadHashMapFromFile(ConstantMap.NAMEFILEMAP);
        initGame();
        createAndShowGUI();
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HexGame2();
            }
        });
    }






    void initGame(){
        HexMech2.setXYasVertex(false); //RECOMMENDED: leave this as FALSE.
        HexMech2.setHeight(HEXSIZE); //Either setHeight or setSize must be run to initialize the hex
        HexMech2.setBorders(BORDERSIZE);
    }

    private void createAndShowGUI()
    {
        DrawingPanel panel = new DrawingPanel();
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Mappa esagoni");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        Container content = frame.getContentPane();
        content.add(panel);
        //this.add(panel);  -- cannot be done in a static context
        //for hexes in the FLAT orientation, the height of a 10x10 grid is 1.1764 * the width. (from h / (s+t))
        frame.setSize(XSCRSIZE, YSCRSIZE);
        frame.setResizable(false);
        frame.setLocationRelativeTo( null );
        frame.setVisible(true);
    }

    //CLASS DRAWING PANEL
    
    class DrawingPanel extends JPanel
    {
        private static final long serialVersionUID = -7028080785459384175L;

        public DrawingPanel()
        {       
            setBackground(COLOURBACKGROUND);
            MyMouseListener ml = new MyMouseListener();            
            addMouseListener(ml);
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
                HexMech2.fillHex(tmp.getX(),tmp.getY(),tmp.getStringValue(),g2);
            }
        }



        //CLASS MOUSE LISTENER
        
        class MyMouseListener extends MouseAdapter      {       //inner class inside DrawingPanel 
            public void mouseClicked(MouseEvent e) {  
                //mPt.x = x;
                //mPt.y = y;
                Point p = new Point( HexMech2.pxtoHex(e.getX(),e.getY()) );
                Coordinate coordKey=new Coordinate(p.x+1,p.y+1);
                if (!guiMap.containsKey(coordKey))
                    return;
                GUICoordinate tmp=guiMap.get(coordKey);
                resetGuiMapColour();
                tmp.setActualColour(Color.RED);
                repaint();
            }                
        } //end of MyMouseListener class 
    } // end of DrawingPanel class



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
            return new GUICoordinate (x,y,charType,COLOURUNSAFESECTOR);
        if(charType=='S')
            return new GUICoordinate (x,y,charType,COLOURSAFESECTOR);
        if(charType=='D')
            return null;
        if(charType=='1'||charType=='2'||charType=='3'||charType=='4')
            return new GUICoordinate (x,y, charType,COLOURACTIVEPH);
        if(charType=='H')
            return new GUICoordinate (x,y, charType,COLOURHUMANBASE);
        if(charType=='A')
            return new GUICoordinate (x,y, charType,COLOURALIENBASE);
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
        for(GUICoordinate tmp:collection)
            tmp.resetColour();
    }

}