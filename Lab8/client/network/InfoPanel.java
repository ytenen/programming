package network;

import managers.ResourceBundleManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class InfoPanel extends JFrame{
    private JPanel InfoPanel;
    private JTextArea text;
    private JButton back;
    private Locale locale;

    public InfoPanel(String info, Locale locale){
        this.locale = locale;
        setContentPane(InfoPanel);
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        setTitle(resourceBundleManager.getString("infoPanelTitle"));
        back.setText(resourceBundleManager.getString("back"));
        setDefaultCloseOperation(2);
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
