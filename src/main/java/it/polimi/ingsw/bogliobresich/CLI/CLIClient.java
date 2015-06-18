/**
 * 
 */
package it.polimi.ingsw.bogliobresich.CLI;

import it.polimi.ingsw.bogliobresich.communication.ClientCommand;
import it.polimi.ingsw.bogliobresich.communication.CommandType;
import it.polimi.ingsw.bogliobresich.communication.client.Client;
import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SectorCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.map.HexMap;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.Commands;
import it.polimi.ingsw.bogliobresich.model.notifications.MovesAvaiable;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationQueue;
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
public class CLIClient implements Observer{
    Scanner scanner = new Scanner(System.in); //scanner.close();
    HexMap gameMap= new HexMap();
    Player player;
    Client controller = new Client((Observer)this);
    
    public CLIClient () {
        controller.doLogin(getNickname() , getPassword()); 
    }
    
    

    @Override
    public void update(Observable obsQueue, Object arg) {
        if(obsQueue instanceof NotificationQueue) {
            final NotificationQueue queue = (NotificationQueue)obsQueue;
            new Thread(new Runnable()
            {
                @Override
                public void run() {
                    while (!queue.isEmpty()) {
                        Commands command = queue.getNotificationCommand();
                        User u;
                        String s;
                        Coordinate coord;
                        ItemCard c;
                        switch(command) {
                        case ATTACK:
                            printString(queue.getString());
                            break;
                        case CALL_RUMOR:
                            printString("Inserisci coordinata lettera: ");
                            char letter=scanner.nextLine().charAt(0);
                            printString("Inserisci coordinata numero: ");
                            int number=Integer.parseInt(scanner.nextLine());
                            controller.sendCommand(new ClientCommand(CommandType.DO_RUMOR_IN_COORDINATE_REQUEST,new Coordinate(letter,number)));
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
                            c= queue.getItemCard();
                            player.getHand().removeCard(c);
                            printString("Hai scartato la carta "+c.toString());
                            break;
                        case DISCARD_HAND:
                            player.getHand().discardHand();
                            printString("Hai scartato la tua mano");
                            break;
                        case DRAW_CARD:
                            c=queue.getItemCard();
                            player.getHand().addCard(c);
                            printString("Hai pescato la carta: "+c.toString());
                            break;
                        case DRAW_SECTOR_CARD:
                            SectorCard sc=queue.getSectorCard();
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
                            printString(queue.getString());
                            break;
                        case GAME_END:
                            printString("La partita è finita, digita exit per uscire");
                            break;
                        case GAME_INFO_MESSAGE:
                            printString(queue.getString());
                            break;
                        case GAME_START:
                            printString("La partita è iniziata");
                            break;
                        case GENERIC_ERROR:
                            printString(queue.getString());
                            break;
                        case GENERIC_MESSAGE:
                            printString(queue.getString());
                            break;
                        case HAND_FULL:
                            printString("La tua mano è piena");
                            break;
                        case IS_NOT_YOUR_TURN:
                            printString("Non è il tuo turno, attendi");
                            break;
                        case ITEM_PLAYED:
                            printString(queue.getString());
                            break;
                        case LIST_USERS:
                            List<User> users = queue.getListOfUsers();
                            s=new String("Lista giocatori");
                            for(User tmpUser:users)
                                s=s+" - "+tmpUser.getNickname();
                            printString(s);
                            break;
                        case MOVES_AVAIABLE:
                            MovesAvaiable m=queue.getMovesAvaiable();
                            do{
                                printString(createStringMovesAvaiable(m));
                            }while(!getInputAndSend());
                            break;
                        case MOVE_NO_AVAIABLE:
                            printString("Mossa non disponibile");
                            break;
                        case PLAYER_JOIN_WAIT_ROOM:
                            printString("Sei entrato nella sala di attesa - attendi l'inizio della partita");
                            break;
                        case PLAYER_MESSAGE:
                            printString(queue.getString());
                            break;
                        case PORTHOLE_BROKEN:
                            coord=queue.getCoordinate();
                            printString("La scialuppa numero "+gameMap.getNumberPorthole(coord)+" in coordinate "+coord.getLetter()+coord.getNumber()+" non è più utilizzabile");
                            break;
                        case SECTOR_TYPE_MESSAGE:
                            printString(queue.getString()); 
                            break;
                        case SET_YOUR_COORDINATE:
                            coord=queue.getCoordinate();
                            printString("Ti trovi nelle coordinate: "+coord.getLetter()+coord.getNumber());
                            break;
                        case START_END_PHASE:
                            printString("Sei nella end-phase");
                            break;
                        case START_MOVEMENT_PHASE:
                            printString("Sei nella fase di movimento");
                            break;
                        case START_TIMER:
                            printString("Hai 2 minuti per concludere il turno");
                            break;
                        case START_TURN:
                            printString("Il tuo turno è iniziato - turno "+queue.getInteger());
                            break;
                        case USER_END_IS_GAME:
                            u=queue.getUser();
                            printString("Il giocatore "+u.getNickname()+" ha terminato la sua partita");
                            break;
                        case USER_END_TURN:
                            u=queue.getUser();
                            printString("Il giocatore "+u.getNickname()+" ha termianto il suo turno");
                            break;
                        case USER_START_TURN: 
                            u=queue.getUser();
                            printString("Il giocatore "+u.getNickname()+" ha iniziato il suo turno");
                            break;
                        case WHO_ARE_YOU:
                            Player p = queue.getPlayer();
                            printString(p.toString());
                            player=p;
                            printString("Il tuo personaggio è : "+p.getCharacterCard().toString());
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
                            printString("Mi dispiace ma hai perso :(");
                            break;
                        case YOU_WIN:
                            printString("Complimenti hai vinto :)");
                            break;
                        default:
                            printString(command.toString()+" comando non supportato");
                            break; 
                        }
                        queue.pollNotification(); //pop notification System.out.println(n.getCommand()  + " " + n.getArgument());
                    }
                }
            }).start();
        }
    }
    
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
    
