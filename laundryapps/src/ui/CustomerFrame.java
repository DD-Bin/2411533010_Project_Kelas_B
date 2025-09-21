package ui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import DAO.CustomerRepo;
import model.Customer;
import table.TableCustomer;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class CustomerFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtNama;
    private JTextField txtAlamat;
    private JTextField txtNomorhp;
    private JTable tableCustomers;

    CustomerRepo customerRepo = new CustomerRepo();
    List<Customer> ls;
    public String id;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    CustomerFrame frame = new CustomerFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reset() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNomorhp.setText("");
        id = null;
    }

    public void loadTable() {
        ls = customerRepo.show();
        TableCustomer tc = new TableCustomer(ls);
        tableCustomers.setModel(tc);
        tableCustomers.getTableHeader().setVisible(true);
    }

    public CustomerFrame() {
        setTitle("Customer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ⬅ jangan EXIT_ON_CLOSE
        setBounds(100, 100, 500, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtNama = new JTextField();
        txtNama.setBounds(121, 39, 300, 19);
        contentPane.add(txtNama);
        txtNama.setColumns(10);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(121, 68, 300, 19);
        contentPane.add(txtAlamat);
        txtAlamat.setColumns(10);

        txtNomorhp = new JTextField();
        txtNomorhp.setBounds(121, 97, 300, 19);
        contentPane.add(txtNomorhp);
        txtNomorhp.setColumns(10);

        JLabel lblNama = new JLabel("Nama");
        lblNama.setBounds(48, 42, 63, 13);
        contentPane.add(lblNama);

        JLabel lblAlamat = new JLabel("Alamat");
        lblAlamat.setBounds(48, 71, 63, 13);
        contentPane.add(lblAlamat);

        JLabel lblNomorhp = new JLabel("Nomor HP");
        lblNomorhp.setBounds(48, 100, 63, 13);
        contentPane.add(lblNomorhp);

        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Customer c = new Customer();
                c.setNama(txtNama.getText());
                c.setAlamat(txtAlamat.getText());
                c.setNomorhp(txtNomorhp.getText());
                customerRepo.save(c);
                reset();
                loadTable();
            }
        });
        btnSave.setFont(new Font("Times New Roman", Font.BOLD, 10));
        btnSave.setBackground(new Color(0, 255, 0));
        btnSave.setBounds(34, 145, 88, 21);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 10));
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    Customer c = new Customer();
                    c.setId(id);
                    c.setNama(txtNama.getText());
                    c.setAlamat(txtAlamat.getText());
                    c.setNomorhp(txtNomorhp.getText());
                    customerRepo.update(c);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan diupdate");
                }
            }
        });
        btnUpdate.setBackground(new Color(0, 255, 255));
        btnUpdate.setBounds(132, 145, 88, 21);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (id != null) {
                    customerRepo.delete(id);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan dihapus");
                }
            }
        });
        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 10));
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setBounds(230, 145, 88, 21);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
                tableCustomers.clearSelection();
            }
        });
        btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 10));
        btnCancel.setBackground(new Color(255, 255, 0));
        btnCancel.setBounds(328, 145, 88, 21);
        contentPane.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 190, 466, 350);
        contentPane.add(scrollPane);

        tableCustomers = new JTable();
        tableCustomers.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 0).toString();
                txtNama.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 1).toString());
                txtAlamat.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 2).toString());
                txtNomorhp.setText(tableCustomers.getValueAt(tableCustomers.getSelectedRow(), 3).toString());
            }
        });
        scrollPane.setViewportView(tableCustomers);
        loadTable();
    }
}