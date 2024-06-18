package network;

import managers.ResourceBundleManager;
import system.TimeThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Objects;

import static java.lang.System.exit;

public class RegistrationPanel extends JFrame{
    private JTextField login;
    private JPanel RegistrationPanel;
    private JPasswordField password;
    private JButton registration;
    private JButton logIn;
    private JTextField result;
    private JButton exit;
    private JButton sssr;
    private JButton norway;
    private JButton ukr;
    private JButton dom;
    private JTextField time;
    private Locale locale;

    private Client client;
    public RegistrationPanel(Client client,Locale locale) {
        this.locale = locale;
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        setContentPane(RegistrationPanel);
        this.client = client;
        setTitle(resourceBundleManager.getString("predyav"));
        login.setText(resourceBundleManager.getString("userName"));
        logIn.setText(resourceBundleManager.getString("login"));
        registration.setText(resourceBundleManager.getString("register"));
        exit.setText(resourceBundleManager.getString("exit"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000,400);
        setLocationRelativeTo(null);
        setVisible(true);
        time.setEditable(false);
        TimeThread timeThread = new TimeThread(time);
        timeThread.startUpdating(locale);
        result.setVisible(false);
        result.setEditable(false);

        registration.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = password.getText();
                String newLogin = login.getText();
                try {
                    Response response = client.sendRequest(new Request(new String[]{"register",newLogin,newPassword},new User(newLogin,newPassword)));
                    result.setText(response.getResult());
                    result.setVisible(true);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        logIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newPassword = password.getText();
                String newLogin = login.getText();
                try{
                    Response response = client.sendRequest(new Request(new String[]{"authorization"},new User(newLogin,newPassword)));
                    String responseResult = response.getResult();
                    if (Objects.equals(responseResult, "This login not exist")){
                        result.setText(responseResult);
                        result.setVisible(true);
                    }else {
                        setVisible(false);
                        Commands commands = new Commands(new User(newLogin,newPassword),client,locale);
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                exit(0);
            }
        });
        sssr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                network.RegistrationPanel panel = new RegistrationPanel(client,new Locale("ru","RU"));
            }
        });
        norway.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                network.RegistrationPanel panel = new RegistrationPanel(client,new Locale("no","NO"));
            }
        });
        ukr.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                network.RegistrationPanel panel = new RegistrationPanel(client,new Locale("uk","UA"));
            }
        });
        dom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                network.RegistrationPanel panel = new RegistrationPanel(client,new Locale("es","DO"));
            }
        });
    }

}
