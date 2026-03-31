package Cyber_Gaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import Cyber_Gaming.dao.userRepository;
import Cyber_Gaming.unity.users;
import Cyber_Gaming.unity.enums.UserRole;
import Cyber_Gaming.util.DbConnection;
import Cyber_Gaming.util.DbUtility;

public class userIMPL implements userRepository {



    // ====================== GET ALL USERS ======================
    @Override
    public Map<Integer, users> getAlluser() {
        Map<Integer, users> userMap = new HashMap<>();

        String sql = "SELECT * FROM users WHERE is_active = true";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                users u = mapResultSetToUser(rs);
                userMap.put(u.getUserId(), u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userMap;
    }

    // ====================== GET ADMIN ======================
    @Override
    public users getAdmin() {
        String sql = "SELECT * FROM users WHERE role = 'ADMIN' AND is_active = true LIMIT 1";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                return mapResultSetToUser(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Không tìm thấy admin
    }

    @Override
    public users findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ? AND is_active = TRUE";

        try (Connection conn = DbUtility.getConnection(); // Dùng DbUtility cho thống nhất
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("tim ng dung "+username);
                if (rs.next()) {
                    users u = new users(); // Dùng constructor mặc định

                    u.setUserId(rs.getInt("user_id"));
                    u.setUsername(rs.getString("username"));
                    u.setfull_name(rs.getString("full_name"));
                    u.setPhone(rs.getString("phone"));
                    u.setBalance(rs.getDouble("balance"));
                    u.setActive(rs.getBoolean("is_active"));
                    u.setCreatedAt(rs.getTimestamp("created_at"));
                    u.setUpdatedAt(rs.getTimestamp("updated_at"));
                    u.setPasswordHash(rs.getString("password_hash")); // ← Sửa ở đây

                    String roleStr = rs.getString("role");
                    if (roleStr != null) {
                        u.setRole(UserRole.valueOf(roleStr));
                    }

                    return u;
                }
            }
            
        } catch (SQLException e) {
            System.out.println("khong tim thay "+username);
            e.printStackTrace();
        }

        return null; // Không tìm thấy user
    }

    // ====================== INSERT USER ======================
    @Override
    public boolean insertUser(users u) {
        String sql = "INSERT INTO users (username, password_hash, full_name, phone, role) "
                + "VALUES (?, ?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getfull_name());
            ps.setString(4, u.getPhone());
            ps.setString(5, u.getRole().name());

            int rows = ps.executeUpdate();

            conn.commit();
            return rows > 0;

        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // ====================== UPDATE USER ======================
    @Override
    public boolean updateUser(int id, users u) {
        String sql = "UPDATE users SET full_name = ?, phone = ?, balance = ? "
                + "WHERE user_id = ? AND is_active = true";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, u.getfull_name());
            ps.setString(2, u.getPhone());
            ps.setDouble(3, u.getBalance());
            ps.setInt(4, id);

            int rows = ps.executeUpdate();

            conn.commit();
            return rows > 0;

        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean deleteUser(int id) {
        String sql = "UPDATE users SET is_active = false WHERE user_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

            int rows = ps.executeUpdate();

            conn.commit();
            return rows > 0;

        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private users mapResultSetToUser(ResultSet rs) throws SQLException {
        users u = new users();

        u.setUserId(rs.getInt("user_id"));
        u.setUsername(rs.getString("username"));
        u.setfull_name(rs.getString("full_name"));
        u.setPhone(rs.getString("phone"));
        u.setBalance(rs.getDouble("balance"));
        u.setActive(rs.getBoolean("is_active"));
        u.setCreatedAt(rs.getTimestamp("created_at"));
        u.setUpdatedAt(rs.getTimestamp("updated_at"));
        u.setPasswordHash(rs.getString("password_hash"));

        String roleStr = rs.getString("role");
        if (roleStr != null) {
            u.setRole(UserRole.valueOf(roleStr));
        }

        return u;
    }
}
