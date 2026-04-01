package cyberGaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import cyberGaming.dao.FoodRepository;
import cyberGaming.unity.Foods;
import cyberGaming.util.DbUtility;

public class FoodIMPL implements FoodRepository {

    @Override
    public Map<Integer, Foods> getAllfood() {
        Map<Integer, Foods> map = new HashMap<>();
        String sql = "SELECT * FROM foods";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Foods f = new Foods();
                f.setFoodId(rs.getInt("food_id"));
                f.setFoodName(rs.getString("food_name"));
                f.setDescription(rs.getString("description"));
                f.setPrice(rs.getDouble("price"));
                f.setStockQuantity(rs.getInt("stock_quantity"));
                f.setAvailable(rs.getBoolean("is_available"));
                f.setCreatedAt(rs.getTimestamp("created_at"));
                f.setUpdatedAt(rs.getTimestamp("updated_at"));

                map.put(f.getFoodId(), f);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean insertFood(Foods f) {
        String sql =
                "INSERT INTO foods (food_name, description, price, stock_quantity, is_available) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, f.getFoodName());
            ps.setString(2, f.getDescription());
            ps.setDouble(3, f.getPrice());
            ps.setInt(4, f.getStockQuantity());
            ps.setBoolean(5, f.isAvailable());

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
    public boolean updateFood(int id, Foods f) {
        String sql =
                "UPDATE foods SET food_name=?, description=?, price=?, stock_quantity=?, is_available=?, updated_at=CURRENT_TIMESTAMP WHERE food_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, f.getFoodName());
            ps.setString(2, f.getDescription());
            ps.setDouble(3, f.getPrice());
            ps.setInt(4, f.getStockQuantity());
            ps.setBoolean(5, f.isAvailable());
            ps.setInt(6, id);

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
    public boolean deleteFood(int id) {
        String sql = "DELETE FROM foods WHERE food_id=?";

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
