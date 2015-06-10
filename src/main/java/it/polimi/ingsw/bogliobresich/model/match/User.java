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
    private int id;
    public User(int id,String nickname){
        this.nickname=nickname;
    }
    
    public String getNickname(){
        return this.nickname;
    }

    public int getId() {
        return id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result
                + ((nickname == null) ? 0 : nickname.hashCode());
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
        return true;
    }
}
