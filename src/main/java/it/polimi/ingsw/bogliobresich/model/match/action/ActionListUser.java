/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match.action;

import it.polimi.ingsw.bogliobresich.model.match.User;

import java.util.ArrayList;
import java.util.List;

/**
 * action for send to match the list of all users for the game
 * @author simoneboglio
 */
public class ActionListUser implements Action {
    List<User> users=new ArrayList<User>();
    
    public ActionListUser(List<User> users){
        this.users=users;
    }
    
    public List<User> getListUser(){
        return this.users;
    }
}
