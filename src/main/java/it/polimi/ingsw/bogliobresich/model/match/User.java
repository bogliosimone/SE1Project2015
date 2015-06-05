/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

/**
 * @author simoneboglio
 *
 */
public class User {
    private String nickname;
    User(String nickname){
        this.nickname=nickname;
    }
    
    public String getNickname(){
        return this.nickname;
    }
}
