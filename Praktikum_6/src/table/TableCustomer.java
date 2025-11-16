package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Customer;

public class TableCustomer extends AbstractTableModel {

    List<Customer> list;
    private String[] columnNames = {"ID", "Nama", "Email", "Alamat", "HP"};

    public TableCustomer(List<Customer> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public Object getValueAt(int row, int col) {
        Customer c = list.get(row);
        switch (col) {
            case 0: return c.getId();
            case 1: return c.getNama();
            case 2: return c.getEmail();
            case 3: return c.getAlamat();
            case 4: return c.getHp();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}
