package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.model.map.ConstantMap;

import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HexagonMapTest{

    //constants and global variables
    final static int HEXSIZE = 34;  //hex size in pixels
    final static int BORDERSIZE = 10;  //border size
    final static int YSIZE=ConstantMap.ROWMAP;
    final static int XSIZE=ConstantMap.COLUMNMAP;
    final static int XSCRSIZE=(int)((HEXSIZE * (XSIZE+1) + BORDERSIZE*3)/1.25);
    final static int YSCRSIZE=HEXSIZE * (YSIZE + 1) + BORDERSIZE*3;
    

    private HexagonMapTest() {
        createAndShowGUI();
    }

    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HexagonMapTest();
            }
        });
    }

    private void createAndShowGUI()
    {
        HexagonMapPanel panel = new HexagonMapPanel(ConstantMap.NAMEFILEMAP);
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
}