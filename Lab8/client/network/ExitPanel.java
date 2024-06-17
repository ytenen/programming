package network;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class ExitPanel extends JFrame{
    private JTextField text;
    private JPanel exitPanel;

    public ExitPanel(Client client) {
        setContentPane(exitPanel);
        setTitle("Окончание работы");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550,400);
        setLocationRelativeTo(null);
        setVisible(true);
        text.setVisible(true);
        text.setEditable(false);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setVisible(false);
                RegistrationPanel registrationPanel = new RegistrationPanel(client);
            }
       };
        Timer timer = new Timer();
        timer.schedule(task,3000);
    }
}
