package it.polimi.ingsw.bogliobresich.model.match;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserTest {

    @Test
    public void test() {
        int id=1;
        String nick="nick";
        String pwd="pwd";
        
         User u=new User(id,nick,pwd);
         User u2=new User(id,nick,pwd);
         assertEquals(nick,u.getNickname());
         assertEquals(id,u.getId());
         assertEquals(pwd,u.getPassword());
         assertNotNull(u.hashCode());
         assertEquals("User [nickname=" + nick + ", id=" + id + "]",u.toString());
         assertTrue(u.equals(u2));
    }

}
