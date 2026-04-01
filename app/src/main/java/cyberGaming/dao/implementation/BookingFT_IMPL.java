package cyberGaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import cyberGaming.dao.BookingFoodItemRepository;
import cyberGaming.unity.BookingFoodItems;
import cyberGaming.util.DbUtility;

public class BookingFT_IMPL implements BookingFoodItemRepository {

    @Override
    public Map<Integer, BookingFoodItems> getAllbookingGT() {
        Map<Integer, BookingFoodItems> map = new HashMap<>();
        String sql = "SELECT * FROM BookingFoodItems";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                BookingFoodItems b = new BookingFoodItems();
                b.setItemId(rs.getInt("item_id"));
                b.setBookingId(rs.getInt("booking_id"));
                b.setFoodId(rs.getInt("food_id"));
                b.setQuantity(rs.getInt("quantity"));
                b.setPriceAtTime(rs.getDouble("price_at_time"));
                b.setSubtotal(rs.getDouble("subtotal"));

                map.put(b.getItemId(), b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean insertBooKingFt(BookingFoodItems bki) {
        String sql =
                "INSERT INTO BookingFoodItems (booking_id, food_id, quantity, price_at_time) VALUES (?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, bki.getBookingId());
            ps.setInt(2, bki.getFoodId());
            ps.setInt(3, bki.getQuantity());
            ps.setDouble(4, bki.getPriceAtTime());

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
    public boolean updateBookingFt(int id, BookingFoodItems bki) {
        String sql =
                "UPDATE BookingFoodItems SET booking_id=?, food_id=?, quantity=?, price_at_time=? WHERE item_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setInt(1, bki.getBookingId());
            ps.setInt(2, bki.getFoodId());
            ps.setInt(3, bki.getQuantity());
            ps.setDouble(4, bki.getPriceAtTime());
            ps.setInt(5, id);

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
    public boolean deleteBookingFt(int id) {
        String sql = "DELETE FROM BookingFoodItems WHERE item_id=?";

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
}
