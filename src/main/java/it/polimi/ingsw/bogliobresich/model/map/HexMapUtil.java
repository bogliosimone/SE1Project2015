/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import it.polimi.ingsw.bogliobresich.model.map.sector.*;

/**
 * Util class for the HexMap class,it contains method for load the HashMap from file and some method for debugging 
 * @author simoneboglio
 */
public class HexMapUtil {
    
    /**
     * private constructor for util class
     */
    private HexMapUtil(){
    }
    
    /**
     * create and return an HashMap with key->CubeCoord, value->Sector from a filename
     * @param fileName
     * @return HashMap<CubeCoord,Sector>  
     * @throws IOException
     */
    public static Map<CubeCoord,Sector> loadHashMapFromFile (String fileName) {
        Map<CubeCoord,Sector> mp= new HashMap<CubeCoord,Sector>();
        int column=ConstantMap.COLUMNMAP;
        int row=ConstantMap.ROWMAP;
        char letterType;
        Sector sect;
        OffsetCoord offsetCoord;
        CubeCoord hexCoord;
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
                    sect = newSectorFromXYLetterType(i+1,j+1,letterType);
                    offsetCoord = new OffsetCoord (sect.getCoordinateX(),sect.getCoordinateY());
                    hexCoord = OffsetCoord.offsetToCube(offsetCoord);
                    mp.put(hexCoord,sect);  
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

    /**
     * Return a specific sector of subclass Sector based on charType with x y as coordinate
     * 
     * @param x
     * @param y
     * @param charType
     * @return
     */
    public static Sector newSectorFromXYLetterType(int x, int y, char charType){
        if(charType=='U')
            return new UnsafeSector (x,y);
        if(charType=='S')
            return new SafeSector (x,y);
        if(charType=='P')
            return new PortholeSector (x,y);
        if(charType=='H')
            return new HumanBaseSector (x,y);
        if(charType=='A')
            return new AlienBaseSector (x,y);
        if(charType=='D')
            return new DisableSector (x, y);
        return new DisableSector (x,y);
    }
    
    /**
     * Print the HexMap
     * @param hexMap
     */
    public static void printMap(HexMap hexMap) {
        Map<CubeCoord,Sector> mp= hexMap.getHashMap();
        Set<CubeCoord> keys = mp.keySet();
        for(CubeCoord key : keys){
            Sector sec = mp.get(key);
            System.out.println(sec + " " + key);
        }
    }
     
    /**
     * Print all the crossable neighbors of hexagon in a specific range with cube coordinate value
     * @param hexMap
     * @param hex
     * @param maxDistance
     */
    public static void printMapNeighborsByDistance(HexMap hexMap,CubeCoord hex, int maxDistance){
        Set<CubeCoord> set = hexMap.getNeighborsByDistance(hex, maxDistance);
        System.out.println("Vicini di "+hex.toString()+" a distanza: "+ maxDistance );
        for (CubeCoord temp: set)
            System.out.println("Vicino: "+temp);
    }
    
    /**
     * Print all the crossable neighbors of hexagon in a specific range with letter-number coordinate value
     * @param hexMap
     * @param coord
     * @param maxDistance
     */
    public static void printMapNeighborsByDistance(HexMap hexMap,Coordinate coord, int maxDistance){
        Set<Sector> set = hexMap.getNeighborsByDistance(coord, maxDistance);
        System.out.println("Vicini di "+ coord.toString() + " a distanza: " + maxDistance );
        for (Sector temp: set)
            System.out.println("Vicino: "+temp.getCoordianteLetter()+temp.getCoordinateNumber());
    }
}