    private boolean getInputAndSend(){
        String command=scanner.nextLine();
        if(command.equals("move")){
            System.out.println("Inserisci coordinata lettera: ");
            char letter=scanner.nextLine().charAt(0);
            System.out.println("Inserisci coordinata numero: ");
            int number=Integer.parseInt(scanner.nextLine());
            controller.sendCommand(new ClientCommand(CommandType.DO_MOVE_REQUEST,new Coordinate(letter,number)));
            return true;
        }
        if(command.equals("attack")){
            controller.sendCommand(new ClientCommand(CommandType.DO_ATTACK_REQUEST,null));
            return true;
        }
        if(command.equals("drawsector")){
            controller.sendCommand(new ClientCommand(CommandType.DO_DRAW_SECTOR_REQUEST,null));
            return true;
        }
        if(command.equals("end movement")){
            controller.sendCommand(new ClientCommand(CommandType.DO_END_MOVEMENT_PHASE_REQUEST,null));
            return true;
        }
        if(command.equals("end turn")){
            controller.sendCommand(new ClientCommand(CommandType.DO_END_TURN_REQUEST,null));
            return true;
        }
        if(command.equals("rumor")){
            System.out.println("Inserisci coordinata lettera: ");
            char letter=scanner.nextLine().charAt(0);
            System.out.println("Inserisci coordinata numero: ");
            int number=Integer.parseInt(scanner.nextLine());
            controller.sendCommand(new ClientCommand(CommandType.DO_RUMOR_IN_COORDINATE_REQUEST,new Coordinate(letter,number)));
            return true;
        }
        if(command.equals("item")){
            //stampa lista carte
            System.out.println("Inserisci id carta da giocare: ");
            int idCard=Integer.parseInt(scanner.nextLine());
            ItemCard card=player.getHand().getCard(idCard);
            if(card instanceof SpotlightItemCard){
                System.out.println("Inserisci coordinata lettera su cui far luce: ");
                char letter=this.scanner.nextLine().charAt(0);
                System.out.println("Inserisci coordinata numero su cui far luce: ");
                int number=Integer.parseInt(scanner.nextLine());
                ((SpotlightItemCard) card).setCoordToLight(new Coordinate(letter,number));
            }
            controller.sendCommand(new ClientCommand(CommandType.DO_PLAY_ITEM_REQUEST,card));
            return true;
        }
        if(command.equals("discard")){
            System.out.println("Inserisci id carta da scartare: ");
            int idCard=Integer.parseInt(scanner.nextLine());
            ItemCard card=player.getHand().getCard(idCard);
            controller.sendCommand(new ClientCommand(CommandType.DO_DISCARD_ITEM_REQUEST,card));
            return true;
        }
        printString("Comando non valido");
        return false;
    }
}
