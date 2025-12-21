package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static Connection connection;

    private static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost:3306/projectpbo"; 
    private static final String USER = "root";
    private static final String PASS = "";

    private DbConnection() {}

    public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName(JDBC_DRIVER);
                connection = DriverManager.getConnection(DB_URL, USER, PASS);
                System.out.println("Koneksi ke database berhasil!");
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                System.out.println("Koneksi database gagal!");
            }
        }
        return connection;
    }	
    public static void main(String[] args) {
        System.out.println("Mencoba menghubungkan...");
        Connection cek = DbConnection.getConnection();
        
        if (cek != null) {
            System.out.println("TEST SUKSES:");
        } else {
            System.out.println("TEST GAGAL");
        }
    }
}
