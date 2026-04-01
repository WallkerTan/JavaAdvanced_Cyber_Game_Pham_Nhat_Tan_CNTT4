package cyberGaming.dao.implementation;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import cyberGaming.dao.PcsRepository;
import cyberGaming.unity.Pcs;
import cyberGaming.unity.enums.AreaType;
import cyberGaming.unity.enums.PCStatus;
import cyberGaming.util.DbUtility;

public class PcsIMPL implements PcsRepository {

    @Override
    public Map<Integer, Pcs> getAllPcs() {
        Map<Integer, Pcs> map = new HashMap<>();
        String sql = "SELECT * FROM Pcs";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Pcs p = new Pcs();
                p.setPcId(rs.getInt("pc_id"));
                p.setPcNumber(rs.getString("pc_number"));
                p.setConfiguration(rs.getString("configuration"));
                p.setPricePerHour(rs.getDouble("price_per_hour"));
                p.setCreatedAt(rs.getTimestamp("created_at"));
                p.setUpdatedAt(rs.getTimestamp("updated_at"));

                String area = rs.getString("area");
                if (area != null)
                    p.setArea(AreaType.valueOf(area));

                String status = rs.getString("status");
                if (status != null)
                    p.setStatus(PCStatus.valueOf(status));

                map.put(p.getPcId(), p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public boolean insertPcs(Pcs p) {
        String sql =
                "INSERT INTO Pcs (pc_number, area, configuration, price_per_hour, status) VALUES (?,?,?,?,?)";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPcNumber());
            ps.setString(2, p.getArea().name());
            ps.setString(3, p.getConfiguration());
            ps.setDouble(4, p.getPricePerHour());
            ps.setString(5, p.getStatus().name());

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
    public boolean updatePcs(int id, Pcs p) {
        String sql =
                "UPDATE Pcs SET pc_number=?, area=?, configuration=?, price_per_hour=?, status=?, updated_at=CURRENT_TIMESTAMP WHERE pc_id=?";

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DbUtility.getConnection();
            conn.setAutoCommit(false);

            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getPcNumber());
            ps.setString(2, p.getArea().name());
            ps.setString(3, p.getConfiguration());
            ps.setDouble(4, p.getPricePerHour());
            ps.setString(5, p.getStatus().name());
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
    public boolean deletePcs(int id) {
        String sql = "DELETE FROM Pcs WHERE pc_id=?";

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

    public Map<Integer, Pcs> getAllPcsByStatus(PCStatus s) {
        Map<Integer, Pcs> map = new HashMap<>();

        String sql = "SELECT * FROM Pcs WHERE status = ?";

        try (Connection conn = DbUtility.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, s.name());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Pcs p = new Pcs();

                    p.setPcId(rs.getInt("pc_id"));
                    p.setPcNumber(rs.getString("pc_number"));
                    p.setConfiguration(rs.getString("configuration"));
                    p.setPricePerHour(rs.getDouble("price_per_hour"));
                    p.setCreatedAt(rs.getTimestamp("created_at"));
                    p.setUpdatedAt(rs.getTimestamp("updated_at"));

                    // Xử lý AreaType
                    String areaStr = rs.getString("area");
                    if (areaStr != null) {
                        p.setArea(AreaType.valueOf(areaStr));
                    }

                    // Xử lý Pcstatus
                    String statusStr = rs.getString("status");
                    if (statusStr != null) {
                        p.setStatus(PCStatus.valueOf(statusStr));
                    }

                    map.put(p.getPcId(), p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return map;
    }

}
