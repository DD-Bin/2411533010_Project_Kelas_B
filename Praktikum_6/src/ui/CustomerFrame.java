package ui;

import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import model.Customer;
import model.CustomerBuilder;
import table.TableCustomer;
import dao.CustomerRepo;

import java.awt.event.*;
import java.util.List;

public class CustomerFrame extends JFrame {

    private JPanel contentPane;
    private JTextField txtNama, txtEmail, txtAlamat, txtHp;
    private JTable table;
    private String selectedId = null;

    CustomerRepo repo = CustomerRepo.getInstance();

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerFrame frame = new CustomerFrame();
                frame.setVisible(true);
            } catch (Exception e) { e.printStackTrace(); }
        });
    }

    public CustomerFrame() {

        setTitle("");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 550, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNama = new JLabel("Nama:");
        lblNama.setBounds(30, 30, 80, 20);
        contentPane.add(lblNama);

        txtNama = new JTextField();
        txtNama.setBounds(120, 30, 300, 20);
        contentPane.add(txtNama);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(30, 60, 80, 20);
        contentPane.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(120, 60, 300, 20);
        contentPane.add(txtEmail);

        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setBounds(30, 90, 80, 20);
        contentPane.add(lblAlamat);

        txtAlamat = new JTextField();
        txtAlamat.setBounds(120, 90, 300, 20);
        contentPane.add(txtAlamat);

        JLabel lblHp = new JLabel("HP:");
        lblHp.setBounds(30, 120, 80, 20);
        contentPane.add(lblHp);

        txtHp = new JTextField();
        txtHp.setBounds(120, 120, 300, 20);
        contentPane.add(txtHp);

        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(e -> saveAction());
        btnSave.setBounds(30, 160, 80, 25);
        contentPane.add(btnSave);

        JButton btnUpdate = new JButton("UPDATE");
        btnUpdate.addActionListener(e -> updateAction());
        btnUpdate.setBounds(120, 160, 100, 25);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("DELETE");
        btnDelete.addActionListener(e -> deleteAction());
        btnDelete.setBounds(230, 160, 100, 25);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.addActionListener(e -> resetForm());
        btnCancel.setBounds(340, 160, 100, 25);
        contentPane.add(btnCancel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 200, 520, 350);
        contentPane.add(scrollPane);

        table = new JTable();
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                selectedId = table.getValueAt(row, 0).toString();
                txtNama.setText(table.getValueAt(row, 1).toString());
                txtEmail.setText(table.getValueAt(row, 2).toString());
                txtAlamat.setText(table.getValueAt(row, 3).toString());
                txtHp.setText(table.getValueAt(row, 4).toString());
            }
        });

        scrollPane.setViewportView(table);

        loadTable();
    }

    public void loadTable() {
        List<Customer> list = repo.show();
        table.setModel(new TableCustomer(list));
    }

    public void resetForm() {
        selectedId = null;
        txtNama.setText("");
        txtEmail.setText("");
        txtAlamat.setText("");
        txtHp.setText("");
        table.clearSelection();
    }

    public void saveAction() {
        Customer c = new CustomerBuilder()
                .setNama(txtNama.getText())
                .setEmail(txtEmail.getText())
                .setAlamat(txtAlamat.getText())
                .setHp(txtHp.getText())
                .build();

        repo.save(c);
        resetForm();
        loadTable();
    }

    public void updateAction() {
        if (selectedId == null) {
            JOptionPane.showMessageDialog(null, "Pilih data dulu!");
            return;
        }

        Customer c = new CustomerBuilder()
                .setId(selectedId)
                .setNama(txtNama.getText())
                .setEmail(txtEmail.getText())
                .setAlamat(txtAlamat.getText())
                .setHp(txtHp.getText())
                .build();

        repo.update(c);
        resetForm();
        loadTable();
    }

    public void deleteAction() {
        if (selectedId == null) {
            JOptionPane.showMessageDialog(null, "Pilih data dulu!");
            return;
        }

        repo.delete(selectedId);
        resetForm();
        loadTable();
    }
}
