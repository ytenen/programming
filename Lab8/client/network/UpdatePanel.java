package network;

import data.Organization;
import data.OrganizationType;
import managers.ResourceBundleManager;
import managers.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;

public class UpdatePanel extends JFrame{
    private JTextField nameFill;
    private JTextField nameInfo;
    private JTextField coordXFill;
    private JTextField coordXInfo;
    private JTextField coordYFill;
    private JTextField coordYInfo;
    private JTextField annualTurnoverFill;
    private JTextField annualTurnoverInfo;
    private JTextField fullNameFill;
    private JTextField fullNameInfo;
    private JComboBox typeFill;
    private JTextField typeInfo;
    private JTextField addressFill;
    private JTextField addressInfo;
    private JTextField error;
    private JButton update;
    private JButton clear;
    private JTextField updateInfo;
    private JPanel updatePanel;

    public UpdatePanel(Client client, User user, Locale locale, String id, String name, String x, String y, String date, String annualTurnover, String fullName, String type, String address,Commands commands){
        setContentPane(updatePanel);
        setDefaultCloseOperation(2);
        setSize(800,400);
        setLocationRelativeTo(null);
        setTitle("Обновить информацию");
        setVisible(true);
        error.setEditable(false);
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        clear.setText(resourceBundleManager.getString("organizationPanelClear"));
        update.setText(resourceBundleManager.getString("update"));
        updateInfo.setText(resourceBundleManager.getString("update"));
        updateInfo.setEditable(false);
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
        nameFill.setText(name);
        coordXFill.setText(x);
        coordYFill.setText(y);
        annualTurnoverFill.setText(annualTurnover);
        fullNameFill.setText(fullName);
        addressFill.setText(address);

        update.addActionListener(new ActionListener() {
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
                    commands.updateOrganizationWithId(organization,"update",id);
                    setVisible(false);
                }catch (Exception ex){
                    error.setText(ex.getMessage());
                }
            }
        });
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
    }
}
