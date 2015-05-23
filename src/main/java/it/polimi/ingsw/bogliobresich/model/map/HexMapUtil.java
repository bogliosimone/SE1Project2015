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
 * @author simoneboglio
 *
 */
public class HexMapUtil {
    
    private HexMapUtil(){
    }
    
    public static Map<Hex,Sector> loadHashMapFromFile (String fileName) throws IOException {
        Map<Hex,Sector> mp= new HashMap<Hex,Sector>();
        int column=ConstantMap.COLUMNMAP;
        int row=ConstantMap.ROWMAP;
        char letterType;
        Sector sect;
        OffsetCoord offsetCoord;
        Hex hexCoord;
        FileReader file;
        BufferedReader buffer;
        String stringa;
        file=new FileReader(fileName);
        buffer=new BufferedReader(file);
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
        buffer.close();
        return mp;
    }
    
    public static Sector newSectorFromXYLetterType(int x, int y, char type){
        if(type=='U')
            return new UnsafeSector (x,y);
        if(type=='S')
            return new SafeSector (x,y);
        if(type=='P')
            return new PortholeSector (x,y);
        if(type=='H')
            return new HumanBaseSector (x,y);
        if(type=='A')
            return new AlienBaseSector (x,y);
        if(type=='D')
            return new DisableSector (x, y);
        return new DisableSector (x,y);
    }
    
    public static void printMap(HexMap hexMap) {
        Map<Hex,Sector> mp= hexMap.getHexMap();
        Set<Hex> keys = mp.keySet();
        for(Hex key : keys){
            Sector sec = mp.get(key);
            System.out.println(sec + " " + key);
        }
    }
     
    public static void printMapNeighborsByDistance(HexMap hexMap,Hex hex, int maxDistance){
        Set<Hex> set = hexMap.getNeighborsByDistance(hex, maxDistance);
        System.out.println("Vicini di "+hex.toString()+" a distanza: "+ maxDistance );
        for (Hex temp: set)
            System.out.println("Vicino: "+temp);
    }
    
    public static void printMapNeighborsByDistance(HexMap hexMap,Coordinate coord, int maxDistance){
        Set<Sector> set = hexMap.getNeighborsByDistance(coord, maxDistance);
        System.out.println("Vicini di "+ coord.toString() + " a distanza: " + maxDistance );
        for (Sector temp: set)
            System.out.println("Vicino: "+temp.getCoordianteLetter()+temp.getCoordinateNumber());
    }
}
