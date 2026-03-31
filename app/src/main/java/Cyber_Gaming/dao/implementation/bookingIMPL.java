package Cyber_Gaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import Cyber_Gaming.dao.bookingRepository;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.enums.BookingStatus;
import Cyber_Gaming.util.DbUtility;

public class bookingIMPL implements bookingRepository {

    @Override
    public Map<Integer, bookings> getAllbooking() {
        Map<Integer, bookings> map = new HashMap<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                bookings b = mapBooking(rs);
                map.put(b.getBookingId(), b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<Integer, bookings> getBystatus(BookingStatus status) {
        Map<Integer, bookings> map = new HashMap<>();
        String sql = "SELECT * FROM bookings WHERE status = ?";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                bookings b = mapBooking(rs);
                map.put(b.getBookingId(), b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean insertBooking(bookings b) {
        String sql =
                "INSERT INTO bookings (user_id, pc_id, start_time, end_time, total_pc_amount, total_food_amount, grand_total, status, payment_status, notes) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, b.getUserId());

            if (b.getPcId() != null)
                ps.setInt(2, b.getPcId());
            else
                ps.setNull(2, Types.INTEGER);

            ps.setTimestamp(3, b.getStartTime());
            ps.setTimestamp(4, b.getEndTime());
            ps.setDouble(5, b.getTotalPcAmount());
            ps.setDouble(6, b.getTotalFoodAmount());
            ps.setDouble(7, b.getGrandTotal());
            ps.setString(8, b.getStatus().name());
            ps.setString(9, b.getPaymentStatus());
            ps.setString(10, b.getNotes());

            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;

        } catch (Exception e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (Exception ex) {
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean updateBooking(int id, bookings b) {
        String sql =
                "UPDATE bookings SET pc_id=?, start_time=?, end_time=?, total_pc_amount=?, total_food_amount=?, grand_total=?, status=?, payment_status=?, notes=?, updated_at=CURRENT_TIMESTAMP WHERE booking_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);

            if (b.getPcId() != null)
                ps.setInt(1, b.getPcId());
            else
                ps.setNull(1, Types.INTEGER);

            ps.setTimestamp(2, b.getStartTime());
            ps.setTimestamp(3, b.getEndTime());
            ps.setDouble(4, b.getTotalPcAmount());
            ps.setDouble(5, b.getTotalFoodAmount());
            ps.setDouble(6, b.getGrandTotal());
            ps.setString(7, b.getStatus().name());
            ps.setString(8, b.getPaymentStatus());
            ps.setString(9, b.getNotes());
            ps.setInt(10, id);

            int rows = ps.executeUpdate();
            conn.commit();
            return rows > 0;

        } catch (Exception e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (Exception ex) {
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    @Override
    public boolean deleteBooking(int id) {
        String sql = "DELETE FROM bookings WHERE booking_id=?";

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

        } catch (Exception e) {
            try {
                if (conn != null)
                    conn.rollback();
            } catch (Exception ex) {
            }
            e.printStackTrace();
            return false;

        } finally {
            try {
                if (ps != null)
                    ps.close();
            } catch (Exception e) {
            }
            try {
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
            }
        }
    }

    private bookings mapBooking(ResultSet rs) throws SQLException {
        bookings b = new bookings();

        b.setBookingId(rs.getInt("booking_id"));
        b.setUserId(rs.getInt("user_id"));

        int pcId = rs.getInt("pc_id");
        if (!rs.wasNull())
            b.setPcId(pcId);

        b.setStartTime(rs.getTimestamp("start_time"));
        b.setEndTime(rs.getTimestamp("end_time"));
        b.setTotalHours(rs.getDouble("total_hours"));
        b.setTotalPcAmount(rs.getDouble("total_pc_amount"));
        b.setTotalFoodAmount(rs.getDouble("total_food_amount"));
        b.setGrandTotal(rs.getDouble("grand_total"));

        String status = rs.getString("status");
        if (status != null)
            b.setStatus(BookingStatus.valueOf(status));

        b.setPaymentStatus(rs.getString("payment_status"));
        b.setNotes(rs.getString("notes"));
        b.setCreatedAt(rs.getTimestamp("created_at"));
        b.setUpdatedAt(rs.getTimestamp("updated_at"));

        return b;
    }
}
