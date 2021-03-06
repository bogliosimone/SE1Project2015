/**
 * 
 */
package it.polimi.ingsw.bogliobresich;

import it.polimi.ingsw.bogliobresich.GUI.GUIController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * This class is the entry point of the Application Process.
 * Main method launches all GUI components.
 * 
 * @author matteo bresich
 * @author simone boglio 
 * @version 0.9
 * 
 */
public class GuiClientMain {
    public static void main (String[] args) {

        GUIController guiController = null;
        ExecutorService executor = null;

        try {
            executor = Executors.newSingleThreadExecutor();
            guiController = GUIController.getInstance();
            executor.submit(guiController);
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
