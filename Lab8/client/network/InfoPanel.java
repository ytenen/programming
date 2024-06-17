package network;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InfoPanel extends JFrame{
    private JPanel InfoPanel;
    private JTextArea text;
    private JButton back;

    public InfoPanel(String info){
        setContentPane(InfoPanel);
        setTitle("Информация о выбранной организации");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300,300);
        setLocationRelativeTo(null);
        setVisible(true);
        text.setEditable(false);
        text.setText(info);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
    }
}
