/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static java.lang.Math.max;
import static java.lang.Math.min;
import it.polimi.ingsw.bogliobresich.model.map.sector.*;

/**
 * @author simoneboglio
 *
 */
public class HexMap {
    private HashMap<Hex,Sector> hexMap  = new HashMap<Hex,Sector>(); //HashMap key = cube_coordinate -> value = sector 
    private Sector sect;
    private OffsetCoord offsetCoord;
    private Hex hexCoord;

    public HexMap(){
        try{
            this.loadMapFromFile("src/main/resources/galilei.txt");
        }
        catch(IOException e)
        {
            System.out.println(e.toString());
        }
        /*        for (int r=0; r<row; r++){
            for (int c=0; c<coloumn; c++){
                sect = newSectorFromXYLetterType(c+1,r+1,charmapYX[c][r]);
                offsetCoord = new OffsetCoord (sect.getCoordinateX(),sect.getCoordinateY());
                hexCoord = OffsetCoord.offsetToCube(offsetCoord);
                hexMap.put(hexCoord,sect);
                //System.out.println(sect.toString()+" Offset: "+offsetCoord.toString()+" Hex: "+hexCoord.toString());
            }
        }*/
    }
    
    public static void main( String[] args ){
        HexMap mappa=new HexMap();
        mappa.printMap();
        mappa.printNeighbors(new Hex (1,-1,0));
    }


    public void printMap() {
        @SuppressWarnings("unchecked")
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

    public Set<Hex> neighborsByDistance (Hex hex, int distance){
        Hex hexTemp;
        Sector secTemp;
        Set<Hex> set = new HashSet<Hex>();
        for (int x = -distance; x <= distance; x++) {
            for (int y = max(-distance, -x - distance); y <= min(distance, -x
                    + distance); y++) {
                int z = -x - y;
                hexTemp= new Hex(hex.getX()+x,hex.getY()+y,hex.getZ()+z);
                secTemp = hexMap.get(hexTemp);
                if(secTemp!=null && secTemp.isCrossable()==true)
                    set.add(new Hex(hex.getX()+x,hex.getY()+y,hex.getZ()+z));
            }
        }
        return set;
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

    public void printNeighbors(Hex hex){
        Set<Hex> set = neighbors(hex);
        System.out.println("Vicini raggiungibili da "+hex.toString());
        for (Hex temp: set)
            System.out.println("Vicino: "+temp);
    }

    public void loadMapFromFile (String fileName) throws IOException {
        int column=ConstantMap.COLUMNMAP;
        int row=ConstantMap.ROWMAP;
        char letterType;
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
                System.out.println(sect.toString());
                hexMap.put(hexCoord,sect);  
            }
        }
        buffer.close();
    }

    public Sector newSectorFromXYLetterType(int x, int y, char type){
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
}