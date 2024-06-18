package network;

import data.Organization;
import managers.ResourceBundleManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Locale;

public class FieldPanel extends JFrame {
    private Client client;
    private JPanel FieldPanel;
    private User user;
    private List<Organization> collection;
    private Locale locale;

    public FieldPanel(Client client, User user, Commands commands,Locale locale) {
        this.locale = locale;
        this.client = client;
        this.user = user;
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        JPanel panel = new JPanel(); // Создаем панель
        panel.setLayout(null); // Установка layout для добавления кнопок с координатами
        add(panel); // Добавляем панель на фрейм
        setTitle(resourceBundleManager.getString("fieldPanelTitle"));
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setVisible(true);


        try {
            Response response = client.sendRequest(new Request(new String[]{"show"}, user));
            collection = response.getCollection();
            for (Organization organization : collection) {
                JButton button = new JButton();
                button.setSize(50,50);
                button.setName(organization.getName());
                button.setText(organization.getName());
                button.setIcon(new ImageIcon("/Users/main/Documents/ITMO/programming/Lab8/client/images/star.png"));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        InfoPanel panel = new InfoPanel(organization.toInfo(),locale);
                    }
                });
                button.setBounds((int) (organization.getCoordinates().getX() * 2.6), (int) (organization.getCoordinates().getY() * 2.6), 100, 30); // Установка координат и размера кнопки
                panel.add(button); // Добавляем кнопку на панель
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}