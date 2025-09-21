package table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.Service;

public class TableService extends AbstractTableModel {
    private List<Service> list;
    private String[] columnNames = {"ID", "Jenis", "Harga", "Status"};

    public TableService(List<Service> list) {
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
        Service s = list.get(rowIndex);
        switch (columnIndex) {
            case 0: return s.getId();
            case 1: return s.getJenis();
            case 2: return s.getHarga();
            case 3: return s.getStatus();
            default: return null;
        }
    }
}