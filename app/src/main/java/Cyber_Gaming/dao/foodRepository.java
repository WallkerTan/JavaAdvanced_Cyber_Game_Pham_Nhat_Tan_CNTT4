package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.foods;

public interface foodRepository {
    //lấy danh sách đồ ăn
    public Map<Integer,foods> getAllfood();
    //CRUD
    public boolean insertFood(foods f);
    public boolean updateFood(int id , foods food);
    public boolean deleteFood(int id);
}
