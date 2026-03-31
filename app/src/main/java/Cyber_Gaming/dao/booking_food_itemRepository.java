package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.booking_food_items;

//bảng phụ booking - food (n-n)
public interface booking_food_itemRepository {
    //lấy toàn bộ
    public Map<Integer,booking_food_items> getAllbookingGT();
    //Crud
    public boolean insertBooKingFt(booking_food_items bki);
    public boolean updateBookingFt(int id,booking_food_items bookingFt);
    public boolean deleteBookingFt(int id); 
}
