/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.io.Serializable;

/**
 * user used by client and match for the game, this class is serializable
 * @author simoneboglio
 *
 */
public class User implements Serializable {

    private static final long serialVersionUID = 2007938065543320937L;
    private String nickname;
    private String password;
    private int id;
    public User(int id,String nickname,String password){
        this.id = id;
        this.nickname=nickname;
        this.password=password;
    }
    
    public String getNickname(){
        return this.nickname;
    }
    
    public String getPassword(){
        return this.password;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "User [nickname=" + nickname + ", id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result
                + ((nickname == null) ? 0 : nickname.hashCode());
        result = prime * result
                + ((password == null) ? 0 : password.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id != other.id)
            return false;
        if (nickname == null) {
            if (other.nickname != null)
                return false;
        } else if (!nickname.equals(other.nickname))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }
}
