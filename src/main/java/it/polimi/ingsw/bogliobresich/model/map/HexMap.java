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

import static java.lang.Math.max;
import static java.lang.Math.min;
import it.polimi.ingsw.bogliobresich.model.map.sector.*;
import it.polimi.ingsw.bogliobresich.model.map.HexMapUtil;

/**
 * Manage a map of hexagon Sector
 * @author simoneboglio
 *
 */
public class HexMap {
    /**
     * HashMap key=Hex -> value=Sector
     */
    private Map<Hex,Sector> hashMap  = new HashMap<Hex,Sector>(); //HashMap key = cube_coordinate -> value = sector 

    /**
     * Create HexMap on default Galilei map
     */
    public HexMap(){
        this.hashMap=HexMapUtil.loadHashMapFromFile(ConstantMap.NAMEFILEMAP);
    }

    public static void main( String[] args ){
        HexMap mp=new HexMap();
        HexMapUtil.printMap(mp);
        Coordinate coord= new Coordinate ('K',8);
        HexMapUtil.printMapNeighborsByDistance(mp, coord, 2);
    }

    /**
     * Get neighbors by distance from a specific Coordinate
     * @param coord coordinate of sector that you want know its neighbors
     * @param maxDistance distance radius
     * @return  set of neighbors Sector 
     */
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
    
    
    /**
     * Get neighbors by distance from a specific Hex
     * @param hex that you want know its neighbors
     * @param maxDistance distance radius
     * @return set of neighbors Hex
     */
    public Set<Hex> getNeighborsByDistance (Hex hex, int maxDistance){
        Map<Hex, Sector> mp = this.hashMap;
        Hex hexTemp;
        Sector secTemp;
        Set<Hex> neighborsSetTemp;
        Set<Sector> sectorsToClear = new HashSet<Sector>(); //sector to clear at end
        Set<Hex> neighborsSet = new HashSet<Hex>(); //neighbors list to return
        Queue<Hex> queue = new LinkedList<Hex>(); //queue for explore map
        secTemp = mp.get(hex);
        if(secTemp==null)
            return neighborsSet;
        secTemp.setDistance(0); //hex where i start the search
        queue.add(hex);
        hexTemp=queue.poll();
        while( hexTemp!=null){       
            secTemp = mp.get(hexTemp);
            sectorsToClear.add(secTemp);
            if(!hexTemp.equals(hex))
                neighborsSet.add(hexTemp);
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
        return neighborsSet;
    }
    
    /**
     * Get the HashMap of the map
     * @return HashMap<Hex,Sector>
     */
    protected Map<Hex,Sector> getHashMap(){
        return this.hashMap;
    }
    
    /**
     * Reset all the sector distance used for found neighbors
     * @param secToClear        set of Sector to clean
     */
    private void resetDistanceSectors(Set<Sector> secToClear){
        for(Sector sector : secToClear){
            sector.resetDistance();
        }
    }
    
    /**
     * Get the first level of hex neighbors
     * @param hex that you want know its neighbors
     * @return set of neighbors Hex 
     */
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