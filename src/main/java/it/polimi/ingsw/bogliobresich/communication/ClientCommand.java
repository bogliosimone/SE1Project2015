/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication;

import java.io.Serializable;

import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

/**
 * @author matteobresich
 *
 */
public class ClientCommand implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = 7567986721790606181L;
    private CommandType commandType = null;
    private Object parameter = null;
    
    public ClientCommand (CommandType commandType, Object parameter) {
        this.commandType = commandType;
        this.parameter = parameter;
    }
    
    public CommandType getCommandType() {
        return commandType;
    }
    
    public Coordinate getCoordinate() {
        if(parameter instanceof Coordinate) {
            return (Coordinate) parameter;
        }
        return null;
    }   
}
