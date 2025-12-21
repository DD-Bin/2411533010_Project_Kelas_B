package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.pesanan;
import model.pesananBuilder;
import model.makanan;
import model.minuman;
import model.produk;
import service.ProdukService;
import util.FormatterUtil;
import util.ValidationException;
import util.ValidationUtil;

public class ProdukFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    
    private JTextField txtId, txtNama, txtHarga, txtInfo;
    private JComboBox<String> cmbKategori;
    private JTable tableProduk;
    private DefaultTableModel tableModel;
    private JButton btnSave, btnUpdate, btnDelete, btnClear, btnTestStruk; 


    private ProdukService produkService;
    private List<produk> listProduk; 

  
    private Color primaryColor = new Color(51, 153, 255);
    private Color successColor = new Color(51, 204, 51);
    private Color warningColor = new Color(255, 204, 51);
    private Color dangerColor = new Color(255, 102, 102);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                ProdukFrame frame = new ProdukFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ProdukFrame() {
        produkService = new ProdukService();
        
        setTitle("Drive Thru System - Admin Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 950, 600);
        
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        initComponent();
        loadTable();
    }

    private void initComponent() {
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(primaryColor);
        headerPanel.setBounds(0, 0, 950, 60);
        headerPanel.setLayout(null);
        contentPane.add(headerPanel);
        
        JLabel lblTitle = new JLabel("MANAJEMEN MENU DRIVE THRU");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("SansSerif", Font.BOLD, 24));
        lblTitle.setBounds(20, 10, 500, 40);
        headerPanel.add(lblTitle);

        int y = 80;
        int xLabel = 30;
        int xInput = 150;
        
        contentPane.add(createLabel("ID Menu:", xLabel, y));
        txtId = createTextField(xInput, y);
        contentPane.add(txtId);

        y += 40;
        contentPane.add(createLabel("Nama Menu:", xLabel, y));
        txtNama = createTextField(xInput, y);
        contentPane.add(txtNama);

        y += 40;
        contentPane.add(createLabel("Harga:", xLabel, y));
        txtHarga = createTextField(xInput, y);
        contentPane.add(txtHarga);

        y += 40;
        contentPane.add(createLabel("Kategori:", xLabel, y));
        cmbKategori = new JComboBox<>(new String[]{"Makanan", "Minuman"});
        cmbKategori.setBackground(Color.WHITE);
        cmbKategori.setBounds(xInput, y, 200, 30);
        contentPane.add(cmbKategori);

        y += 40;
        contentPane.add(createLabel("Info:", xLabel, y));
        txtInfo = createTextField(xInput, y);
        contentPane.add(txtInfo);

        int btnY = 320;
        btnSave = createButton("Simpan", successColor, 30, btnY);
        btnSave.addActionListener(e -> saveAction());
        contentPane.add(btnSave);

        btnUpdate = createButton("Update", warningColor, 140, btnY);
        btnUpdate.addActionListener(e -> updateAction());
        contentPane.add(btnUpdate);

        btnDelete = createButton("Hapus", dangerColor, 250, btnY);
        btnDelete.addActionListener(e -> deleteAction());
        contentPane.add(btnDelete);

        btnClear = createButton("Reset", Color.GRAY, 360, btnY);
        btnClear.addActionListener(e -> reset());
        contentPane.add(btnClear);
        
        btnTestStruk = new JButton("Print Struk");
        btnTestStruk.setBounds(30, 380, 440, 40);
        btnTestStruk.setBackground(new Color(100, 100, 255));
        btnTestStruk.setForeground(Color.WHITE);
        btnTestStruk.setFont(new Font("SansSerif", Font.BOLD, 12));
        btnTestStruk.addActionListener(e -> testBuilderPattern());
        contentPane.add(btnTestStruk);

        JLabel lblTable = new JLabel("Daftar Menu:");
        lblTable.setFont(new Font("SansSerif", Font.BOLD, 16));
        lblTable.setBounds(500, 70, 200, 30);
        contentPane.add(lblTable);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(500, 100, 420, 400);
        contentPane.add(scrollPane);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nama", "Harga Final", "Kat", "Info"}, 0);
        tableProduk = new JTable(tableModel);
        tableProduk.setRowHeight(25);
        scrollPane.setViewportView(tableProduk);

        tableProduk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = tableProduk.getSelectedRow();
                if (row != -1) {
                    produk p = listProduk.get(row);
                    txtId.setText(p.getId());
                    txtNama.setText(p.getNama());
                    txtHarga.setText(String.valueOf((int)p.getHargaDasar()));
                    
                    if (p instanceof makanan) {
                        cmbKategori.setSelectedItem("Makanan");
                        txtInfo.setText(String.valueOf(((makanan)p).isPedas()));
                    } else {
                        cmbKategori.setSelectedItem("Minuman");
                        txtInfo.setText(((minuman)p).getUkuran());
                    }
                    
                    txtId.setEditable(false);
                    btnSave.setEnabled(false);
                }
            }
        });
    }

    private void saveAction() { processData(false); }
    private void updateAction() { processData(true); }

    private void processData(boolean isUpdate) {
        try {
            String id = txtId.getText();
            String nama = txtNama.getText();
            double harga = Double.parseDouble(txtHarga.getText());
            String info = txtInfo.getText();
            String kat = (String) cmbKategori.getSelectedItem();

            ValidationUtil.validateInput(id);
            ValidationUtil.validateInput(nama);

            produk p;
            if (kat.equals("Makanan")) {
                p = new makanan(id, nama, harga, Boolean.parseBoolean(info));
            } else {
                p = new minuman(id, nama, harga, info);
            }

            if(isUpdate) produkService.updateProduk(p);
            else produkService.addProduk(p);

            reset();
            loadTable();
            JOptionPane.showMessageDialog(this, "Sukses!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }

    private void deleteAction() {
        int row = tableProduk.getSelectedRow();
        if(row != -1) {
            String id = (String) tableModel.getValueAt(row, 0);
            produkService.deleteProduk(id);
            reset();
            loadTable();
        }
    }

    private void reset() {
        txtId.setText(""); txtId.setEditable(true);
        txtNama.setText(""); txtHarga.setText(""); txtInfo.setText("");
        btnSave.setEnabled(true);
        tableProduk.clearSelection();
    }

    private void loadTable() {
        tableModel.setRowCount(0);
        listProduk = produkService.getAllProduk();
        for (produk p : listProduk) {
            String info = (p instanceof makanan) ? 
                          (((makanan)p).isPedas() ? "Pedas" : "Ori") : 
                          ((minuman)p).getUkuran();
            
            tableModel.addRow(new Object[]{
                p.getId(), p.getNama(), 
                FormatterUtil.formatRupiah(p.hitungHargaFinal()), 
                (p instanceof makanan) ? "Mkn" : "Min", 
                info
            });
        }
    }

    private void testBuilderPattern() {
        int row = tableProduk.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih menu dulu!");
            return;
        }

        produk p = listProduk.get(row);

        pesananBuilder builder = new pesananBuilder();
        builder.setIdOtomatis();          
        builder.setNamaPelanggan("Admin"); 
        builder.tambahItem(p);            
        
        pesanan pesanan = builder.build();

        JOptionPane.showMessageDialog(this, 
            pesanan.getStruk() + "\n\n[Builder Pattern OK]", 
            "Test Struk", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    private JLabel createLabel(String text, int x, int y) {
        JLabel lbl = new JLabel(text);
        lbl.setBounds(x, y, 120, 20);
        return lbl;
    }

    private JTextField createTextField(int x, int y) {
        JTextField txt = new JTextField();
        txt.setBounds(x, y, 200, 30);
        return txt;
    }
    
    private JButton createButton(String text, Color bg, int x, int y) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 100, 35);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        return btn;
    }
}