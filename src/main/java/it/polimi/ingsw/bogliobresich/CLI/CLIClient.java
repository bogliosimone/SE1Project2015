/**
 * 
 */
package it.polimi.ingsw.bogliobresich.CLI;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.communication.client.ClientController;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;
import it.polimi.ingsw.bogliobresich.model.player.Player;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.Set;

/**
 * @author matteobresich
 * @author simoneboglio
 */
public class CLIClient implements Observer, Runnable{
    Scanner scanner = new Scanner(System.in); //scanner.close();
    HexMap gameMap= new HexMap();
    Player player;
    ClientController controller;

    @Override
    public void run() {
        controller = ClientController.getInstance();
        controller.addObserver(this);
        printString("*Benvenuto in Escape from the Aliens in Outer Space*");
        controller.doLogin(getNickname() , getPassword()); 
    }


    @Override
    public void update(Observable o, Object obsNotification) {
        NotificationMessage notification = controller.getQueueNotification().poll();
//        if(obsNotification instanceof NotificationMessage)
//        {
            User u;
            String s;
            Coordinate coord;
            ItemCard c;
            Player p;
            char letter;
            int number;

//            NotificationMessage notification = (NotificationMessage)obsNotification;

            Commands command = notification.getCommand();
            switch(command) {
            case LIST_PLAYERS_END_GAME:
                List<Player> tmpList = notification.getListOfPlayers();
                printString("***Lista vincitori e sconfitti***");
                for(Player tmp:tmpList){
                    s=" ha perso";
                    if(tmp.isWinner())
                        s=" ha vinto";
                    printString(tmp.getNickName()+s+" - Natura: "+tmp.getCharacterCard().getCharacterType()+" - Personaggio: "+tmp.getCharacterCard().getCharacterName());
                }
                break;
            case GAME_MAP_FILE_NAME:
                gameMap= new HexMap(notification.getString());
                printString("Stai giocando sulla mappa: "+(notification.getString()).replace(".txt", "").toUpperCase());
                break;
            case ATTACK:
                printString(notification.getString());
                break;
            case CALL_RUMOR:
                new Thread (new Runnable() { public void run() {
                printString("Inserisci coordinata (lettera-numero): ");
                 String s=readString();
                char letter=getLetterCoordianteFromString(s);
                int number=getNumberCoordinateFromString(s);
                controller.sendCommand(new ClientCommand(CommandType.DO_RUMOR_IN_COORDINATE_REQUEST,new Coordinate(letter,number)));
                }
                }).start();
                break;
            case CANT_DISCARD_CARD:
                printString("Errore non puoi scartare questa carta");
                break;
            case CANT_PLAY_CARD:
                printString("Errore, non puoi giocare questa carta");
                break;
            case CARDS_END:
                printString("Le carte oggetto sono finite");
                break;
            case COORDINATE_ERROR:
                printString("Le coordinate immesse non sono valide");
                break;
            case DISCARD_CARD:
                c= notification.getItemCard();
                player.getHand().removeCard(c);
                printString("Hai scartato la carta "+c.getName());
                break;
            case DISCARD_HAND:
                player.getHand().discardHand();
                printString("Hai scartato la tua mano");
                break;
            case DRAW_CARD:
                c=notification.getItemCard();
                player.getHand().addCard(c);
                printString("Hai pescato la carta: "+c.getName());
                break;
            case DRAW_SECTOR_CARD:
                SectorCard sc=notification.getSectorCard();
                s="Hai pescato la carta settore ";
                if(sc.isThereNoiseInAnySector())
                    s=s+"RUMORE IN QUALUNQUE SETTORE";
                if(sc.isThereNoiseInMySector())
                    s=s+"RUMORE NEL TUO SETTORE";
                if(sc.isThereSilence())
                    s=s+"SILENZIO";
                printString(s);
                break;
            case END_TURN:
                printString("Il tuo turno è finito");
                break;
            case FATAL_ERROR:
                printString(notification.getString());
                break;
            case GAME_END:
                printString("La partita è finita, digita exit per uscire");
                boolean exit=false;
                while(!exit){
                    if(readString().equals("exit"))
                        exit=true;
                }
                System.exit(0);
                break;
            case GAME_INFO_MESSAGE:
                printString(notification.getString());
                break;
            case GAME_START:
                printString("La partita è iniziata");
                break;
            case GENERIC_ERROR:
                printString(notification.getString());
                break;
            case GENERIC_MESSAGE:
                printString(notification.getString());
                break;
            case HAND_FULL:
                printString("La tua mano è piena");
                break;
            case IS_NOT_YOUR_TURN:
                printString("Non è il tuo turno, attendi");
                break;
            case ITEM_PLAYED:
                printString(notification.getString());
                break;
            case LIST_USERS:
                List<User> users = notification.getListOfUsers();
                s=new String("Lista giocatori");
                int i=1;
                for(User tmpUser:users)
                    s=s+"\n"+(i++)+" - "+tmpUser.getNickname();
                printString(s);
                break;
            case MOVES_AVAIABLE:
                final MovesAvaiable m=notification.getMovesAvaiable();
                new Thread (new Runnable() { public void run() {
                do{
                    printString(createStringMovesAvaiable(m));
                }while(!getInputAndSend(m));
                }
                }).start();
               
                break;
            case MOVE_NO_AVAIABLE:
                printString("Mossa non disponibile");
                break;
            case PLAYER_JOIN_WAIT_ROOM:
                printString("Sei entrato nella sala di attesa - attendi l'inizio della partita");
                break;
            case PLAYER_MESSAGE:
                printString(notification.getString());
                break;
            case PORTHOLE_BROKEN:
                coord=notification.getCoordinate();
                printString("La scialuppa numero "+gameMap.getNumberPorthole(coord)+" in coordinate "+coord.getLetter()+coord.getNumber()+" non è più utilizzabile");
                break;
            case SECTOR_TYPE_MESSAGE:
                printString(notification.getString()); 
                break;
            case SET_YOUR_COORDINATE:
                coord=notification.getCoordinate();
                printString("Ti trovi nelle coordinate: "+coord.getLetter()+coord.getNumber());
                break;
            case START_END_PHASE:
                printString("*Sei nella end-phase*");
                break;
            case START_MOVEMENT_PHASE:
                printString("*Sei nella fase di movimento*");
                break;
            case START_TIMER:
                printString("Hai 2 minuti per concludere il turno");
                break;
            case START_TURN:
                printString("*Il tuo turno è iniziato - turno "+notification.getInteger()+"*");
                break;
            case USER_DISCONNECTED:
                u=notification.getUser();
                printString("----------------------\nIl giocatore "+u.getNickname()+" si è disconnesso\n----------------------");
                break;
            case HUMAN_ESCAPE:
                p=notification.getPlayer();
                printString(p.getNickName()+" personaggio: "+p.getCharacterCard().getCharacterName()+" natura:"+p.getCharacterCard().getCharacterType()+" ha lasciato l'astronave, "+p.getNickName()+" ha vinto");
                break;
            case PLAYER_DIE:
                p=notification.getPlayer();
                printString(p.getNickName()+" personaggio: "+p.getCharacterCard().getCharacterName()+" natura:"+p.getCharacterCard().getCharacterType()+" è morto");
                break;
            case USER_END_TURN:
                u=notification.getUser();
                printString("Il giocatore "+u.getNickname()+" ha termianto il suo turno");
                break;
            case USER_START_TURN: 
                u=notification.getUser();
                printString("----------------------\nIl giocatore "+u.getNickname()+" ha iniziato il suo turno");
                break;
            case WHO_ARE_YOU:
                p = notification.getPlayer();
                printString(p.getNickName());
                player=p;
                printString("*Il tuo personaggio è : "+p.getCharacterCard().getCharacterName()+" natura: "+p.getCharacterCard().getCharacterType()+"*");
                break;
            case YOU_ARE_FEED:
                printString("Ti sei nutrito di umano, ora puoi muoverti di tre caselle");
                break;
            case YOU_DIE:
                printString("Sei stato ucciso! la tua partita finisce qua, attendi la fine del match");
                break;
            case YOU_DISCONNECTED:
                printString("Sei stato disconnesso dal gioco per inattività");
                break;
            case YOU_LOST:
                printString("Mi dispiace ma HAI PERSO :(");
                break;
            case YOU_WIN:
                printString("Complimenti HAI VINTO :)");
                break;
            default:
                printString(command.toString()+" comando non supportato");
                break; 
            }





        }

//    }



