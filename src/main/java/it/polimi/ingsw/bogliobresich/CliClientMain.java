/**
 * 
 */
package it.polimi.ingsw.bogliobresich;

import it.polimi.ingsw.bogliobresich.CLI.CLIClient;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * @author matteo bresich
 * @author simone boglio
 *
 * This class is the entry point of the Application Process.
 * Main method launches all CLI components. 
 */
public final class CliClientMain {
    public static void main (String[] args) {
        
        CLIClient client = null;
        ExecutorService executor = null;
        
        try {
            executor = Executors.newSingleThreadExecutor();
            client = new CLIClient();
            executor.submit(client);
        }
        catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(executor != null) {
                executor.shutdownNow();
            }   
        } 
    }
}