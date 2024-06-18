package network;

import data.Organization;
import data.OrganizationType;
import managers.ResourceBundleManager;
import managers.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class OrganizationPanel extends JFrame{
    private JPanel OrganizationPanel;
    private JTextField nameInfo;
    private JTextField coordXInfo;
    private JTextField annualTurnoverInfo;
    private JTextField fullNameInfo;
    private JTextField typeInfo;
    private JTextField addressInfo;
    private JTextField nameFill;
    private JTextField coordXFill;
    private JTextField annualTurnoverFill;
    private JTextField fullNameFill;
    private JComboBox typeFill;
    private JTextField addressFill;
    private JButton add;
    private JButton clear;
    private JTextField info;
    private JTextField error;
    private JTextField coordYInfo;
    private JTextField coordYFill;
    private  Locale locale;

    public OrganizationPanel(Client client, User user, Commands commands, String command, Locale locale){
        this.locale = locale;
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        setContentPane(OrganizationPanel);
        setTitle(resourceBundleManager.getString("organizationPanelTitle"));
        setDefaultCloseOperation(2);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        error.setEditable(false);
        clear.setText(resourceBundleManager.getString("organizationPanelClear"));
        add.setText(resourceBundleManager.getString("organizationPanelAdd"));
        info.setText(resourceBundleManager.getString("organizationPanelInfo"));
        info.setEditable(false);
        nameInfo.setText(resourceBundleManager.getString("organizationPanelName"));
        nameInfo.setEditable(false);
        coordXInfo.setText(resourceBundleManager.getString("organizationPanelCoordX"));
        coordXInfo.setEditable(false);
        coordYInfo.setText(resourceBundleManager.getString("organizationPanelCoordY"));
        coordYInfo.setEditable(false);
        annualTurnoverInfo.setText(resourceBundleManager.getString("organizationPanelAnnualTurnover"));
        annualTurnoverInfo.setEditable(false);
        fullNameInfo.setText(resourceBundleManager.getString("organizationPanelFullName"));
        fullNameInfo.setEditable(false);
        typeInfo.setText(resourceBundleManager.getString("organizationPanelType"));
        typeInfo.setEditable(false);
        addressInfo.setText(resourceBundleManager.getString("organizationPanelAddress"));
        addressInfo.setEditable(false);
        typeFill.addItem(OrganizationType.COMMERCIAL.toString());
        typeFill.addItem(OrganizationType.PUBLIC.toString());
        typeFill.addItem(OrganizationType.OPEN_JOINT_STOCK_COMPANY.toString());
        typeFill.addItem(OrganizationType.PRIVATE_LIMITED_COMPANY.toString());
        typeFill.setVisible(true);


        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nameFill.setText("");
                coordXFill.setText("");
                coordYFill.setText("");
                annualTurnoverFill.setText("");
                fullNameFill.setText("");
                typeFill.setSelectedIndex(-1);
                addressFill.setText("");
            }
        });
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Validator.inputIsNotEmpty(nameFill.getText(),"название");
                    Validator.coordinateXIsOk(coordXFill.getText());
                    Validator.coordinateYIsOk(coordYFill.getText());
                    Validator.annualTurnoverIsOk(annualTurnoverFill.getText());
                    Validator.inputIsNotEmpty(fullNameFill.getText(),"полное название");
                    Validator.inputIsNotEmpty(addressFill.getText(),"адрес");
                    Organization organization = new Organization(nameFill.getText(),Double.parseDouble(coordXFill.getText()),Integer.parseInt(coordYFill.getText()),Integer.parseInt(annualTurnoverFill.getText()),OrganizationType.valueOf(typeFill.getSelectedItem().toString()),fullNameFill.getText(),addressFill.getText());
                    if (!command.equals("update")){
                        commands.addOrganization(organization,command);
                    }else {
                        commands.updateOrganization(organization,command);
                    }
                    setVisible(false);
                }catch (Exception ex){
                    error.setText(ex.getMessage());
                }
            }
        });
    }


}
