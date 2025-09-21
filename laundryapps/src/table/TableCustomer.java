package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Customer;

public class TableCustomer extends AbstractTableModel {

    private List<Customer> list;
    private String[] columnNames = {"ID", "Nama", "Alamat", "No. HP"};

    public TableCustomer(List<Customer> list) {
        this.list = list;
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Customer customer = list.get(rowIndex);
        switch(columnIndex) {
            case 0: return customer.getId();
            case 1: return customer.getNama();
            case 2: return customer.getAlamat();
            case 3: return customer.getNomorhp();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }
}