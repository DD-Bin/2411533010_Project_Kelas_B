package service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.makanan;
import model.minuman;
import model.produk;
import util.DbConnection;

public class ProdukService {
    private final String INSERT = "INSERT INTO produk (id, nama, harga_dasar,"
    		+ 						" kategori, info_tambahan) VALUES (?, ?, ?, ?, ?)";
    private final String SELECT = "SELECT * FROM produk";
    private final String DELETE = "DELETE FROM produk WHERE id = ?";
    
    // CREATE:
    public void addProduk(produk p) {
        Connection conn = DbConnection.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(INSERT)) {
            
            ps.setString(1, p.getId());
            ps.setString(2, p.getNama());
            ps.setDouble(3, p.getHargaDasar());
            if (p instanceof makanan) {
                ps.setString(4, "Makanan");
                makanan m = (makanan) p;
                ps.setString(5, String.valueOf(m.isPedas())); 
            } else if (p instanceof minuman) {
                ps.setString(4, "Minuman");
                minuman m = (minuman) p;
                ps.setString(5, m.getUkuran());
            }
            ps.executeUpdate();
            System.out.println("Data " + p.getNama() + " berhasil disimpan!");
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    // READ
    public List<produk> getAllProduk() {
        List<produk> listProduk = new ArrayList<>();
        Connection conn = DbConnection.getConnection();

        try (Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(SELECT)) {

            while (rs.next()) {
                String id = rs.getString("id");
                String nama = rs.getString("nama");
                double harga = rs.getDouble("harga_dasar");
                String kategori = rs.getString("kategori");
                String info = rs.getString("info_tambahan");

                if (kategori.equalsIgnoreCase("Makanan")) {
                    boolean isPedas = Boolean.parseBoolean(info);
                    listProduk.add(new makanan(id, nama, harga, isPedas));
                } else {
                    listProduk.add(new minuman(id, nama, harga, info));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return listProduk;
    }

    // DELETE
    public void deleteProduk(String id) {
        Connection conn = DbConnection.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(DELETE)) {
            ps.setString(1, id);
            ps.executeUpdate();
            System.out.println("Data berhasil dihapus!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
 // UPDATE
    public void updateProduk(produk p) {
        String query = "UPDATE produk SET nama=?, harga_dasar=?, kategori=?, info_tambahan=? WHERE id=?";
        Connection conn = DbConnection.getConnection();

        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setString(1, p.getNama());
            ps.setDouble(2, p.getHargaDasar());

            if (p instanceof makanan) {
                ps.setString(3, "Makanan");
                makanan m = (makanan) p;
                ps.setString(4, String.valueOf(m.isPedas())); 
            } else if (p instanceof minuman) {
                ps.setString(3, "Minuman");
                minuman m = (minuman) p;
                ps.setString(4, m.getUkuran());
            }

            ps.setString(5, p.getId());

            ps.executeUpdate();
            System.out.println("Data " + p.getNama() + " berhasil diupdate!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}