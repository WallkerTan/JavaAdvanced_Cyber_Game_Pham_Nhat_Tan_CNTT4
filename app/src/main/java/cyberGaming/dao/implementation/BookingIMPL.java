package cyberGaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import cyberGaming.dao.BookingRepository;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.enums.BookingStatus;
import cyberGaming.util.DbUtility;

public class BookingIMPL implements BookingRepository {

    @Override
    public Map<Integer, Bookings> getAllbooking() {
        Map<Integer, Bookings> map = new HashMap<>();
        String sql = "SELECT * FROM bookings";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bookings b = mapBooking(rs);
                map.put(b.getBookingId(), b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Bookings getByUserID(int user_id) {
        Bookings temp = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "SELECT * FROM bookings WHERE user_id = ? AND status = ?";

        try {
            conn = DbUtility.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            ps.setString(2, BookingStatus.PENDING.toString());

            rs = ps.executeQuery();

            if (rs.next()) {
                temp = mapBooking(rs);
            } else {
                System.out.println("Khong tim thay booking!");
            }

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return temp;
    }

    @Override
    public Map<Integer, Bookings> getBystatus(BookingStatus status) {
        Map<Integer, Bookings> map = new HashMap<>();
        String sql = "SELECT * FROM bookings WHERE status = ?";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, status.name());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Bookings b = mapBooking(rs);
                map.put(b.getBookingId(), b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean insertBooking(Bookings booking) {
        String sql =
                "INSERT INTO bookings (user_id, pc_id, start_time, end_time, total_pc_amount, total_food_amount, grand_total, status, payment_status, notes) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, booking.getUserId());

            if (booking.getPcId() != null)
                ps.setInt(2, booking.getPcId());
            else
                ps.setNull(2, Types.INTEGER);

            ps.setTimestamp(3, booking.getStartTime());
            ps.setTimestamp(4, booking.getEndTime());
            ps.setDouble(5, booking.getTotalPcAmount());
            ps.setDouble(6, booking.getTotalFoodAmount());
            ps.setDouble(7, booking.getGrandTotal());
            ps.setString(8, booking.getStatus().name());
            ps.setString(9, booking.getPaymentStatus());
            ps.setString(10, booking.getNotes());

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
    public boolean updateBooking(int id, Bookings booking) {
        String sql =
                "UPDATE bookings SET pc_id=?, start_time=?, end_time=?, total_pc_amount=?, total_food_amount=?, grand_total=?, status=?, payment_status=?, notes=?, updated_at=CURRENT_TIMESTAMP WHERE booking_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);

            if (booking.getPcId() != null)
                ps.setInt(1, booking.getPcId());
            else
                ps.setNull(1, Types.INTEGER);

            ps.setTimestamp(2, booking.getStartTime());
            ps.setTimestamp(3, booking.getEndTime());
            ps.setDouble(4, booking.getTotalPcAmount());
            ps.setDouble(5, booking.getTotalFoodAmount());
            ps.setDouble(6, booking.getGrandTotal());
            ps.setString(7, booking.getStatus().name());
            ps.setString(8, booking.getPaymentStatus());
            ps.setString(9, booking.getNotes());
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

    private Bookings mapBooking(ResultSet rs) throws SQLException {
        Bookings b = new Bookings();

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
        b.setUpdatedAt();

        return b;
    }
}
