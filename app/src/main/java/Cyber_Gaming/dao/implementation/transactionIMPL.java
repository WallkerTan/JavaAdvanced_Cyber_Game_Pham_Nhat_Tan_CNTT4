package Cyber_Gaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import Cyber_Gaming.dao.transactionRepository;
import Cyber_Gaming.unity.transactions;
import Cyber_Gaming.unity.enums.TransactionType; // Giả sử bạn có enum này
import Cyber_Gaming.util.DbUtility;

public class transactionIMPL implements transactionRepository {

    // ====================== LẤY TOÀN BỘ LỊCH SỬ GIAO DỊCH ======================
    @Override
    public Map<Integer, transactions> getAlltransaction() {
        Map<Integer, transactions> transactionMap = new HashMap<>();
        String sql = "SELECT * FROM transactions ORDER BY created_at DESC";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                transactions t = mapResultSetToTransaction(rs);
                transactionMap.put(t.getTransactionId(), t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionMap;
    }

    // ====================== LẤY LỊCH SỬ GIAO DỊCH THEO USER ID ======================
    @Override
    public Map<Integer, transactions> getTransactionByid(int id_User) {
        Map<Integer, transactions> transactionMap = new HashMap<>();
        String sql = "SELECT * FROM transactions WHERE user_id = ? ORDER BY created_at DESC";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id_User);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                transactions t = mapResultSetToTransaction(rs);
                transactionMap.put(t.getTransactionId(), t);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactionMap;
    }

    // ====================== THÊM GIAO DỊCH MỚI ======================
    @Override
    public boolean addTransaction(transactions tran) {
        String sql = "INSERT INTO transactions (user_id, booking_id, type, amount, description) "
                + "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, tran.getUserId());
            ps.setObject(2, tran.getBookingId(), Types.INTEGER); // Có thể null
            ps.setString(3, tran.getType().name()); // Enum -> String
            ps.setDouble(4, tran.getAmount());
            ps.setString(5, tran.getDescription());

            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ====================== XÓA GIAO DỊCH ======================
    @Override
    public boolean deleteTransaction(int id) {
        String sql = "DELETE FROM transactions WHERE transaction_id = ?";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            int rows = ps.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    // ====================== HELPER METHOD - MAP RESULTSET ======================
    private transactions mapResultSetToTransaction(ResultSet rs) throws SQLException {
        transactions t = new transactions();

        t.setTransactionId(rs.getInt("transaction_id"));
        t.setUserId(rs.getInt("user_id"));

        // booking_id có thể null
        int bookingId = rs.getInt("booking_id");
        if (!rs.wasNull()) {
            t.setBookingId(bookingId);
        }

        // Xử lý Enum TransactionType
        String typeStr = rs.getString("type");
        if (typeStr != null) {
            t.setType(TransactionType.valueOf(typeStr));
        }

        t.setAmount(rs.getDouble("amount"));
        t.setDescription(rs.getString("description"));
        t.setCreatedAt(rs.getTimestamp("created_at"));

        return t;
    }
}
