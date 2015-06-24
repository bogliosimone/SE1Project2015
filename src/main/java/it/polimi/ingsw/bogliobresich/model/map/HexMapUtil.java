/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;


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
     * Print the HexMap
     * @param hexMap
     */
    public static void printMap(HexMap hexMap) {
        Map<CubeCoord,Sector> mp= hexMap.getHashMap();
        Set<CubeCoord> keys = mp.keySet();
        for(CubeCoord key : keys){
            Sector sec = mp.get(key);
            printString(sec + " " + key);
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
        printString("Vicini di "+hex.toString()+" a distanza: "+ maxDistance );
        for (CubeCoord temp: set)
            printString("Vicino: "+temp);
    }
    
    /**
     * Print all the crossable neighbors of hexagon in a specific range with letter-number coordinate value
     * @param hexMap
     * @param coord
     * @param maxDistance
     */
    public static void printMapNeighborsByDistance(HexMap hexMap,Coordinate coord, int maxDistance){
        Set<Coordinate> set = hexMap.getNeighborsByDistance(coord, maxDistance);
        printString("Vicini di "+ coord.toString() + " a distanza: " + maxDistance );
        for (Coordinate temp: set)
            printString("Vicino: "+temp.getLetter()+temp.getNumber());
    }
    
    public static void printAllNeighbors(HexMap hexMap,Coordinate coord){
        Set<Coordinate> set = hexMap.allNeighbors(coord);
        printString("TUTTI i vicini di "+ coord.toString());
        for (Coordinate temp: set)
            printString("Vicino: "+temp.getLetter()+temp.getNumber());
        
    }
    
    public static void printIsValidMove(HexMap hexMap,Coordinate start,Coordinate end,int range){
        boolean isValid=hexMap.isValidMove(start, end, range);
        if (isValid)
            printString(start+" può raggiungere "+end);
        else
            printString(start+" non può raggiungere "+end);
    }
    
    private static void printString(String string){
        System.out.println(string);
    }
    
}
