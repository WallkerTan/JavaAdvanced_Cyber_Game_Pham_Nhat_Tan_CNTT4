package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.pcs;

public interface pcsRepository {
    // lấy toàn bộ máy 
    public Map<Integer,pcs> getAllpcs();
    //crud
    public boolean insertPcs(pcs p);
    public boolean updatePcs(int id,pcs p);
    public boolean deletePcs(int id);
}
