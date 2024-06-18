package network;

import managers.ResourceBundleManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BeriaPanel extends JFrame{
    private JButton beat;
    private JPanel BeriaPanel;
    private JButton guilty;
    private JTextField beria;
    private JButton icon;

    public BeriaPanel(String login,Client client,User user){
        setContentPane(BeriaPanel);
        setDefaultCloseOperation(2);
        setSize(800,400);
        setLocationRelativeTo(null);
        setTitle("Чистка Берии");
        setVisible(true);
        beat.setText("Осудить товарища " + login);

        guilty.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        beat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    client.sendRequest(new Request(new String[]{"deleteUser",login},user));
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }
}
