package network;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
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


    private Client client;
    public RegistrationPanel(Client client) {
        setContentPane(RegistrationPanel);
        this.client = client;
        setTitle("Электронное предъявление партийного билета");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(550,400);
        setLocationRelativeTo(null);
        setVisible(true);
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
                        Commands commands = new Commands(new User(newLogin,newPassword),client);
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
    }

}
