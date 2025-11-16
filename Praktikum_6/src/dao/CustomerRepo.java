package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import config.database;
import model.Customer;
import model.CustomerBuilder;

public class CustomerRepo {

    private static CustomerRepo instance;
    private Connection conn;

    private final String INSERT = "INSERT INTO customer (nama, email, alamat, hp) VALUES (?,?,?,?)";
    private final String SELECT = "SELECT * FROM customer";
    private final String UPDATE = "UPDATE customer SET nama=?, email=?, alamat=?, hp=? WHERE id=?";
    private final String DELETE = "DELETE FROM customer WHERE id=?";

    private CustomerRepo() {
        conn = database.koneksi();
    }

    public static CustomerRepo getInstance() {
        if (instance == null) {
            instance = new CustomerRepo();
        }
        return instance;
    }

    public List<Customer> show() {
        List<Customer> list = new ArrayList<>();
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(SELECT);

            while (rs.next()) {
                Customer c = new CustomerBuilder()
                        .setId(rs.getString("id"))
                        .setNama(rs.getString("nama"))
                        .setEmail(rs.getString("email"))
                        .setAlamat(rs.getString("alamat"))
                        .setHp(rs.getString("hp"))
                        .build();

                list.add(c);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public void save(Customer c) {
        try {
            PreparedStatement ps = conn.prepareStatement(INSERT);
            ps.setString(1, c.getNama());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getAlamat());
            ps.setString(4, c.getHp());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(Customer c) {
        try {
            PreparedStatement ps = conn.prepareStatement(UPDATE);
            ps.setString(1, c.getNama());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getAlamat());
            ps.setString(4, c.getHp());
            ps.setString(5, c.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        try {
            PreparedStatement ps = conn.prepareStatement(DELETE);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
