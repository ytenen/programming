package network;

import data.Organization;
import data.comparators.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ShowPanel extends JFrame{
    private JComboBox sortValue;
    private JButton sort;
    private JButton remove;
    private JButton back;
    private JTable table;
    private JPanel ShowPanel;
    private JScrollPane skroll;
    private Response response;
    private List<Organization> collection;

    public ShowPanel(Client client,User user,Commands commands){
        setContentPane(ShowPanel);
        setTitle("Реестр");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        setVisible(true);
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Идентификатор");
        model.addColumn("Название");
        model.addColumn("Координата Х");
        model.addColumn("Координата Y");
        model.addColumn("Дата учреждения");
        model.addColumn("Годовой оборот");
        model.addColumn("Полное наименование");
        model.addColumn("Тип");
        model.addColumn("Адрес");
        try {
            response = client.sendRequest(new Request(new String[]{"show"},user));
            collection = response.getCollection();
            if (collection!=null){
                for (Organization organization : collection){
                    model.addRow(new String[]{Integer.toString(organization.getId()),organization.getName(),Double.toString(organization.getCoordinates().getX()),Integer.toString(organization.getCoordinates().getY()),organization.getCreationDate().toString(), Integer.toString(organization.getAnnualTurnover()),organization.getFullName(),organization.getType().toString(),organization.getOfficialAddress().getZipCode()});
                }
            }
            table.setModel(model);
            javax.swing.table.JTableHeader header = table.getTableHeader();
            // Set the column names
            TableColumn column;
            for (int i = 0; i < table.getColumnCount(); i++) {
                column = table.getColumnModel().getColumn(i);
                column.setHeaderValue(model.getColumnName(i));
            }
            table.setVisible(true);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                commands.setVisible(true);
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[] rows = table.getSelectedRows();
                for (int i : rows){
                    try {
                        client.sendRequest(new Request(new String[]{"remove_by_id",model.getValueAt(i,0).toString()},user));
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
                try{
                    response = client.sendRequest(new Request(new String[]{"show"},user));
                    collection = response.getCollection();
                    model.setRowCount(0);
                    if (collection!=null){
                        for (Organization organization : collection){
                            model.addRow(new String[]{Integer.toString(organization.getId()),organization.getName(),Double.toString(organization.getCoordinates().getX()),Integer.toString(organization.getCoordinates().getY()),organization.getCreationDate().toString(), Integer.toString(organization.getAnnualTurnover()),organization.getFullName(),organization.getType().toString(),organization.getOfficialAddress().getZipCode()});
                        }
                    }
                    table.setModel(model);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }

            }
        });

        sortValue.addItem("Идентификатор");
        sortValue.addItem("Название");
        sortValue.addItem("Координата X");
        sortValue.addItem("Координата Y");
        sortValue.addItem("Дата учреждения");
        sortValue.addItem("Годовой оборот");
        sortValue.addItem("Полное наименование");
        sortValue.addItem("Тип");
        sortValue.addItem("Адрес");

        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = sortValue.getSelectedItem().toString();
                int flag = 1;
                switch (value){
                    case "Идентификатор":
                        IdComparator IC = new IdComparator();
                        collection = collection.stream().sorted(IC).toList();
                        break;
                    case "Название":
                        NameComparator NC = new NameComparator();
                        collection = collection.stream().sorted(NC).toList();
                        break;
                    case "Координата X":
                        CoordXComparator CXC = new CoordXComparator();
                        collection = collection.stream().sorted(CXC).toList();
                        break;
                    case "Координата Y":
                        CoordYComparator CYC = new CoordYComparator();
                        collection = collection.stream().sorted(CYC).toList();
                        break;
                    case "Дата учреждения":
                        DateComparator DC = new DateComparator();
                        collection = collection.stream().sorted(DC).toList();
                        break;
                    case "Годовой оборот":
                        AnnualTurnoverComparator ATC = new AnnualTurnoverComparator();
                        collection = collection.stream().sorted(ATC).toList();
                        break;
                    case "Полное наименование":
                        FullNameComparator FNC = new FullNameComparator();
                        collection = collection.stream().sorted(FNC).toList();
                        break;
                    case "Тип":
                        TypeComparator TC = new TypeComparator();
                        collection = collection.stream().sorted(TC).toList();
                        break;
                    case"Адрес":
                        AddressComparator AC = new AddressComparator();
                        collection = collection.stream().sorted(AC).toList();
                        break;
                    default:
                        flag = 0;
                }
                if (flag==1){
                    model.setRowCount(0);
                    if (collection!=null){
                        for (Organization organization : collection){
                            model.addRow(new String[]{Integer.toString(organization.getId()),organization.getName(),Double.toString(organization.getCoordinates().getX()),Integer.toString(organization.getCoordinates().getY()),organization.getCreationDate().toString(), Integer.toString(organization.getAnnualTurnover()),organization.getFullName(),organization.getType().toString(),organization.getOfficialAddress().getZipCode()});
                        }
                    }
                    table.setModel(model);
                }
            }
        });
    }
}
