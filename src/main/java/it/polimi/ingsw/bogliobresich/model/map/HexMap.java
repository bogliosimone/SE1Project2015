/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.io.IOException;

import static java.lang.Math.max;
import static java.lang.Math.min;
import it.polimi.ingsw.bogliobresich.model.map.sector.*;
import it.polimi.ingsw.bogliobresich.model.map.HexMapUtil;

/**
 * @author simoneboglio
 *
 */
public class HexMap {
    private Map<Hex,Sector> hashMap  = new HashMap<Hex,Sector>(); //HashMap key = cube_coordinate -> value = sector 

    public HexMap(){
        try{
            this.hashMap=HexMapUtil.loadHashMapFromFile(ConstantMap.NAMEFILEMAP);
        }
        catch(IOException e)
        {
            System.err.println(e.toString()+ " Errore apertura file mappa");
        }
    }

    public static void main( String[] args ){
        HexMap mp=new HexMap();

        HexMapUtil.printMap(mp);
        Coordinate coord= new Coordinate ('K',8);
        HexMapUtil.printMapNeighborsByDistance(mp, coord, 2);
    }

    public Set<Sector> getNeighborsByDistance(Coordinate coord, int maxDistance){
        Set<Sector> sectorSet = new HashSet<Sector>();
        Set<Hex> hexTempSet;
        Hex hex = new Hex(coord);
        hexTempSet=getNeighborsByDistance(hex,maxDistance);
        for(Hex hexTemp: hexTempSet){
            sectorSet.add(this.hashMap.get(hexTemp));
        }   
        return sectorSet;
    }

    public Set<Hex> getNeighborsByDistance (Hex hex, int maxDistance){
        Map<Hex, Sector> mp = this.hashMap;
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
    
    protected Map<Hex,Sector> getHexMap(){
        return this.hashMap;
    }
    
    private void resetDistanceSectors(Set<Sector> secToClear){
        for(Sector sector : secToClear){
            sector.resetDistance();
        }
    }
    
    private Set<Hex> neighbors (Hex hex){
        Hex hexTemp;
        Sector secTemp;
        int distance=1;
        Set<Hex> set = new HashSet<Hex>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x
                    + distance); y++) {
                int z = -x - y;
                hexTemp= new Hex(hex.getX()+x,hex.getY()+y,hex.getZ()+z);
                secTemp = hashMap.get(hexTemp);
                if(secTemp!=null && secTemp.isCrossable() && hexTemp!=hex)
                    set.add(hexTemp);
            }
        }
        return set;
    }
}