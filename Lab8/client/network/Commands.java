package network;



import data.Organization;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Commands extends JFrame{
    private JPanel CommandsPanel;
    private JButton add;
    private JButton addIfMax;
    private JButton clear;
    private JButton containsName;
    private JButton executeScript;
    private JButton help;
    private JButton info;
    private JButton remove;
    private JButton removeFirst;
    private JButton removeLower;
    private JButton show;
    private JButton startsFullName;
    private JButton update;
    private JButton exit;
    private JTextArea result;
    private JButton toGulag;
    private JTextField dopInfo;
    private JButton addInfo;
    private JButton showOnField;
    private String information;
    private User user;
    private Client client;
    private  int id;

    public Commands (User user, Client client){
        this.client = client;
        this.user = user;
        setContentPane(CommandsPanel);
        setTitle("Список команд");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,500);
        setLocationRelativeTo(null);
        setVisible(true);
        result.setEditable(false);
        dopInfo.setEditable(true);
        if (!user.getLogin().equals("Сталин")){
            toGulag.setVisible(false);
        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ExitPanel exitPanel = new ExitPanel(client);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Response response = client.sendRequest(new Request(new String[]{"clear"},user));
                    String res = result.getText();
                    res+= '\n' + response.getResult();
                    result.setText(res);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        help.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Response response = client.sendRequest(new Request(new String[]{"help"},user));
                    String res = result.getText();
                    res+= '\n' + response.getResult();
                    result.setText(res);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Response response = client.sendRequest(new Request(new String[]{"info"},user));
                    String res = result.getText();
                    res+= '\n' + response.getResult();
                    result.setText(res);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        removeFirst.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Response response = client.sendRequest(new Request(new String[]{"remove_first"},user));
                    String res = result.getText();
                    res+= '\n' + response.getResult();
                    result.setText(res);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        containsName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = result.getText();
                res+= '\n' + "Введите сочетание букв, которое должно содержать название организации в поле дополнительной информации и нажмите добавить:" + '\n';
                result.setText(res);
                addInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = dopInfo.getText();
                        try {
                            Response response = client.sendRequest(new Request(new String[]{"filter_contains_name",information},user));
                            String res = result.getText();
                            res+= '\n' + response.getResult();
                            result.setText(res);
                            for (ActionListener listener : addInfo.getActionListeners()) {
                                addInfo.removeActionListener(listener);
                            }
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = result.getText();
                res+= '\n' + "Введите идентификатор организации, которую следует упразднить в поле дополнительной информации и нажмите добавить:" + '\n';
                result.setText(res);
                addInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = dopInfo.getText();
                        try {
                            Response response = client.sendRequest(new Request(new String[]{"remove_by_id",information},user));
                            String res = result.getText();
                            res+= '\n' + response.getResult();
                            result.setText(res);
                            for (ActionListener listener : addInfo.getActionListeners()) {
                                addInfo.removeActionListener(listener);
                            }
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        startsFullName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = result.getText();
                res+= '\n' + "Введите сочетание букв,с которого должно начинаться полное название организации в поле дополнительной информации и нажмите добавить:" + '\n';
                result.setText(res);
                addInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = dopInfo.getText();
                        try {
                            Response response = client.sendRequest(new Request(new String[]{"filter_starts_with_full_name",information},user));
                            String res = result.getText();
                            res+= '\n' + response.getResult();
                            result.setText(res);
                            for (ActionListener listener : addInfo.getActionListeners()) {
                                addInfo.removeActionListener(listener);
                            }
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"add");
            }
        });
        addIfMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"add_if_max");
            }
        });
        removeLower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"remove_lower");
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = result.getText();
                res+= '\n' + "Введите идентификатор организации, информацию о которой нужно обновить в поле дополнительной информации и нажмите добавить:" + '\n';
                result.setText(res);
                addInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = dopInfo.getText();
                        try {
                            Commands.this.id = Integer.parseInt(information);
                            setVisible(false);
                            OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"update");
                            for (ActionListener listener : addInfo.getActionListeners()) {
                                addInfo.removeActionListener(listener);
                            }
                        } catch (Exception ex) {
                            String res = result.getText();
                            res+= '\n' + "Неверный формат идентификатора";
                            result.setText(res);
                            ex.printStackTrace();
                        }
                    }
                });

            }
        });
        show.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ShowPanel showPanel = new ShowPanel(client,user,Commands.this);
            }
        });
        showOnField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FieldPanel panel = new FieldPanel(client,user);
            }
        });
    }

    public void addOrganization(Organization organization,String command){
        try {
            Response response = client.sendRequest(new Request(organization,new String[]{command},user));
            String res = result.getText();
            res+= '\n' + response.getResult();
            result.setText(res);
            Commands.this.setVisible(true);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void updateOrganization(Organization organization,String command){
        try {
            Response response = client.sendRequest(new Request(organization,new String[]{command,Integer.toString(id)},user));
            String res = result.getText();
            res+= '\n' + response.getResult();
            result.setText(res);
            Commands.this.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
