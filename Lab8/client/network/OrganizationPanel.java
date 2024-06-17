package network;

import data.Organization;
import data.OrganizationType;
import managers.Validator;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public OrganizationPanel(Client client,User user,Commands commands,String command){
        setContentPane(OrganizationPanel);
        setTitle("Внесение организации в реестр");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        error.setEditable(false);
        info.setText("Введите параметры организации и добавьте ее в реестр");
        info.setEditable(false);
        nameInfo.setText("Введите название:");
        nameInfo.setEditable(false);
        coordXInfo.setText("Введите координаты на карте по оси х(допустимо дробное значение):");
        coordXInfo.setEditable(false);
        coordYInfo.setText("Введите координаты на карте по оси y(только целое значение):");
        coordYInfo.setEditable(false);
        annualTurnoverInfo.setText("Введите плановый годовой оборот(округлить до целого):");
        annualTurnoverInfo.setEditable(false);
        fullNameInfo.setText("Введите полное название организации:");
        fullNameInfo.setEditable(false);
        typeInfo.setText("Выберете официальный тип организации:");
        typeInfo.setEditable(false);
        addressInfo.setText("Введите адрес организации:");
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
