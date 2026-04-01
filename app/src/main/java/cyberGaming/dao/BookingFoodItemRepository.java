package cyberGaming.dao;

import java.util.Map;
import cyberGaming.unity.BookingFoodItems;

//bảng phụ booking - food (n-n)
public interface BookingFoodItemRepository {
    //lấy toàn bộ
    public Map<Integer,BookingFoodItems> getAllbookingGT();
    //Crud
    public boolean insertBooKingFt(BookingFoodItems bki);
    public boolean updateBookingFt(int id,BookingFoodItems bookingFt);
    public boolean deleteBookingFt(int id); 
}
