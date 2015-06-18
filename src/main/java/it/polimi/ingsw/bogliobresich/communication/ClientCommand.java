/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;

import java.io.Serializable;

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
    
    public ItemCard getCard() {
        if(parameter instanceof ItemCard) {
            return (ItemCard) parameter;
        }
        return null;
    }
}
