package service;

import java.sql.*;
import config.database;
import model.User;

public class LoginService {
    public boolean authenticate(User user) {
        try (Connection conn = database.koneksi()) {
            String sql = "SELECT * FROM user WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            ResultSet rs = stmt.executeQuery();

            return rs.next(); 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
	