    private void printString(String s){
        System.out.println(s);
    }

    private String getNickname(){
        printString("Inserisci nickname: ");
        return readString();
    }

    private String getPassword(){
        printString("Inserisci password: ");
        return readString();
    }

    private String readString(){
        String s=scanner.nextLine();
        return s;
    }

    private String createStringMovesAvaiable(MovesAvaiable m){
        String s= new String("Mosse disponibili:");
        if(m.canMove())
            s=s+" - MUOVI";
        if(m.canPlayItem())
            s=s+" - GIOCA OGGETTO";
        if(m.canAttack())
            s=s+" - ATTACCA";
        if(m.canCallRumor())
            s=s+" - CHIAMA RUMORE";
        if(m.canDiscardItemCard())
            s=s+" - SCARTA OGGETTO";
        if(m.canDrawSectorCard())
            s=s+" - PESCA CARTA SETTORE";
        if(m.canEndTurn())
            s=s+" - TERMINA TURNO";
        if(m.canGoInEndPhase())
            s=s+" - TERMINA FASE DI MOVIMENTO";
        if(m.canMove()){
            s=s+"\nLista settori in cui puoi muoverti";
            Set<Coordinate> setCoord=m.getReachableCoordinate();
            for(Coordinate coord: setCoord){
                s=s+" - "+coord.getLetter()+coord.getNumber();
            }
        }
        return s;
    }

