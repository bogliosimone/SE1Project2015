package it.polimi.ingsw.bogliobresich.model;


/**
 * The <code>Maps</code> enum contains all the maps for the game.
 * 
 * @author matteo bresich
 * @author simone boglio
 *  
 * @version 1.0
 * 
 */
public enum Maps {
    GALILEI(1,"Galilei","galilei.txt",8),
    GALVANI(2,"Galvani","galvani.txt",8),
    FERMI(3,"Fermi","fermi.txt",8),
    SOCRATES(4,"Socrates","socrates.txt",8),
    DILEMMA(5,"Dilemma","dilemma.txt",2);
    
    private int number;
    private String name;
    private String fileName;
    private int numberPlayer;
    
    Maps(int number,String name,String fileName, int numberPlayer){
        this.number=number;
        this.name=name;
        this.fileName=fileName;
        this.numberPlayer=numberPlayer;
    }
    
    /**
     * @return the number of the map
     */
    public int getNumberMap(){
        return this.number;
    }
    
    /**
     * @return the name of the map
     */
    public String getNameMap(){
        return this.name;
    }
    
    /**
     * @return the filesystem name of the map file 
     */
    public String getFileNameMap(){
        return this.fileName;
    }
    
    /**
     * @return the maximum number of the player for the map 
     */
    public int getNumberPlayerMap(){
        return this.numberPlayer;
    }
}
