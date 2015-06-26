package it.polimi.ingsw.bogliobresich.GUI;

import it.polimi.ingsw.bogliobresich.communication.notification.NotificationMessage;

public interface View {

    public void initView();
    public void doUpdate(NotificationMessage notification);
    public void dispose();
    
}
