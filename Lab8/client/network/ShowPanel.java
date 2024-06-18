package network;

import data.Organization;
import data.comparators.*;
import managers.ResourceBundleManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.event.*;
import java.util.List;
import java.util.Locale;

public class ShowPanel extends JFrame{
    private JComboBox sortValue;
    private JButton sort;
    private JButton remove;
    private JButton back;
    private JTable table;
    private JPanel ShowPanel;
    private JScrollPane skroll;
    private JButton icon;
    private JTextField userLogin;
    private JButton update;
    private Response response;
    private List<Organization> collection;
    private Locale locale;
    public ShowPanel(Client client,User user,Commands commands,Locale locale){
        this.locale = locale;
        ResourceBundleManager resourceBundleManager = new ResourceBundleManager(locale);
        setContentPane(ShowPanel);
        setTitle(resourceBundleManager.getString("reestr"));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800,600);
        setLocationRelativeTo(null);
        userLogin.setEditable(false);
        userLogin.setText(resourceBundleManager.getString("tovarish") + " " + user.getLogin());
        setVisible(true);
        if (user.getLogin().equals("Сталин")){
            icon.setIcon(new ImageIcon("/Users/main/Documents/ITMO/programming/Lab8/client/images/stalin.jpg"));
        }
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn(resourceBundleManager.getString("id"));
        model.addColumn(resourceBundleManager.getString("name"));
        model.addColumn(resourceBundleManager.getString("coordX"));
        model.addColumn(resourceBundleManager.getString("coordY"));
        model.addColumn(resourceBundleManager.getString("date"));
        model.addColumn(resourceBundleManager.getString("annualTurnover"));
        model.addColumn(resourceBundleManager.getString("fullName"));
        model.addColumn(resourceBundleManager.getString("type"));
        model.addColumn(resourceBundleManager.getString("address"));
        sort.setText(resourceBundleManager.getString("sort"));
        back.setText(resourceBundleManager.getString("back"));
        remove.setText(resourceBundleManager.getString("remove"));
        update.setText(resourceBundleManager.getString("update"));
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

        sortValue.addItem(resourceBundleManager.getString("id"));
        sortValue.addItem(resourceBundleManager.getString("name"));
        sortValue.addItem(resourceBundleManager.getString("coordX"));
        sortValue.addItem(resourceBundleManager.getString("coordY"));
        sortValue.addItem(resourceBundleManager.getString("date"));
        sortValue.addItem(resourceBundleManager.getString("annualTurnover"));
        sortValue.addItem(resourceBundleManager.getString("fullName"));
        sortValue.addItem(resourceBundleManager.getString("type"));
        sortValue.addItem(resourceBundleManager.getString("address"));

        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String value = sortValue.getSelectedItem().toString();
                int flag = 1;
                if (value.equals(resourceBundleManager.getString("id"))){
                    IdComparator IC = new IdComparator();
                    collection = collection.stream().sorted(IC).toList();
                }else if(value.equals(resourceBundleManager.getString("name"))) {
                    NameComparator NC = new NameComparator();
                    collection = collection.stream().sorted(NC).toList();
                }else if(value.equals(resourceBundleManager.getString("coordX"))) {
                    CoordXComparator CXC = new CoordXComparator();
                    collection = collection.stream().sorted(CXC).toList();
                }else if (value.equals(resourceBundleManager.getString("coordY"))) {
                    CoordYComparator CYC = new CoordYComparator();
                    collection = collection.stream().sorted(CYC).toList();
                }else if (value.equals(resourceBundleManager.getString("date"))) {
                    DateComparator DC = new DateComparator();
                    collection = collection.stream().sorted(DC).toList();
                }else if (value.equals(resourceBundleManager.getString("annualTurnover"))) {
                    AnnualTurnoverComparator ATC = new AnnualTurnoverComparator();
                    collection = collection.stream().sorted(ATC).toList();
                }else if (value.equals(resourceBundleManager.getString("fullName"))) {
                    FullNameComparator FNC = new FullNameComparator();
                    collection = collection.stream().sorted(FNC).toList();
                }else if (value.equals(resourceBundleManager.getString("type"))) {
                    TypeComparator TC = new TypeComparator();
                    collection = collection.stream().sorted(TC).toList();
                }else if (value.equals(resourceBundleManager.getString("address"))) {
                    AddressComparator AC = new AddressComparator();
                    collection = collection.stream().sorted(AC).toList();
                }else {
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
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = table.getSelectedRow();
                if (selected!=-1){
                    UpdatePanel updatePanel = new UpdatePanel(client,user,locale,(String)table.getValueAt(selected,0),(String)table.getValueAt(selected,1),(String)table.getValueAt(selected,2),(String)table.getValueAt(selected,3),(String)table.getValueAt(selected,4),(String)table.getValueAt(selected,5),(String)table.getValueAt(selected,6),(String)table.getValueAt(selected,7),(String)table.getValueAt(selected,8),commands);
                }
            }
        });
    }
}
