/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
     * HashMap key=CubeCoord -> value=Sector
     */
    private Map<CubeCoord,Sector> hashMap  = new HashMap<CubeCoord,Sector>(); //HashMap key = cube_coordinate -> value = sector 
    /**
     * Coordinate of human base
     */
    private Coordinate alienBaseCoordinate;
    /**
     * Coordinate of alien base
     */
    private Coordinate humanBaseCoordinate;
    /**
     * Create HexMap on default Galilei map
     */
    public HexMap(){
        this.hashMap=loadHashMapFromFile(ConstantMap.NAMEFILEMAP);
    }

    public static void main( String[] args ){
        HexMap mp=new HexMap();
        HexMapUtil.printMap(mp);
        Coordinate coord= new Coordinate ('K',8);
        Coordinate end1= new Coordinate ('J',8);
        Coordinate end2= new Coordinate ('K',6);
        HexMapUtil.printMapNeighborsByDistance(mp, coord, 2);
        HexMapUtil.printAllNeighbors(mp,coord);
        HexMapUtil.printIsValidMove(mp,coord,end1,2);
        HexMapUtil.printIsValidMove(mp,coord,end2,4);
        System.out.println("Base alieni: "+mp.alienBaseCoordinate+" Base umani: "+mp.humanBaseCoordinate);
    }
    
    /**
     * @param start coordinate where i start
     * @param end       coordinate where i want go
     * @param range     maximum distance i can walk 
     * @return boolean  true if is valid move, false instead
     */
    public boolean isValidMove(Coordinate start, Coordinate end, int range){
        Set<Coordinate> setCoord= new HashSet<Coordinate>();
        setCoord = getNeighborsByDistance(start,range);
        if(setCoord.contains(end))
            return true;
        return false;
    }
    
    /**
     * Find all crossable and not crossable neighbors of home
     * @param home where i start the search
     * @return coordinate set of all neighbors 
     */
    public Set<Coordinate>  allNeighbors(Coordinate home){
        Set<Sector> secTemp= getSectorSetFromCubeCoordSet(allNeighbors(new CubeCoord(home)));
        return createCoordinateSetFromSectorSet(secTemp);
    }
    
    /**
     * Return true if a coordinate is a porthole sector, instead return false
     * @param coord     coordinate of sector
     * @return  true if is porthole sector, false instead
     */
    public boolean coordinateIsPortholeSector(Coordinate coord){
        return this.getSectorFromCoordinate(coord) instanceof PortholeSector;
    }
    
    /**
     * Return true if a coordinate is a safe sector, instead return false
     * @param coord     that i want know
     * @return  true if is safe sector, false instead
     */
    public boolean coordinateIsSafeSector(Coordinate coord){
        return this.getSectorFromCoordinate(coord) instanceof SafeSector;
    }
    
    /**
     * Return true if a coordinate is a unsafe sector, instead return false
     * @param coord     that i want know
     * @return  true if is unsafe sector, false instead
     */
    public boolean coordinateIsUnsafeSector(Coordinate coord){
        return this.getSectorFromCoordinate(coord) instanceof UnsafeSector;
    }
    
    public Coordinate getCoordinateHumanBase(){
        return humanBaseCoordinate;
    }
    
    public Coordinate getCoordinateAlienBase(){
        return alienBaseCoordinate;
    }
    
    public boolean setPortholeStatus(Coordinate coord,boolean status){
        if(coordinateIsPortholeSector(coord)){
            PortholeSector ph= (PortholeSector) getSectorFromCoordinate(coord);
            ph.setCrossable(status);
            return true;
        }
        return false;
    }
    
    private void setHumanBaseCoordinate(Coordinate coord){
        this.humanBaseCoordinate=coord;
    }
    
    private void setAlienBaseCoordinate(Coordinate coord){
        this.alienBaseCoordinate=coord;
    }
    
    /**
     * Get Coordinate of all crossable neighbors by distance from a specific Coordinate
     * @param coord coordinate of sector that you want know its neighbors
     * @param maxDistance distance radius
     * @return  set of neighbors Coordinate
     */
    protected Set<Coordinate> getNeighborsByDistance(Coordinate coord, int maxDistance){
        Set<Sector> sectorSet = new HashSet<Sector>();
        Set<CubeCoord> hexTempSet;
        CubeCoord hex = new CubeCoord(coord);
        hexTempSet=getNeighborsByDistance(hex,maxDistance);
        for(CubeCoord hexTemp: hexTempSet){
            sectorSet.add(this.hashMap.get(hexTemp));
        }   
        return createCoordinateSetFromSectorSet(sectorSet);
    }
    
    
    /**
     * Get neighbors by distance from a specific CubeCoord
     * @param hex that you want know its neighbors
     * @param maxDistance distance radius
     * @return set of neighbors CubeCoord
     */
    protected Set<CubeCoord> getNeighborsByDistance (CubeCoord hex, int maxDistance){
        Map<CubeCoord, Sector> mp = this.hashMap;
        CubeCoord hexTemp;
        Sector secTemp;
        Set<CubeCoord> neighborsSetTemp;
        Set<Sector> sectorsToClear = new HashSet<Sector>(); //sector to clear at end
        Set<CubeCoord> neighborsSet = new HashSet<CubeCoord>(); //neighbors list to return
        Queue<CubeCoord> queue = new LinkedList<CubeCoord>(); //queue for explore map
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
                neighborsSetTemp=crossableNeighbors(hexTemp);
                for(CubeCoord hexNeighbor : neighborsSetTemp){
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
     * @return HashMap<CubeCoord,Sector>
     */
    protected Map<CubeCoord,Sector> getHashMap(){
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
     * Get the first level of crossable  hex neighbors
     * @param hex that you want know its neighbors
     * @return set of neighbors CubeCoord 
     */
    private Set<CubeCoord> crossableNeighbors (CubeCoord hex){
        CubeCoord hexTemp;
        Sector secTemp;
        int distance=1;
        Set<CubeCoord> set = new HashSet<CubeCoord>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x
                    + distance); y++) {
                int z = -x - y;
                hexTemp= new CubeCoord(hex.getX()+x,hex.getY()+y,hex.getZ()+z);
                secTemp = hashMap.get(hexTemp);
                if(secTemp!=null && secTemp.isCrossable() && !hexTemp.equals(hex))
                    set.add(hexTemp);
            }
        }
        return set;
    }
    
    /**
     * Search all crossable and not crossable neighbors
     * @param cube      home where start the search
     * @return  set of all neighbors in CubeCoord
     */
    private Set<CubeCoord> allNeighbors (CubeCoord cube){
        CubeCoord hexTemp;
        Sector secTemp;
        int distance=1;
        Set<CubeCoord> set = new HashSet<CubeCoord>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x
                    + distance); y++) {
                int z = -x - y;
                hexTemp= new CubeCoord(cube.getX()+x,cube.getY()+y,cube.getZ()+z);
                secTemp = hashMap.get(hexTemp);
                if(secTemp!=null && !hexTemp.equals(cube))
                    set.add(hexTemp);
            }
        }
        return set;
    }
    
    /**
     * Create a Coordinate Set from Sector Set
     * @param sectorSet that i want transform
     * @return  Coordinate Set
     */
    private static Set<Coordinate> createCoordinateSetFromSectorSet(Set<Sector> sectorSet){
        Set<Coordinate> coordSet=new HashSet<Coordinate>();
        for(Sector sec:sectorSet)
            coordSet.add(createCoordinateFromSector(sec));
        return coordSet;
    }
    
    /**
     * Search and return the sector set in the HashMap from a cube set
     * @param cubeSet that i want know corresponding sector Set
     * @return Sector Set
     */
    private Set<Sector> getSectorSetFromCubeCoordSet(Set<CubeCoord> cubeSet){
        Set<Sector> sectorSet=new HashSet<Sector>();
        for(CubeCoord cube: cubeSet)
           sectorSet.add(getSectorFromCube(cube));
        return sectorSet;
    }
    
    /**
     * Create a Coordinate from a Sector
     * @param sector that i want transform in coordinate
     * @return Coordinate
     */
    private static Coordinate createCoordinateFromSector(Sector sector){
        return new Coordinate(sector.getCoordinateX(),sector.getCoordinateY());
    }
    
    /**
     * Return corresponding Sector from Cube in the HashMap
     * @param cube that i want know corresponding sector
     * @return Sector
     */
    private Sector getSectorFromCube(CubeCoord cube){
        return this.hashMap.get(cube);
    }
    
    /**
     * Return corresponding Sector from Coordinate in the HashMap
     * @param coord that i want know corresponding sector
     * @return Sector
     */
    private Sector getSectorFromCoordinate(Coordinate coord){
        return getSectorFromCube(new CubeCoord(coord));
    }
    
    /**
     * create and return an HashMap with key->CubeCoord, value->Sector from a filename
     * @param fileName
     * @return HashMap<CubeCoord,Sector>  
     * @throws IOException
     */
    private Map<CubeCoord,Sector> loadHashMapFromFile (String fileName) {
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
    private Sector newSectorFromXYLetterType(int x, int y, char charType){
        if(charType=='U')
            return new UnsafeSector (x,y);
        if(charType=='S')
            return new SafeSector (x,y);
        if(charType=='D')
            return new DisableSector (x, y);
        if(charType=='1'||charType=='2'||charType=='3'||charType=='4')
            return new PortholeSector (x,y,charType-'0');
        if(charType=='H'){
            setHumanBaseCoordinate(new Coordinate(x,y));
            return new HumanBaseSector (x,y);
            }
        if(charType=='A'){
            setAlienBaseCoordinate(new Coordinate(x,y));
            return new AlienBaseSector (x,y);
            }
        return new DisableSector (x,y);
    }
    
}