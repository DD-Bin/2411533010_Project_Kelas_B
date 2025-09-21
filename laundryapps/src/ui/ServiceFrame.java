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

import DAO.ServiceRepo;
import model.Service;
import table.TableService;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class ServiceFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField txtJenis;
    private JTextField txtHarga;
    private JTextField txtStatus;
    private JTable tableServices;

    ServiceRepo serviceRepo = new ServiceRepo();
    List<Service> ls;
    public String id;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ServiceFrame frame = new ServiceFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void reset() {
        txtJenis.setText("");
        txtHarga.setText("");
        txtStatus.setText("");
    }

    public void loadTable() {
        ls = serviceRepo.show();
        TableService ts = new TableService(ls);
        tableServices.setModel(ts);
        tableServices.getTableHeader().setVisible(true);
    }

    /**
     * Create the frame.
     */
    public ServiceFrame() {
        setTitle("Layanan");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // ⬅ supaya MainFrame tidak ikut tertutup
        setBounds(100, 100, 450, 580);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        txtJenis = new JTextField();
        txtJenis.setBounds(121, 39, 270, 19);
        contentPane.add(txtJenis);
        txtJenis.setColumns(10);

        txtHarga = new JTextField();
        txtHarga.setBounds(121, 68, 270, 19);
        contentPane.add(txtHarga);
        txtHarga.setColumns(10);

        txtStatus = new JTextField();
        txtStatus.setBounds(121, 97, 270, 19);
        contentPane.add(txtStatus);
        txtStatus.setColumns(10);

        JLabel lblNewLabel = new JLabel("Jenis");
        lblNewLabel.setBounds(48, 42, 63, 13);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Harga");
        lblNewLabel_1.setBounds(48, 71, 63, 13);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Status");
        lblNewLabel_2.setBounds(48, 100, 63, 13);
        contentPane.add(lblNewLabel_2);

        JButton btnSave = new JButton("SAVE");
        btnSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Service s = new Service();
                s.setJenis(txtJenis.getText());
                s.setHarga(txtHarga.getText());
                s.setStatus(txtStatus.getText());
                serviceRepo.save(s);
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
                    Service s = new Service();
                    s.setJenis(txtJenis.getText());
                    s.setHarga(txtHarga.getText());
                    s.setStatus(txtStatus.getText());
                    s.setId(id);
                    serviceRepo.update(s);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di update");
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
                    serviceRepo.delete(id);
                    reset();
                    loadTable();
                } else {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih data yang akan di hapus");
                }
            }
        });

        btnDelete.setFont(new Font("Times New Roman", Font.BOLD, 10));
        btnDelete.setBackground(new Color(255, 0, 0));
        btnDelete.setBounds(230, 145, 88, 21);
        contentPane.add(btnDelete);

        JButton btnCancel = new JButton("CANCEL");
        btnCancel.setFont(new Font("Times New Roman", Font.BOLD, 10));
        btnCancel.setBackground(new Color(255, 255, 0));
        btnCancel.setBounds(328, 145, 88, 21);
        contentPane.add(btnCancel);
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reset();
                id = null;
                tableServices.clearSelection();
            }
        });

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 190, 416, 343);
        contentPane.add(scrollPane);

        tableServices = new JTable();
        tableServices.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                id = tableServices.getValueAt(tableServices.getSelectedRow(), 0).toString();
                txtJenis.setText(tableServices.getValueAt(tableServices.getSelectedRow(), 1).toString());
                txtHarga.setText(tableServices.getValueAt(tableServices.getSelectedRow(), 2).toString());
                txtStatus.setText(tableServices.getValueAt(tableServices.getSelectedRow(), 3).toString());
            }
        });
        scrollPane.setViewportView(tableServices);
        loadTable();
    }
}