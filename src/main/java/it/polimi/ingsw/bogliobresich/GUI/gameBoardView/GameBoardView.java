package it.polimi.ingsw.bogliobresich.GUI.gameBoardView;
import it.polimi.ingsw.bogliobresich.GUI.GUIConstants;
import it.polimi.ingsw.bogliobresich.GUI.GUIController;
import it.polimi.ingsw.bogliobresich.GUI.ImagesHolder;
import it.polimi.ingsw.bogliobresich.GUI.View;
import it.polimi.ingsw.bogliobresich.model.map.ConstantMap;
import it.polimi.ingsw.bogliobresich.model.match.User;
import it.polimi.ingsw.bogliobresich.model.notifications.NotificationMessage;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalExclusionType;
import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.BevelBorder;


public class GameBoardView extends JFrame implements View {

    /**
     * 
     */
    private static final long serialVersionUID = 1974037371015620156L;
    private static final int BLINK_STEP = 500;
    private static final Color MESSAGES_AREA = new Color(0, 0, 0);
    private static final Color MESSAGES_AREA_TEXT = new Color(0, 102, 255);
    private ImagesHolder imagesHolder = ImagesHolder.getInstance();
    private GUIController guiController = GUIController.getInstance();
    
    private CommandPanel commandPanel;
    private JTextArea txtMessagesArea;
    private HexagonMapPanel map;

    protected Object messageMonitor = new Object();

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    GameBoardView window = new GameBoardView();
                    window.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public GameBoardView() {
        setResizable(false);
        initialize();
    }


    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        getContentPane().setBackground(Color.BLACK);
        setTitle(GUIConstants.APP_TITLE);
        setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
        setAlwaysOnTop(true);
        setBounds(100, 100, 1280, 730);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        String path = new String(ConstantMap.PATHFILEMAP + guiController.getMapFileName());
        map = new HexagonMapPanel(path,getCommandPanel());
        map.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        map.setBounds(60, 18, 676, 540);
        getContentPane().add(map);

        commandPanel = new CommandPanel(map);
        commandPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        commandPanel.setBounds(814, 6, 460, 686);
        commandPanel.setBackground(new Color(0, 0, 0));
        getContentPane().add(commandPanel);
        commandPanel.setLayout(null);


        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(60, 559, 676, 133);
        scrollPane.setAutoscrolls(true);
        getContentPane().add(scrollPane);

        txtMessagesArea = new JTextArea();
        txtMessagesArea.setDragEnabled(false);
        txtMessagesArea.setEditable(false);
        scrollPane.setViewportView(txtMessagesArea);
        txtMessagesArea.setBackground(MESSAGES_AREA);
        txtMessagesArea.setForeground(MESSAGES_AREA_TEXT);


        List <User> users = guiController.getUserList();
        commandPanel.initAndPrintUserList(guiController.getUserList(),guiController.getCommandPanelUserList());
        commandPanel.printPlayer(guiController.getMyPlayer());
        commandPanel.printCurrentTurnNumber(1);
        commandPanel.printMyCoordinate(guiController.getMyPlayer().getCoordinate());

        commandPanel.disableCommandPanel();
        cursorBlinkEffect();
    }

    public CommandPanel getCommandPanel() {
        return commandPanel;
    }

    public synchronized void printMessage(String msg) {
        synchronized(messageMonitor){
            txtMessagesArea.append("> " + msg + "\n");
        }
    }

    @Override
    public void initView() {
        try {
            this.setLocationRelativeTo(null);
            this.setVisible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doUpdate(NotificationMessage notification) {
        CommandHandler.dispatchUpdate(this, map ,notification);
    }

    public void cursorBlinkEffect() {
        new Thread(new Runnable()
        {
            @Override
            public void run() {
                try {
                    while(true)
                    {
                        synchronized(messageMonitor){
                            txtMessagesArea.append("_");
                            Thread.sleep(BLINK_STEP);

                            String appo = txtMessagesArea.getText().substring(0,txtMessagesArea.getText().length() - 1);
                            txtMessagesArea.setText(appo);
                        }
                        Thread.sleep(BLINK_STEP);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
