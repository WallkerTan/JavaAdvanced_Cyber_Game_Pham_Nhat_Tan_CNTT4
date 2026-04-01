package cyberGaming.dao;

import java.util.Map;
import cyberGaming.unity.Foods;

public interface FoodRepository {
    //lấy danh sách đồ ăn
    public Map<Integer,Foods> getAllfood();
    //CRUD
    public boolean insertFood(Foods f);
    public boolean updateFood(int id , Foods food);
    public boolean deleteFood(int id);
}
