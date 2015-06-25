/**
 * 
 */
package it.polimi.ingsw.bogliobresich;

import it.polimi.ingsw.bogliobresich.communication.server.Server;
import it.polimi.ingsw.bogliobresich.model.Maps;

import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author matteo bresich
 * @author simone boglio
 * This class is the entry point of the Server Process.
 * Main method launches all server components. 
 */
public class ServerMain {
    public static void main (String [] args) {
        Server server = null;
        ExecutorService executor = null;
        boolean validMap=false;
        Scanner scanner = new Scanner(System.in);
        String map = Maps.GALILEI.getFileNameMap();
        int numberPlayer = Maps.GALILEI.getNumberPlayerMap();
        String s;
        while(!validMap){
            printString("Scegli mappa (numero):");
            for(Maps tmpMap:Maps.values()){
                printString(tmpMap.getNumberMap()+" - "+tmpMap.getNameMap());
            }
            try{
                s=scanner.nextLine();
                int n=Integer.parseInt(s);
                for(Maps tmpMap:Maps.values()){
                    if(tmpMap.getNumberMap()==n){
                        map=tmpMap.getFileNameMap();
                        numberPlayer=tmpMap.getNumberPlayerMap();
                        validMap=true;
                    }
                }
                if(!validMap){
                    printString("Comando non valido");
                }
            }
            catch(Exception e){ 
                printString("Comando non valido");
                validMap=false;
            }
        }
        scanner.close(); 

        try {
            executor = Executors.newSingleThreadExecutor();
            server = Server.getInstance();
            Server.selectMap(map);
            Server.setNumberOfPlayerInMap(numberPlayer);
            executor.submit(server);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(server != null) {
                server.shutdownNow();
            }
            if(executor != null) {
                executor.shutdownNow();
            }   
        } 
    }

    /***/
    private ServerMain () 
    {}


    private static void printString(String s){
        System.out.println(s);
    }
}
