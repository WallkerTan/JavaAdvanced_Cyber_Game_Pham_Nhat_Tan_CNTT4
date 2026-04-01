package cyberGaming.dao;

import java.util.Map;
import cyberGaming.unity.Bookings;
import cyberGaming.unity.enums.BookingStatus;

public interface BookingRepository {
    // lấy toàn bộ lịch sử booking
    public Map<Integer, Bookings> getAllbooking();

    // lấy theo trạng thái
    public Map<Integer, Bookings> getBystatus(BookingStatus status);

    // CRUD
    // lấy theo id nguoi dung
    public Bookings getByUserID(int user_id);

    public boolean insertBooking(Bookings booking);

    public boolean updateBooking(int id, Bookings booking);

    public boolean deleteBooking(int id);
}