    private boolean getInputAndSend(MovesAvaiable m){
        String command=readString();
        String s;
        command=command.toLowerCase();
        if(command.equals("move")&&m.canMove()){
            printString("Inserisci coordinate (lettera-numero): ");
            s=readString();
            char letter=getLetterCoordianteFromString(s);
            int number=getNumberCoordinateFromString(s);
            controller.sendCommand(new ClientCommand(CommandType.DO_MOVE_REQUEST,new Coordinate(letter,number)));
            return true;
        }
        if(command.equals("attack")&&m.canAttack()){
            controller.sendCommand(new ClientCommand(CommandType.DO_ATTACK_REQUEST,null));
            return true;
        }
        if(command.equals("draw sector")&&m.canDrawSectorCard()){
            controller.sendCommand(new ClientCommand(CommandType.DO_DRAW_SECTOR_REQUEST,null));
            return true;
        }
        if(command.equals("end movement")&&m.canGoInEndPhase()){
            controller.sendCommand(new ClientCommand(CommandType.DO_END_MOVEMENT_PHASE_REQUEST,null));
            return true;
        }
        if(command.equals("end turn")&&m.canEndTurn()){
            controller.sendCommand(new ClientCommand(CommandType.DO_END_TURN_REQUEST,null));
            return true;
        }
        if(command.equals("item")&&m.canPlayItem()){
            if(player.getHand().isEmpty()){
                printString("Non hai carte in mano da giocare");
                return false;
            }
            List<ItemCard> tmpList=player.getHand().getAllCard();
            printString("Lista carte in mano");
            for(ItemCard tmpCard:tmpList)
                printString("- "+tmpCard.getName() + " id:" +tmpCard.getId()+" ");
            printString("Inserisci id carta da giocare: ");
            int idCard=idCardFromString(readString());
            ItemCard card=player.getHand().getCard(idCard);
            if(card==null){
                printString("Non possiedi questa carta");
                return false;
            }
            if(card instanceof SpotlightItemCard){
                printString("Inserisci coordinata (lettera-numero) su cui far luce: ");
                s=readString();
                char letter=getLetterCoordianteFromString(s);
                int number=getNumberCoordinateFromString(s);
                ((SpotlightItemCard) card).setCoordToLight(new Coordinate(letter,number));
            }
            controller.sendCommand(new ClientCommand(CommandType.DO_PLAY_ITEM_REQUEST,card));
            return true;
        }
        if(command.equals("discard")&&m.canDiscardItemCard()){
            if(player.getHand().isEmpty()){
                printString("Non hai carte in mano da scartare");
                return false;
            }
            List<ItemCard> tmpList=player.getHand().getAllCard();
            printString("Lista carte in mano");
            for(ItemCard tmpCard:tmpList)
                printString("- "+tmpCard.getName() + " id:" +tmpCard.getId()+" ");
            printString("Inserisci id carta da scartare: ");
            int idCard=idCardFromString(readString());
            ItemCard card=player.getHand().getCard(idCard);
            if(card==null){
                printString("Non possiedi questa carta");
                return false;
            }
            controller.sendCommand(new ClientCommand(CommandType.DO_DISCARD_ITEM_REQUEST,card));
            return true;
        }
        printString("Comando non valido");
        return false;
    }
    
    private char getLetterCoordianteFromString(String s){
        s=s.toUpperCase();
        return s.charAt(0);
    }
    
    private int getNumberCoordinateFromString(String s){
        s=s.substring(1,s.length());
        try{
        int numb=Integer.parseInt(s);
        return numb;
        }
        catch(Exception e){
            return 0;
        }
        
    }
    
    private int idCardFromString(String s){
        try{
            int numb=Integer.parseInt(s);
            return numb;
            }
            catch(Exception e){
                return 0;
            }
    }
}
