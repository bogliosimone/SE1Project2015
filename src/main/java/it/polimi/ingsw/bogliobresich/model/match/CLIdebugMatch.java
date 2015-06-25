/**
 * 
 */
package it.polimi.ingsw.bogliobresich.model.match;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.polimi.ingsw.bogliobresich.model.cards.ItemCard;
import it.polimi.ingsw.bogliobresich.model.cards.SpotlightItemCard;
import it.polimi.ingsw.bogliobresich.model.map.Coordinate;
import it.polimi.ingsw.bogliobresich.model.match.action.AddPlayerAction;
import it.polimi.ingsw.bogliobresich.model.match.action.AttackAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DiscardAction;
import it.polimi.ingsw.bogliobresich.model.match.action.DrawSectorAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndPhaseAction;
import it.polimi.ingsw.bogliobresich.model.match.action.EndTurnAction;
import it.polimi.ingsw.bogliobresich.model.match.action.MovementAction;
import it.polimi.ingsw.bogliobresich.model.match.action.PlayItemAction;
import it.polimi.ingsw.bogliobresich.model.match.action.RumorCoordinate;

/**
 * @author simoneboglio
 *
 */
public class CLIdebugMatch {
private boolean end=false;
//private boolean start=false;
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
Match match = new Match(1,null);
    
    public static void main(String[] args) throws IOException {
        CLIdebugMatch cli=new CLIdebugMatch();
        cli.match.setIsCLIenable(true);
        System.out.println("Inserisci comando: ");
        while(!cli.end){
            cli.action(cli.br.readLine());
            if(!cli.end)
                System.out.println("Inserisci comando: ");
        }
        cli.br.close();     
    }

    
    private void action (String command) throws IOException{
        /*if(command.equals("start")){
            this.match = new Match(null);
            return;
        }
        if(start==false){
            System.out.println("Devi far iniziare la partita");
            return;
        }*/
        if(command.equals("ap")){
            System.out.println("Inserisci id: ");
            int id=Integer.parseInt(this.br.readLine());
            System.out.println("Inserisci nickname: ");
            String nickname = this.br.readLine();
            System.out.println("Inserisci password: ");
            String password = this.br.readLine();
            match.doAction(this.match.getCurrentPlayer(), new AddPlayerAction(new User(id,nickname,password)));
            return;
        }
        if(command.equals("m")){
            System.out.println("Inserisci coordinata lettera: ");
            char letter=this.br.readLine().charAt(0);
            System.out.println("Inserisci coordinata numero: ");
            int number=Integer.parseInt(this.br.readLine());
            match.doAction(match.getCurrentPlayer(), new MovementAction(new Coordinate(letter,number)));
            return;
        }
        if(command.equals("a")){
            match.doAction(match.getCurrentPlayer(), new AttackAction());
            return;
        }
        if(command.equals("ds")){
            match.doAction(match.getCurrentPlayer(), new DrawSectorAction());
            return;
        }
        if(command.equals("em")){
            match.doAction(match.getCurrentPlayer(), new EndPhaseAction());
            return;
        }
        if(command.equals("et")){
            match.doAction(match.getCurrentPlayer(), new EndTurnAction());
            return;
        }
        if(command.equals("rc")){
            System.out.println("Inserisci coordinata lettera: ");
            char letter=this.br.readLine().charAt(0);
            System.out.println("Inserisci coordinata numero: ");
            int number=Integer.parseInt(this.br.readLine());
            match.doAction(match.getCurrentPlayer(), new RumorCoordinate(new Coordinate(letter,number)));
            return;
        }
        if(command.equals("i")){
            System.out.println("Inserisci id carta da giocare: ");
            int idCard=Integer.parseInt(this.br.readLine());
            ItemCard card=this.match.getCurrentPlayer().getHand().getCard(idCard);
            if(card instanceof SpotlightItemCard){
                System.out.println("Inserisci coordinata lettera su cui far luce: ");
                char letter=this.br.readLine().charAt(0);
                System.out.println("Inserisci coordinata numero su cui far luce: ");
                int number=Integer.parseInt(this.br.readLine());
                ((SpotlightItemCard) card).setCoordToLight(new Coordinate(letter,number));
            }
            match.doAction(match.getCurrentPlayer(), new PlayItemAction(card));
            return;
        }
        if(command.equals("di")){
            System.out.println("Inserisci id carta da scartare: ");
            int idCard=Integer.parseInt(this.br.readLine());
            ItemCard card=this.match.getCurrentPlayer().getHand().getCard(idCard);
            match.doAction(match.getCurrentPlayer(), new DiscardAction(card));
            return;
        }
        if(command.equals("exit")){
            this.end=true;
            System.out.println("Fine del gioco");
            return;
        }
        
        System.out.println("Comando non valido");
        return;
    }
    
}
