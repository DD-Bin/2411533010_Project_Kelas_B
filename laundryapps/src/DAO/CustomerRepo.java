package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import config.database;
import model.Customer;

public class CustomerRepo implements CustomerDao {
    private Connection connection;

    final String insert = "INSERT INTO customer (nama, alamat, nomorhp) VALUES (?,?,?);";
    final String select = "SELECT * FROM customer;";
    final String delete = "DELETE FROM customer WHERE id=?;";
    final String update = "UPDATE customer SET nama=?, alamat=?, nomorhp=? WHERE id=?;";

    public CustomerRepo() {
        connection = database.koneksi();
    }

    @Override
    public void save(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(insert);
            st.setString(1, customer.getNama());
            st.setString(2, customer.getAlamat());
            st.setString(3, customer.getNomorhp());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) st.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Customer> show() {
        List<Customer> list = new ArrayList<>();
        Statement st = null;
        ResultSet rs = null;
        try {
            st = connection.createStatement();
            rs = st.executeQuery(select);
            while(rs.next()) {
                Customer customer = new Customer();
                customer.setId(rs.getString("id"));
                customer.setNama(rs.getString("nama"));
                customer.setAlamat(rs.getString("alamat"));
                customer.setNomorhp(rs.getString("nomorhp"));
                list.add(customer);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(rs != null) rs.close();
                if(st != null) st.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public void delete(String id) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(delete);
            st.setString(1, id);
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) st.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Customer customer) {
        PreparedStatement st = null;
        try {
            st = connection.prepareStatement(update);
            st.setString(1, customer.getNama());
            st.setString(2, customer.getAlamat());
            st.setString(3, customer.getNomorhp());
            st.setString(4, customer.getId());
            st.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if(st != null) st.close();
            } catch(SQLException e) {
                e.printStackTrace();
            }
        }
    }
}