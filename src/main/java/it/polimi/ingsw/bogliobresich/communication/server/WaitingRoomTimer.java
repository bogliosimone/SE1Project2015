/**
 * 
 */
package it.polimi.ingsw.bogliobresich.communication.server;

import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * @author matteobresich
 *
 */
public class WaitingRoomTimer extends TimerTask {
    
    WaitingRoom timerOwner = null;
    
    public WaitingRoomTimer (WaitingRoom timerOwner) {
        setWaitingRoom(timerOwner);
    }

    @Override
    public void run() {
        if(timerOwner != null) {
            Date d1 = new Date();
            System.out.println("Timer task started at:"+d1);
            timeToCompleteTask();
            Date d2 = new Date();
            long diff = d2.getTime() - d1.getTime();
            long seconds = TimeUnit.MILLISECONDS.toSeconds(diff);
            System.out.println("Timer task finished at:"+d2 + " dopo: " + seconds + "s");
            timerOwner.initMatch();
        }
    }
    
    private void setWaitingRoom (WaitingRoom timerOwner) {
        this.timerOwner = timerOwner;
    }
    
    private void timeToCompleteTask() {
        try {
            //assuming it takes 10 secs to complete the task
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
