/**
 * 
 */
package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;

import it.polimi.ingsw.bogliobresich.model.match.User;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author matteobresich
 *
 */
public class UserList {

    
    private Map <User, Proprieties> map;
    
    public UserList() {
        this(null,null,null);
    }
    
    public UserList(List <User> userList, List <Color> color, List <String> state) {
        map = new HashMap <User, Proprieties> ();
        
        if((userList != null)&&(color != null)&&(state != null)) {
            if((userList.size() == color.size())&&(userList.size() == state.size())) {
                int index = 0;
                for(User user : userList) {
                    map.put(user, new Proprieties(color.get(index),state.get(index)));
                    index++;
                }
            } else {
                throw new RuntimeException();
            }
            
        }
    }
    
    class Proprieties {
        private Color color;
        private String state;
        public Proprieties(Color c, String state) {
            this.color = c;
            this.state = state;
        }
        
        public Color getColor() {
            return this.color;
        }
        
        public String getState() {
            return this.state;
        }
    }
    
    public Color getColorByUser(User u) {
        return map.get(u).getColor();
    }
    
    public String getStateByUser(User u) {
        return map.get(u).getState();
    }
    
    public void setUserColor(User u, Color c) {
        Proprieties p = map.get(u);
        if(p != null) {
            map.put(u, new Proprieties(c,p.getState()));
        }
    }
    
    public void setUserState(User u, String state) {
        Proprieties p = map.get(u);
        if(p != null) {
            map.put(u, new Proprieties(p.getColor(),state));
        }
    }
    
    public void addUser(User u, Color c, String state) {
        if(map != null) {
            map.put(u, new Proprieties(c,state));
        }
    }
}
