package cyberGaming.dao;

import java.util.Map;
import cyberGaming.unity.Pcs;
import cyberGaming.unity.enums.PCStatus;

public interface PcsRepository {
    // lấy toàn bộ máy 
    public Map<Integer,Pcs> getAllPcs();
    public Map<Integer, Pcs> getAllPcsByStatus(PCStatus s);
    //crud
    public boolean insertPcs(Pcs p);
    public boolean updatePcs(int id,Pcs p);
    public boolean deletePcs(int id);
}
