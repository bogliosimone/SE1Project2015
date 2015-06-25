package it.polimi.ingsw.bogliobresich.model;

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
    
    public int getNumberMap(){
        return this.number;
    }
    
    public String getNameMap(){
        return this.name;
    }
    
    public String getFileNameMap(){
        return this.fileName;
    }
    
    public int getNumberPlayerMap(){
        return this.numberPlayer;
    }
}
