/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.match.User;

/**
 * @author simoneboglio
 *
 */
public class AddPlayerAction implements Action {
    User user;
    
    public AddPlayerAction(User user){
        this.user=user;
    }
    
    public User getUser(){
        return this.user;
    }
}
