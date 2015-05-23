/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.max;
import static java.lang.Math.min;
import it.polimi.ingsw.bogliobresich.model.map.sector.*;
import it.polimi.ingsw.bogliobresich.model.map.HexMapUtils;

/**
 * @author simoneboglio
 *
 */
public class HexMap {
    private HashMap<Hex,Sector> hexMap  = new HashMap<Hex,Sector>(); //HashMap key = cube_coordinate -> value = sector 


    public HexMap(){
        try{
            this.loadMapFromFile(ConstantMap.NAMEFILEMAP);
        }
        catch(IOException e)
        {
            System.err.println(e.toString()+ " Errore apertura file mappa");
        }
    }
    
    public static void main( String[] args ){
        HexMap mappa=new HexMap();
        //mappa.printMap();
        mappa.printNeighborsByDistance(new Coordinate('K',8),2);
    }

    public void resetDistanceSectors(Set<Sector> secToClear){
        for(Sector sector : secToClear){
            sector.resetDistance();
        }
    }
    
    public Set<Sector> neighborsByDistance(Coordinate coord, int distance){
        Set<Sector> sectorSet = new HashSet<Sector>();
        Set<Hex> hexTempSet;
        Hex hex = new Hex(coord);
        hexTempSet=neighborsByDistance(hex,distance);
        for(Hex HexTemp: hexTempSet){
            sectorSet.add(this.hexMap.get(HexTemp));
        }   
        return sectorSet;
    }
    
    public Set<Hex> neighbors (Hex hex){
        Hex hexTemp;
        Sector secTemp;
        int distance=1;
        Set<Hex> set = new HashSet<Hex>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x
                    + distance); y++) {
                int z = -x - y;
                hexTemp= new Hex(hex.getX()+x,hex.getY()+y,hex.getZ()+z);
                secTemp = hexMap.get(hexTemp);
                if(secTemp!=null && secTemp.isCrossable() && hexTemp!=hex)
                    set.add(hexTemp);
            }
        }
        return set;
    }
    
    public Set<Hex> neighborsByDistance (Hex hex, int maxDistance){
        Map<Hex, Sector> mp = this.hexMap;
        Hex hexTemp;
        Sector secTemp;
        Set<Hex> neighborsSetTemp;
        Set<Sector> sectorsToClear = new HashSet<Sector>();
        Set<Hex> set = new HashSet<Hex>(); //neighbors list to return
        Queue<Hex> queue = new LinkedList<Hex>(); //queue for explore map
        
        secTemp = mp.get(hex);
        if(secTemp==null){
            return set;
        }
        secTemp.setDistance(0);
        queue.add(hex);
        hexTemp=queue.poll();
        while( hexTemp!=null){       
            secTemp = mp.get(hexTemp);
            sectorsToClear.add(secTemp);
            if(!hexTemp.equals(hex))
                set.add(hexTemp);
            if(secTemp.getDistance()<maxDistance){
                neighborsSetTemp=neighbors(hexTemp);
                for(Hex hexNeighbor : neighborsSetTemp){
                    Sector secNeighborsTemp = mp.get(hexNeighbor);
                    if(secNeighborsTemp.getDistance()>secTemp.getDistance()){
                        secNeighborsTemp.setDistance(secTemp.getDistance()+1);
                            queue.add(hexNeighbor);
                    }
                }
            }
            hexTemp=queue.poll();
        }
        resetDistanceSectors(sectorsToClear);
        return set;
    }
    
    public void loadMapFromFile (String fileName) throws IOException {
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
                sect = HexMapUtils.newSectorFromXYLetterType(i+1,j+1,letterType);
                offsetCoord = new OffsetCoord (sect.getCoordinateX(),sect.getCoordinateY());
                hexCoord = OffsetCoord.offsetToCube(offsetCoord);
                this.hexMap.put(hexCoord,sect);  
            }
        }
        buffer.close();
    }
    
    public void printMap() {
        Map<Hex, Sector> mp = (Map<Hex,Sector>) hexMap.clone();
        Iterator<Entry<Hex, Sector>> it = mp.entrySet().iterator();
        while (it.hasNext()) {
            Entry<Hex, Sector> pair = (Entry<Hex, Sector>)it.next();
            System.out.println(pair.getKey() + " " + pair.getValue());
            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void printHashMap(){
        Map<Hex, Sector> mp = this.hexMap;
        Set<Hex> keys = mp.keySet();
        for(Hex key : keys){
            Sector sec = mp.get(key);
            System.out.println("key= "+key+"value= " + sec);

        }
    }

    public void printNeighbors(Hex hex){
        printNeighborsByDistance(hex,1);
    }
    
    
    public void printNeighborsByDistance(Hex hex, int distance){
        Set<Hex> set = neighborsByDistance(hex,distance);
        System.out.println("Vicini raggiungibili con passo "+distance+" da "+hex.toString());
        for (Hex temp: set)
            System.out.println("Vicino: "+temp);
    }
    
    public void printNeighborsByDistance(Coordinate coord, int distance){
        Set<Sector> set = neighborsByDistance(coord,distance);
        System.out.println("Vicini di "+ coord.toString() + " a distanza: " + distance );
        for (Sector temp: set)
            System.out.println("Vicino: "+temp.getCoordianteLetter()+temp.getCoordinateNumber());
    }
}