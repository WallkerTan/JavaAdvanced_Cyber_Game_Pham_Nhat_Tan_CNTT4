package Cyber_Gaming.dao;

import java.util.Map;
import Cyber_Gaming.unity.bookings;
import Cyber_Gaming.unity.enums.BookingStatus;

public interface bookingRepository {
    // lấy toàn bộ lịch sử booking
    public Map<Integer, bookings> getAllbooking();

    // lấy theo trạng thái
    public Map<Integer, bookings> getBystatus(BookingStatus status);

    // CRUD
    // lấy theo id nguoi dung
    public bookings getByUserID(int user_id);

    public boolean insertBooking(bookings booking);

    public boolean updateBooking(int id, bookings booking);

    public boolean deleteBooking(int id);
}
