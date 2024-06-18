package network;

import managers.ResourceBundleManager;

import javax.swing.*;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class ExitPanel extends JFrame{
    private JButton text;
    private JPanel exitPanel;
    private Locale locale;

    public ExitPanel(Client client,Locale locale,User user) {
        this.locale = locale;
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        setContentPane(exitPanel);
        setTitle(resourceBundleManager.getString("endOfWork"));
        if (user.getLogin().equals("Сталин")){
            setSize(800,1000);
            text.setText("Спасибо за работу многоуважаемый, мудрый, добрый, великодушный, умный, незаменимый, дальновидный товарищ Сталин");
        }
        else{
            text.setText(resourceBundleManager.getString("thanksToTovarisch"));
            setSize(550,400);
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        text.setVisible(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                setVisible(false);
                RegistrationPanel registrationPanel = new RegistrationPanel(client,new Locale("ru","RU"));
            }
       };
        Timer timer = new Timer();
        timer.schedule(task,3000);
    }
}
