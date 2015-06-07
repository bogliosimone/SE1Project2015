package it.polimi.ingsw.bogliobresich.model.match;

import java.util.ArrayList;
import java.util.List;

public class ProvaMatch {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        User u1= new User("pippo");
        User u2= new User("pluto");
        User u3= new User("gatto");
        List<User> users=new ArrayList<User>();
        users.add(u1);
        users.add(u2);
        users.add(u3);
        Match match = new Match(users);
        System.out.println(match.getNextPlayer(null));
    }

}
