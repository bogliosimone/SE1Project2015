/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.map;

import it.polimi.ingsw.bogliobresich.model.map.sector.*;

/**
 * @author simoneboglio
 *
 */
public class HexMapUtils {
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
}
