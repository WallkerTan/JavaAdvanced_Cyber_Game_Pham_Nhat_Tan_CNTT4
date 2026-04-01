package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.pcs;
import Cyber_Gaming.unity.enums.PCStatus;

public interface pcsRepository {
    // lấy toàn bộ máy 
    public Map<Integer,pcs> getAllpcs();
    public Map<Integer, pcs> getAllpcsByStatus(PCStatus s);
    //crud
    public boolean insertPcs(pcs p);
    public boolean updatePcs(int id,pcs p);
    public boolean deletePcs(int id);
}
