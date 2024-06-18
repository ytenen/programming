package network;



import data.Organization;
import managers.ResourceBundleManager;
import system.TimeThread;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

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
    private JTextField userLogin;
    private JButton icon;
    private JTextField time;
    private String information;
    private User user;
    private Client client;
    private  int id;

    private Locale locale;
    public Commands (User user, Client client,Locale locale){
        this.client = client;
        this.user = user;
        this.locale = locale;
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        setContentPane(CommandsPanel);
        setTitle(resourceBundleManager.getString("commandList"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200,500);
        setLocationRelativeTo(null);
        setVisible(true);
        if (user.getLogin().equals("Сталин")){
            icon.setIcon(new ImageIcon("/Users/main/Documents/ITMO/programming/Lab8/client/images/stalin.jpg"));
        }
        TimeThread timeThread = new TimeThread(time);
        timeThread.startUpdating(locale);
        time.setEditable(false);
        userLogin.setEditable(false);
        userLogin.setText(resourceBundleManager.getString("tovarish") + " " + user.getLogin());
        result.setEditable(false);
        dopInfo.setEditable(true);
        add.setText(resourceBundleManager.getString("add"));
        addIfMax.setText(resourceBundleManager.getString("addIfMax"));
        clear.setText(resourceBundleManager.getString("clear"));
        containsName.setText(resourceBundleManager.getString("containsName"));
        executeScript.setText(resourceBundleManager.getString("execute_script"));
        dopInfo.setText(resourceBundleManager.getString("dopInfo"));
        help.setText(resourceBundleManager.getString("help"));
        info.setText(resourceBundleManager.getString("info"));
        remove.setText(resourceBundleManager.getString("remove"));
        removeFirst.setText(resourceBundleManager.getString("removeFirst"));
        removeLower.setText(resourceBundleManager.getString("removeLower"));
        addInfo.setText(resourceBundleManager.getString("addInfo"));
        show.setText(resourceBundleManager.getString("show"));
        startsFullName.setText(resourceBundleManager.getString("startFullName"));
        update.setText(resourceBundleManager.getString("update"));
        showOnField.setText(resourceBundleManager.getString("showOnField"));
        exit.setText(resourceBundleManager.getString("exit"));
        if (!user.getLogin().equals("Сталин")){
            toGulag.setVisible(false);
        }
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                ExitPanel exitPanel = new ExitPanel(client,locale,user);
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
                res+= '\n' + resourceBundleManager.getString("containsNameText") + '\n';
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
                res+= '\n' + resourceBundleManager.getString("removeText") + '\n';
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
                res+= '\n' + resourceBundleManager.getString("startsFullNameText") + '\n';
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
                OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"add",locale);
            }
        });
        addIfMax.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"add_if_max",locale);
            }
        });
        removeLower.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"remove_lower",locale);
            }
        });

        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = result.getText();
                res+= '\n' + resourceBundleManager.getString("updateText") + '\n';
                result.setText(res);
                addInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = dopInfo.getText();
                        try {
                            Commands.this.id = Integer.parseInt(information);
                            OrganizationPanel panel = new OrganizationPanel(client,user,Commands.this,"update",locale);
                            for (ActionListener listener : addInfo.getActionListeners()) {
                                addInfo.removeActionListener(listener);
                            }
                        } catch (Exception ex) {
                            String res = result.getText();
                            res+= '\n' + resourceBundleManager.getString("wrongIdFormat");
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
                ShowPanel showPanel = new ShowPanel(client,user,Commands.this,locale);
            }
        });
        showOnField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FieldPanel panel = new FieldPanel(client,user,Commands.this,locale);
            }
        });
        toGulag.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String res = result.getText();
                res+= '\n' + "Огласите имя предателя родины:" + '\n';
                result.setText(res);
                addInfo.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        information = dopInfo.getText();
                        try {
                            Response response = client.sendRequest(new Request(new String[]{"goToGulag",information},user));
                            String res = result.getText();
                            res+= '\n' + response.getResult();
                            result.setText(res);
                            if (response.getResult().equals("Национализация прошла успешно")){
                                BeriaPanel beriaPanel = new BeriaPanel(information,client,user);
                            }
                            for (ActionListener listener : addInfo.getActionListeners()) {
                                addInfo.removeActionListener(listener);
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                });
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
    public void updateOrganizationWithId(Organization organization,String command, String id){
        try {
            Response response = client.sendRequest(new Request(organization,new String[]{command,id},user));
            String res = result.getText();
            res+= '\n' + response.getResult();
            result.setText(res);
            Commands.this.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
